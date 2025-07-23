package org.etix.adapters.managebean;



import jakarta.annotation.ManagedBean;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.etix.adapters.config.Security.AuthenticationService;
import org.etix.adapters.notification.FlashMessage;
import org.etix.adapters.utils.Request;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@ViewScoped
@ManagedBean
@Named(value = "changePasswordMB")
public class ChangePasswordMB implements Serializable {

    private static final long serialVersionUID = 3873491051584538395L;

    @Autowired
    private AuthenticationService authenticationService;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String newPassword;

    @Getter
    @Setter
    private String passwordConfirmed;

    public void change() {
        try {
            if (authenticationService.updatePassword(password, newPassword, passwordConfirmed)) {
                PrimeFaces.current().executeScript("PF('passwordUpdatedDlg').show()");
                this.initialisation();
            }
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     *
     */
    public void initialisation() {
        this.password = null;
        this.newPassword = null;
        this.passwordConfirmed = null;
    }


    public void logout() {
        Request.redirect("/logout");
        return;
    }
}
