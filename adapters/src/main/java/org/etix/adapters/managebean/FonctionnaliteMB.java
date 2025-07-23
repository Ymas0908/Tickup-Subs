package org.etix.adapters.managebean;



import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.etix.adapters.config.Security.AuthenticationService;
import org.etix.adapters.config.Security.UtilisateurDto;
import org.etix.adapters.entities.Security.FonctionnaliteEntity;
import org.etix.adapters.notification.FlashMessage;
import org.etix.adapters.driver.facades.GestionSecuriteFacade;
import org.etix.domain.models.enumerations.TypeLog;
import org.etix.domain.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
@Named("fonctionnaliteMB")
public class FonctionnaliteMB implements Serializable {

    private static final long serialVersionUID = 2055561905397068116L;

    @Autowired
    private GestionSecuriteFacade fonctionnaliteService;

    @Getter
    @Setter
    private List<FonctionnaliteEntity> fonctionnalites;

    @Getter
    @Setter
    private FonctionnaliteEntity fonctionnalite;

    @Getter
    @Setter
    private UtilisateurDto principal;

    @Autowired
    private GestionSecuriteFacade gestionSecuriteFacade;

    @Autowired
    private AuthenticationService authenticationService;

    @PostConstruct
    public void init() {
        try {
            this.getAllFonctionnalites();
            this.resetFonctionnalite();
            this.principal = authenticationService.getPrincipal();
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void saveFonctionnalite() {
        try {
            this.fonctionnalite = fonctionnaliteService.saveFonctionnalite(fonctionnalite);
            gestionSecuriteFacade.traceLog(TypeLog.INFO, "Edition fonctionnalité", "Création | Modification de fonctionnalité", principal.getLogin());

            ListUtils.addItem(fonctionnalites, fonctionnalite);
            this.getAllFonctionnalites();
            this.resetFonctionnalite();
            FlashMessage.flash(FlashMessage.INFO, "Succès !", "Fonctionnalité enregistrée avec succès.");
        } catch (Exception ex) {
            ex.printStackTrace();
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
        }
    }

    private void getAllFonctionnalites() {
        this.fonctionnalites = fonctionnaliteService.getFonctionnalites();
    }

    public void deleteFonctionnalite() {
        try {
            fonctionnaliteService.deleteFonctionnalite(fonctionnalite);
            gestionSecuriteFacade.traceLog(TypeLog.INFO, "Suppression de fonctionnalité", "Suppression de la fonctionnalité " + fonctionnalite.getLibelle(), principal.getLogin());

            ListUtils.removeItem(fonctionnalites, fonctionnalite);
            this.getAllFonctionnalites();
            this.resetFonctionnalite();
            FlashMessage.flash(FlashMessage.INFO, "Succès !", "Fonctionnalité supprimée avec succès.");
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
        }
    }

    public void resetFonctionnalite() {
        this.fonctionnalite = new FonctionnaliteEntity();
    }
}
