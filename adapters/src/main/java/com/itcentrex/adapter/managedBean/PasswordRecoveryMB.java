package com.itcentrex.adapter.managedBean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.tickup.adapters.config.AuthService;

import jakarta.annotation.ManagedBean;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@ManagedBean
@ViewScoped
@Named("passwordRecoveryMB")
public class PasswordRecoveryMB implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private AuthService authService;
    
    @Getter
    @Setter
    private String email;

    public void recovery() {
        try {
            if (email == null || email.trim().isEmpty()) {
                addMessage("Veuillez saisir votre adresse email", FacesMessage.SEVERITY_ERROR);
                return;
            }
            
            // TODO: Implémenter la logique de récupération de mot de passe
            // Envoyer un email avec un token de réinitialisation
            addMessage("Un email de réinitialisation a été envoyé à " + email, FacesMessage.SEVERITY_INFO);
            
            // Réinitialiser le champ
            email = "";
            
        } catch (Exception e) {
            addMessage("Erreur lors de la récupération du mot de passe: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    private void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
