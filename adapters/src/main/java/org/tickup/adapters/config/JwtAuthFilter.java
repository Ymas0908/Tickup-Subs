package org.tickup.adapters.config;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;
    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    public JwtAuthFilter(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            //Recuperer l'entete de la requete
            String authHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;
//            log.info("---------------------");
//            log.info("AUTH HEADER {} ", authHeader);
//            log.info("URL {} ", request.getRequestURL());
//            log.info("---------------------");


            // Verifier que l'entete n'est pas null et contient <<Bearer >>
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                // Extraire le token
                token = authHeader.substring(7);
                //Extraire l'utilisateur
                username = JwtHelper.extractUsername(token);
            }

            // Arreter le proccessus si le token est null
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            // Verifier que le username n'est pas null et qu'il n'y a pas déjà une authentification
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Rechercher l'utilisateur dans la base de donnée à partir de son username
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                //Verifier la validité du token
                if (JwtHelper.validateToken(token, userDetails)) {
                    // Creer un objet d'authentification avec les infos nesseccaires (Les roles de l'utilisateur  l'adresse IP de l'utilisateur, le navigateur, etc.)
                    // puis l'ajoute dans le context de spring security
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (AccessDeniedException e) {
//            e.printStackTrace();
//            System.out.println("Access Denied" + e.getMessage());
            log.warn("Access Denied Message {},URL : {}", e.getMessage(), request.getRequestURL().toString());
            throw e;
        } catch (ExpiredJwtException e) {
//            e.printStackTrace();
//            System.out.println("Token Expired////////////////////" + e.getMessage());
            log.warn("Token Expired Message {},URL : {}", e.getMessage(), request.getRequestURL());
            throw e;
        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error in JWT authentication filter" + e.getMessage());
//            String requestURI = request.getRequestURI();
//            System.out.println("Request URI////////////////////" + requestURI);
            log.warn("Error in JWT authentication filter Message {},URL : {}", e.getMessage(), request.getRequestURI());
            throw e;
        }
    }


}
