package org.tickup.adapters.config;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;
    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    // Liste des endpoints publics
    private static final List<String> PUBLIC_URLS = List.of(
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/api/v1/usagers/usager" // Création utilisateur
    );

    public JwtAuthFilter(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Si l'URL fait partie des routes publiques, on ne filtre pas
        String path = request.getRequestURI();
        return PUBLIC_URLS.stream().anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Récupérer l'entête Authorization
            String authHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;

            log.info("---------------------");
            log.info("AUTH HEADER {} ", authHeader);
            log.info("URL {} ", request.getRequestURL());
            log.info("---------------------");

            // Vérifier que l'entête n'est pas null et contient "Bearer "
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = JwtHelper.extractUsername(token);
            }

            // Si token ou username absent, continuer le filtre
            if (token == null || username == null) {
                filterChain.doFilter(request, response);
                return;
            }

            // Vérifier que l'utilisateur n'est pas déjà authentifié
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Vérifier la validité du token
                if (JwtHelper.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            // Token expiré
            log.warn("Token Expired Message {}, URL: {}", e.getMessage(), request.getRequestURL());
            // Pour les routes publiques, on ignore et on continue
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.warn("Error in JWT authentication filter Message {}, URL: {}", e.getMessage(), request.getRequestURI());
            filterChain.doFilter(request, response);
        }
    }
}