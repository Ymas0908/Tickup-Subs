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
import org.etix.adapters.entities.Security.FonctionnaliteEntity;
import org.etix.adapters.entities.Security.ProfilAccesEntity;
import org.etix.adapters.entities.Security.StrategieCompteEntity;
import org.etix.adapters.notification.FlashMessage;
import org.etix.adapters.driver.facades.GestionSecuriteFacade;
import org.etix.adapters.utils.ListUtils;
import org.etix.domain.models.enumerations.TypeLog;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


@ManagedBean
@ViewScoped
@Named("profilAccessMB")
public class ProfilAccessMB implements Serializable {

    private static final long serialVersionUID = 2055561905397068116L;

    @Autowired
    private GestionSecuriteFacade strategieCompteService;

    @Autowired
    private GestionSecuriteFacade fonctionnaliteService;

    @Autowired
    private GestionSecuriteFacade profilService;

    /*
     * @Getter
     *
     * @Setter private Fonctionnalite[] fonctionnaliteSelected;
     */
    @Getter
    @Setter
    private Set<FonctionnaliteEntity> fonctionnaliteChoosed;

    @Getter
    @Setter
    private Integer[] fonctionnaliteSelectedIds;

    @Getter
    @Setter
    private List<FonctionnaliteEntity> fonctionnalites;

    @Getter
    @Setter
    private FonctionnaliteEntity mainFonctionnalite;

    @Getter
    @Setter
    private List<StrategieCompteEntity> strategieComptes;

    @Getter
    @Setter
    private StrategieCompteEntity strategieCompte;

    @Getter
    @Setter
    private Integer mainFonctionnaliteId;

    @Getter
    @Setter
    private List<ProfilAccesEntity> profils;

    @Getter
    private ProfilAccesEntity profil;

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
            this.getAllFonctionnalites();
            this.getAllProfiles();
            this.resetProfilData();
            this.principal = authenticationService.getPrincipal();
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     *
     */
    public void initStrategieCompte() {
        try {
            this.strategieCompte = new StrategieCompteEntity();
            PrimeFaces.current().executeScript("PF('strategieCompteDlg').show();");
            PrimeFaces.current().ajax().update("strategieCompteForm:strategieCompteContent");
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
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
            ex.printStackTrace();
        }
    }

    /**
     *
     */
    public void resetStrategieCompte() {
        try {
            this.strategieCompte = new StrategieCompteEntity();
            PrimeFaces.current().ajax().update("strategieCompteForm:strategieCompteContent");
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Créer une nouvelle stratégie de compte
     */
    public void saveStrategieCompte() {
        try {
            strategieCompte = strategieCompteService.saveStrategieCompte(strategieCompte);
            gestionSecuriteFacade.traceLog(TypeLog.INFO, "Création de Strategie de compte", "Création de Strategie de compte", principal.getLogin());
            ListUtils.addItem(new ArrayList<>(strategieComptes), strategieCompte);
            PrimeFaces.current().executeScript("PF('strategieCompteDlg').hide();");
            FlashMessage.flash(FlashMessage.INFO, "Succès !", "Stratégie de compte enregistrée avec succès.");
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     *
     */
    public void saveProfil() {
        try {
            profil = profilService.saveProfil(profil);
            gestionSecuriteFacade.traceLog(TypeLog.INFO, "Edition de profil d'accès", "Création | Modification de profil d'accès", principal.getLogin());

            ListUtils.addItem(new ArrayList<>(profils), profil);
            this.getAllProfiles();
            this.resetProfil();
            FlashMessage.flash(FlashMessage.INFO, "Succès !", "Profil enregistré avec succès.");
        } catch (Exception ex) {
            ex.printStackTrace();
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
        }
    }

    public void deleteProfil() {
        try {
            profilService.deleteProfil(profil);
            gestionSecuriteFacade.traceLog(TypeLog.INFO, "Suppression de profil d'accès", "Suppression de profil d'accès" + profil.getLibelle(), principal.getLogin());

            ListUtils.removeItem(profils, profil);
            this.getAllProfiles();
            this.resetProfil();
            FlashMessage.flash(FlashMessage.INFO, "Succès !", "Profil supprimé avec succès.");
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void setupProfil() {
        try {
            if (profil == null || profil.getId() == null) {
                FlashMessage.flash(FlashMessage.INFO, "Info !", "Aucun profil à modifier selectionné.");
                return;
            }

            FonctionnaliteEntity fonctionnaliteAccueil = null;
            Set<FonctionnaliteEntity> fonctionnaliteHashSet = getSelectedFonctionnalites(fonctionnaliteSelectedIds);
            if (mainFonctionnaliteId != null && !fonctionnaliteHashSet.isEmpty()) {
                fonctionnaliteAccueil = fonctionnaliteHashSet.stream()
                        .filter(item -> Objects.equals(mainFonctionnaliteId, item.getId())).findFirst()
                        .orElseThrow();
            }
            profil.setFonctionnalites(fonctionnaliteHashSet);
            profil.setFonctionnaliteAccueil(fonctionnaliteAccueil);
            profil = profilService.saveProfil(profil);

            ListUtils.addItem(profils, profil);
            FlashMessage.flash(FlashMessage.INFO, "Succès !", "Profil enregistré avec succès.");
            PrimeFaces.current().executeScript("PF('mainPrivilegeDlg').hide();");
        } catch (Exception e) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", e.getMessage());
            e.printStackTrace();
        }
    }

    public void setProfil(ProfilAccesEntity profil) {

        if (profil != null) {
            if (this.profil.getId() == null || !profil.equals(this.profil)) {
                if (profil.getFonctionnalites() != null) {

                    this.fonctionnaliteSelectedIds = new Integer[profil.getFonctionnalites().size()];
                    this.fonctionnaliteSelectedIds = profil.getFonctionnalites().stream()
                            .map(fonctionnalite -> fonctionnalite.getId()).toArray(Integer[]::new);

                    if (profil.getFonctionnaliteAccueil() != null) {
                        this.mainFonctionnaliteId = profil.getFonctionnaliteAccueil().getId();
                    }
                } else {
                    this.fonctionnaliteSelectedIds = null;
                    this.mainFonctionnaliteId = null;
                }
            }
        } else {
            this.resetProfil();
        }
        this.profil = profil;
    }

    /**
     *
     */
    public void resetProfilData() {
        this.fonctionnaliteSelectedIds = null;
        this.mainFonctionnaliteId = null;
        this.resetProfil();
    }

    public void resetProfil() {
        this.profil = new ProfilAccesEntity();
    }

    /**
     *
     */
    public void cocherTousFonctionnalites() {
        this.fonctionnaliteSelectedIds = fonctionnalites.stream()
                .map(fonctionnalite -> fonctionnalite.getId()).toArray(Integer[]::new);
        PrimeFaces.current().ajax().update("privilegeForm:privilegeContent");
    }

    /**
     *
     */
    public void deCocherTousFonctionnalites() {
        this.fonctionnaliteSelectedIds = null;
        PrimeFaces.current().ajax().update("privilegeForm:privilegeContent");
    }

    /**
     *
     */
    public void submitSelectedCompteFonctionnalite() {
        try {
            if (selectionEmpty()) {
                FlashMessage.flash(FlashMessage.WARN, "Erreur survenue !", "Vous devez choisir au moins une fonctionnalité.");
                return;
            }
            this.fonctionnaliteChoosed = getSelectedFonctionnalites(this.fonctionnaliteSelectedIds);
            gestionSecuriteFacade.traceLog(TypeLog.INFO, "Rattachement de fonctionnalités", "Ratachement de fonctionnalités au profil " + profil.getLibelle(), principal.getLogin());
            PrimeFaces.current().executeScript("PF('mainPrivilegeDlg').show();");
            PrimeFaces.current().ajax().update("privilegeForm:mainPrivilegeContent");
        } catch (Exception e) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Verifier que toutes les fonctionnalitées sont cochées
     *
     * @return
     */
    public boolean selectionComplete() {
        return null != fonctionnaliteSelectedIds && fonctionnaliteSelectedIds.length == fonctionnalites.size();
    }

    /**
     * Verifier qu'aucune des fonctionnalitées n'est cochées
     *
     * @return
     */
    public boolean selectionEmpty() {
        return null == fonctionnaliteSelectedIds || fonctionnaliteSelectedIds.length == 0;
    }

    private void getAllStrategieComptes() {
        this.strategieComptes = strategieCompteService.getStrategieComptes();
    }

    private void getAllFonctionnalites() {
        this.fonctionnalites = fonctionnaliteService.getFonctionnalites();
    }

    private void getAllProfiles() {
        this.profils = profilService.getProfils();
    }

    private Set<FonctionnaliteEntity> getSelectedFonctionnalites(Integer[] selectionIds) {
        List<Integer> selected = Arrays.asList(selectionIds);
        return fonctionnalites.stream().filter(item -> selected.contains(item.getId())).collect(Collectors.toSet());
    }

}
