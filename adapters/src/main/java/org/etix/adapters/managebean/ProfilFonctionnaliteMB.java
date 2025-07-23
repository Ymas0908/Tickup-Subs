/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.etix.adapters.managebean;



import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.model.SelectItem;
import jakarta.faces.model.SelectItemGroup;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.etix.adapters.driver.facades.GestionSecuriteFacade;
import org.etix.adapters.entities.Security.FonctionnaliteEntity;
import org.etix.adapters.entities.Security.ProfilAccesEntity;
import org.etix.adapters.entities.Security.StrategieCompteEntity;
import org.etix.adapters.notification.FlashMessage;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@ManagedBean
@ViewScoped
@Named("profilFonctionnaliteMB")
public class ProfilFonctionnaliteMB implements Serializable {

    private static final long serialVersionUID = 2055561905397068116L;

    @Autowired
    private GestionSecuriteFacade strategieCompteService;

    @Autowired
    private GestionSecuriteFacade fonctionnaliteService;

    @Autowired
    private GestionSecuriteFacade profilService;


    @Getter
    @Setter
    private FonctionnaliteEntity[] fonctionnaliteSelected;

    @Getter
    @Setter
    private List<FonctionnaliteEntity> fonctionnaliteChoosed;

    @Getter
    @Setter
    private List<SelectItem> groupedFonctionnalites;

    @Getter
    @Setter
    private List<FonctionnaliteEntity> fonctionnalites;

    @Getter
    @Setter
    private FonctionnaliteEntity mainFonctionnalite;

    @Getter
    @Setter
    private List<ProfilAccesEntity> profils;

    @Getter
    @Setter
    private List<StrategieCompteEntity> strategieComptes;

    @Getter
    @Setter
    private StrategieCompteEntity strategieCompte;

    @Getter
    @Setter
    private List<SelectItem> countries;
    @Getter
    private ProfilAccesEntity profil;

    @PostConstruct
    public void init() {
        try {
            this.countries = new ArrayList<>();
            this.getAllStrategieComptes();
            this.getAllFonctionnalites();
            this.getAllProfiles();
            this.resetProfil();

            SelectItemGroup europeCountries = new SelectItemGroup("Gestion des utilisateurs");
            europeCountries.setSelectItems(
                    new SelectItem[]{new SelectItem(fonctionnalites.get(0), fonctionnalites.get(0).getLibelle()),
                            new SelectItem(fonctionnalites.get(1), fonctionnalites.get(1).getLibelle()),
                            new SelectItem(fonctionnalites.get(2), fonctionnalites.get(2).getLibelle())});
            SelectItemGroup afriqCountries = new SelectItemGroup("Gestion des demandes de credit");
            afriqCountries.setSelectItems(
                    new SelectItem[]{new SelectItem(fonctionnalites.get(4), fonctionnalites.get(4).getLibelle()),
                            new SelectItem(fonctionnalites.get(10), fonctionnalites.get(10).getLibelle()),
                            new SelectItem(fonctionnalites.get(8), fonctionnalites.get(8).getLibelle()),
                            new SelectItem(fonctionnalites.get(7), fonctionnalites.get(7).getLibelle())});
            countries.add(europeCountries);
            countries.add(afriqCountries);

        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
        }
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
            PrimeFaces.current().ajax().update("strategieCompteForm:strategieCompteContent");
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
        }
    }

    /**
     * Créer une nouvelle stratégie de compte
     */
    public void saveStrategieCompte() {
        try {
            strategieCompteService.saveStrategieCompte(strategieCompte);
            strategieComptes.add(0, strategieCompte);
            PrimeFaces.current().executeScript("PF('strategieCompteDlg').hide();");
            FlashMessage.flash(FlashMessage.INFO, "Succès !", "Stratégie de compte enregistrée avec succès.");
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
        }
    }

    /**
     *
     */
    public void saveProfil() {
        try {
            profilService.saveProfil(profil);
            //this.addItem(profils, profil);
            this.resetProfil();
            FlashMessage.flash(FlashMessage.INFO, "Succès !", "Profil enregistrée avec succès.");
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
        }
    }

    public void deleteProfil() {
        try {
            profilService.deleteProfil(profil);
            //	this.removeItem(profils, profil);
            this.resetProfil();
            FlashMessage.flash(FlashMessage.INFO, "Succès !", "Profil supprimée avec succès.");
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
        }
    }

    public void setupProfil() {
        try {
            if (Objects.isNull(profil.getId())) {
                FlashMessage.flash(FlashMessage.INFO, "Info !", "Aucun profil à modifier selectionné.");
                return;
            }

            //profil.setFonctionnalites(Sets.newHashSet(fonctionnaliteSelected));
            //profilService.saveProfil(profil);
            //this.addItem(profils, profil);
            FlashMessage.flash(FlashMessage.INFO, "Succès !", "Profil enregistrée avec succès.");
        } catch (Exception e) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", e.getMessage());
            e.printStackTrace();
        }
    }


    public void setProfil(ProfilAccesEntity profil) {
        if (profil != null && profil.getId() != null && profil.getFonctionnalites() != null) {
            if (this.profil.getId() == null || !profil.equals(this.profil)) {
                this.fonctionnaliteSelected = new FonctionnaliteEntity[profil.getFonctionnalites().size()];
                this.fonctionnaliteSelected = (FonctionnaliteEntity[]) profil.getFonctionnalites()
                        .toArray(this.fonctionnaliteSelected);
            }
        } else {
            profil = new ProfilAccesEntity();
        }
        //this.profil = profil;
    }

    public void resetProfil() {
        this.mainFonctionnalite = new FonctionnaliteEntity();
        this.fonctionnaliteSelected = null;
        //this.profil = new ProfilAcces();
    }


    /**
     *
     */
    public void cocherTousFonctionnalites() {
        this.fonctionnaliteSelected = new FonctionnaliteEntity[fonctionnalites.size()];
        this.fonctionnaliteSelected = (FonctionnaliteEntity[]) fonctionnalites.toArray(this.fonctionnaliteSelected);
        PrimeFaces.current().ajax().update("privilegeForm:privilegeContent");
    }

    /**
     *
     */
    public void deCocherTousFonctionnalites() {
        this.fonctionnaliteSelected = null;
        PrimeFaces.current().ajax().update("privilegeForm:privilegeContent");
    }

    public void removeItem(List<ProfilAccesEntity> elements, ProfilAccesEntity element) {
        elements.removeIf(item -> item.equals(element));
    }

    public void addItem(List<ProfilAccesEntity> elements, ProfilAccesEntity element) {
        int itemIndex = elements.indexOf(element);
        if (itemIndex < 0) {
            elements.add(element);
        } else {
            elements.set(itemIndex, element);
        }
    }

    /**
     *
     */
    public void submitSelectedCompteFonctionnalite() {
        try {


            if (selectionEmpty()) {
                FlashMessage.flash(FlashMessage.WARN, "Erreur survenue !",
                        "Vous devez choisir au moins une fonctionnalité.");
                return;
            }
            this.fonctionnaliteChoosed = Arrays.asList(this.fonctionnaliteSelected);
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
        return null != fonctionnaliteSelected && fonctionnaliteSelected.length == fonctionnalites.size();
    }

    /**
     * Verifier qu'aucune des fonctionnalitées n'est cochées
     *
     * @return
     */
    public boolean selectionEmpty() {
        return null == fonctionnaliteSelected || fonctionnaliteSelected.length == 0;
    }

}
