package org.etix.adapters.managebean;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.etix.adapters.config.Security.AuthenticationService;
import org.etix.adapters.notification.FlashMessage;
import org.etix.adapters.session.ApplicationSession;
import org.etix.adapters.session.SessionConstant;
import org.etix.adapters.utils.Request;
import org.etix.domain.models.Security.Principal;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Objects;

@ManagedBean
@ViewScoped
@Named("connexionMB")
public class ConnexionMB implements Serializable {
    private static final long serialVersionUID = -571885223235755584L;

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private ApplicationSession applicationSession;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;


    @PostConstruct
    public void init() {

        if (authenticationService.isAuthenticated()) {

            String defPage = "/content/dashboard.xhtml";
            Principal principal = authenticationService.getPrincipal();
            if (!Objects.isNull(principal.getId())) {
                applicationSession.put(SessionConstant.USER_CONNECTED, principal);
            }
            Request.redirect(defPage);

            return;
        }
        this.resetValue();
    }

    private void resetValue() {
        this.username = "";
        this.password = "";
    }

    /**
     *
     */
    public void connexion() {
        try {
            System.out.println("username::: " + username);
            System.out.println("password::: " + password);
            authenticationService.login(username, password);
            this.resetValue();
            FlashMessage.flash(FlashMessage.INFO, "Succès !", "Connexion réussie.");
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
        }
    }

    public String redirect() {
        return "/content/dashboard.xhtml?faces-redirect=true";
    }

}