/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.etix.adapters.managebean;



import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.etix.adapters.config.Security.AuthenticationService;
import org.etix.adapters.config.Security.UtilisateurDto;
import org.etix.adapters.entities.Security.StrategieCompteEntity;
import org.etix.adapters.notification.FlashMessage;
import org.etix.adapters.driver.facades.GestionSecuriteFacade;
import org.etix.adapters.utils.ListUtils;
import org.etix.domain.models.enumerations.TypeLog;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;


@ManagedBean
@ViewScoped
@Named("strategieCompteMB")
public class StrategieCompteMB implements Serializable {

    private static final long serialVersionUID = 2055561905397068116L;

    @Autowired
    private GestionSecuriteFacade strategieCompteService;

    @Getter
    @Setter
    private List<StrategieCompteEntity> strategieComptes;

    @Getter
    @Setter
    private StrategieCompteEntity strategieCompte;

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
            this.getAllStrategieComptes();
            this.resetStrategieCompte();
            this.principal = authenticationService.getPrincipal();
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void getAllStrategieComptes() {
        this.strategieComptes = strategieCompteService.getStrategieComptes();
    }

    /**
     *
     */
    public void initStrategieCompte() {
        try {
            PrimeFaces.current().executeScript("PF('strategieCompteDlg').show();");
            PrimeFaces.current().ajax().update("strategieCompteForm:strategieCompteContent");
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
        }
    }

    /**
     *
     */
    public void initFonctionnaliteSetup() {
        try {
            this.strategieCompte = new StrategieCompteEntity();
            PrimeFaces.current().executeScript("PF('privilegeDlg').show();");
            PrimeFaces.current().ajax().update("privilegeForm:privilegeContent");
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
        }
    }

    /**
     *
     */
    public void resetStrategieCompte() {
        try {
            this.strategieCompte = new StrategieCompteEntity();
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
        }
    }

    /**
     * Créer une nouvelle stratégie de compte
     */
    public void saveStrategieCompte() {
        try {
            strategieCompte = strategieCompteService.saveStrategieCompte(strategieCompte);
            gestionSecuriteFacade.traceLog(TypeLog.INFO, "Edition de stratégie de compte", "Création | Modification de strategie de compte", principal.getLogin());

            ListUtils.addItem(strategieComptes, strategieCompte);
            this.getAllStrategieComptes();
            this.resetStrategieCompte();
            FlashMessage.flash(FlashMessage.INFO, "Succès !", "Stratégie de compte enregistrée avec succès.");
        } catch (Exception ex) {
            ex.printStackTrace();
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
        }
    }


    public void deleteStrategieCompte() {
        try {
            strategieCompteService.deleteStrategieCompte(strategieCompte);
            gestionSecuriteFacade.traceLog(TypeLog.INFO, "Suppression de strategie de compte", "Suppression de strategie de compte", principal.getLogin());

            ListUtils.removeItem(strategieComptes, strategieCompte);
            this.getAllStrategieComptes();
            this.resetStrategieCompte();
            FlashMessage.flash(FlashMessage.INFO, "Succès !", "Strategie de compte supprimée avec succès.");
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
        }
    }

}
