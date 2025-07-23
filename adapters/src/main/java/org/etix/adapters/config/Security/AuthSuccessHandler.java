package org.etix.adapters.config.Security;

import com.itcentrex.adapter.entities.Security.LogAccesEntity;
import com.itcentrex.domain.models.enumeration.TypeLog;
import com.itcentrex.domain.ports.driven.LogAccesRepo;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;
import java.time.LocalDateTime;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private final LogAccesRepo logAccesRepo;
    private final HttpSessionRequestCache requestCache;

    public AuthSuccessHandler(LogAccesRepo logAccesRepo) {
        this.logAccesRepo = logAccesRepo;
        this.requestCache = new HttpSessionRequestCache();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        // Journalisation
        traceAuthenticationLog(authentication);

        // Récupération de la requête originale
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        ApplicationUtilisateur principal = (ApplicationUtilisateur) authentication.getPrincipal();

        // Gestion de la redirection
        String targetUrl = determineTargetUrl(savedRequest, principal);

        // Redirection selon le contexte
        if (FacesContext.getCurrentInstance() != null) {
            // Contexte JSF
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect(request.getContextPath() + targetUrl);
        } else {
            // Redirection standard
            response.sendRedirect(targetUrl);
        }
    }

    private String determineTargetUrl(SavedRequest savedRequest, ApplicationUtilisateur principal) {
        // 1. Priorité à la requête sauvegardée
        if (savedRequest != null) {
            return savedRequest.getRedirectUrl();
        }

        // 2. Page d'accueil de l'utilisateur
        if (principal.getFonctionnaliteAccueil() != null) {
            return principal.getFonctionnaliteAccueil().getPageUrl();
        }

        // 3. Fallback par défaut
        return "/content/dashboard.xhtml";
    }

    private void traceAuthenticationLog(Authentication authentication) {
        LogAccesEntity log = LogAccesEntity.builder()
                .description(String.format("Connexion réussie pour l'utilisateur %s", authentication.getName()))
                .libelle("Authentification")
                .auteur(authentication.getName())
                .createdAt(LocalDateTime.now())
                .type(TypeLog.ACCES)
                .build();

        logAccesRepo.createLogAcces(log.toDomain());
    }
}