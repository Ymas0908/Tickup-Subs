package org.etix.adapters.managebean;



import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.etix.adapters.config.Security.AuthenticationService;
import org.etix.adapters.notification.FlashMessage;
import org.etix.adapters.session.ApplicationSession;
import org.etix.adapters.session.SessionConstant;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@ViewScoped
@ManagedBean("passwordRecoveryConfirmationMB")
public class PasswordRecoveryConfirmationMB implements Serializable {

    private static final long serialVersionUID = -2872063531734752121L;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ApplicationSession applicationSession;

    @Getter
    @Setter
    private boolean linkExpired;

    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String password2;

    @PostConstruct
    public void init() {
        try {
            this.token = (String) applicationSession.get(SessionConstant.PASSWORD_RESET_TOKEN);
            this.linkExpired = (boolean) applicationSession.get(SessionConstant.PASSWORD_RESET_LINK);
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void reset() {
        this.token = null;
        this.password = null;
        this.password2 = null;
    }


    public void changePassword() {

        try {
            authenticationService.confirmPasswordResetRequest(password, password2, token);
            this.reset();
            FlashMessage.flash(FlashMessage.INFO, "Succès", "Mot de passe changé avec succès");
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
        }
    }

    public String goBack() {
        return "/auth/connexion.xhtml?faces-redirect=true";
    }

}
