package com.itcentrex.adapter.managedBean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.tickup.adapters.config.AuthService;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@ManagedBean
@ViewScoped
@Named("connexionMB")
public class ConnexionMB implements Serializable {
    private static final long serialVersionUID = -571885223235755584L;

    @Autowired
    private AuthService authService;
    
    @Getter
    @Setter
    private String username;
    
    @Getter
    @Setter
    private String password;

    @PostConstruct
    public void init() {
        if (authService.isUserConnected()) {
            // Rediriger vers le dashboard si déjà connecté
            redirect();
            return;
        }
        this.resetValue();
    }

    private void resetValue() {
        this.username = "";
        this.password = "";
    }

    public void connexion() {
        try {
            System.out.println("username::: " + username);
            System.out.println("password::: " + password);
            
            // Appeler le service d'authentification
            boolean loginSuccess = authService.login(username, password);
            
            if (loginSuccess) {
                this.resetValue();
                System.out.println("Connexion réussie !");
                redirect();
            } else {
                System.out.println("Échec de la connexion");
            }
        } catch (Exception ex) {
            System.err.println("Erreur de connexion: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public String redirect() {
        return "/content/dashboard.xhtml?faces-redirect=true";
    }
}
