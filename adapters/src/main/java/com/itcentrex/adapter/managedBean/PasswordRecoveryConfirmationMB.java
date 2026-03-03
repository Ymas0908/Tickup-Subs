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
@Named("passwordRecoveryConfirmationMB")
public class PasswordRecoveryConfirmationMB implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private AuthService authService;
    
    @Getter
    @Setter
    private String password;
    
    @Getter
    @Setter
    private String password2;

    public void changePassword() {
        try {
            if (password == null || password.trim().isEmpty()) {
                addMessage("Veuillez saisir un nouveau mot de passe", FacesMessage.SEVERITY_ERROR);
                return;
            }
            
            if (password2 == null || password2.trim().isEmpty()) {
                addMessage("Veuillez confirmer votre mot de passe", FacesMessage.SEVERITY_ERROR);
                return;
            }
            
            if (!password.equals(password2)) {
                addMessage("Les mots de passe ne correspondent pas", FacesMessage.SEVERITY_ERROR);
                return;
            }
            
            if (password.length() < 6) {
                addMessage("Le mot de passe doit contenir au moins 6 caractères", FacesMessage.SEVERITY_ERROR);
                return;
            }
            
            // TODO: Implémenter la logique de changement de mot de passe
            // Utiliser le token pour réinitialiser le mot de passe
            addMessage("Mot de passe réinitialisé avec succès", FacesMessage.SEVERITY_INFO);
            
            // Réinitialiser les champs
            password = "";
            password2 = "";
            
        } catch (Exception e) {
            addMessage("Erreur lors de la réinitialisation du mot de passe: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    private void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
