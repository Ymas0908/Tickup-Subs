package org.etix.adapters.managebean;



import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.etix.adapters.config.Security.AuthenticationService;
import org.etix.adapters.notification.FlashMessage;
import org.etix.adapters.utils.Request;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

;

@ViewScoped
@ManagedBean("passwordRecoveryMB")
public class PasswordRecoveryMB implements Serializable {

    private static final long serialVersionUID = -2872063531734752121L;

    @Autowired
    private AuthenticationService authenticationService;

    @Getter
    @Setter
    private String email;

    @PostConstruct
    public void init() {
        try {
            if (authenticationService.isAuthenticated()) {
                //  System.out.println("authenticationService: " + authenticationService.getPrincipal());
                Request.redirect("/content/dashboard.xhtml");
                return;
            }
            this.reset();
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void reset() {
        this.email = null;
    }


    public void recovery() {
        try {
            if (authenticationService.initPasswordResetRequest(email)) {
                FlashMessage.flash(FlashMessage.INFO, "Succès", "Les instructions de changement de votre mot de passe ont été envoyées à l'adresse mail.");
                return;
            }
            this.reset();
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
        }
    }
}
