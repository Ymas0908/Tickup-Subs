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
import org.etix.adapters.entities.Security.ProfilAccesEntity;
import org.etix.adapters.entities.Security.UtilisateurEntity;
import org.etix.adapters.notification.FlashMessage;
import org.etix.adapters.driver.facades.GestionSecuriteFacade;
import org.etix.adapters.session.ApplicationSession;
import org.etix.adapters.session.SessionConstant;
import org.etix.adapters.utils.ListUtils;
import org.etix.domain.models.Security.Principal;
import org.etix.domain.models.Security.ProfilAccesSummary;
import org.etix.domain.models.Security.UtilisateurSlim;
import org.etix.domain.models.enumerations.TypeLog;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@ManagedBean
@ViewScoped
@Named("utilisateurMB")
public class UtilisateurMB implements Serializable {

    private static final long serialVersionUID = 2055561905397068116L;

    @Autowired
    private GestionSecuriteFacade gestionSecuriteFacade;
    @Autowired
    private ApplicationSession applicationSession;
    @Autowired
    private AuthenticationService authenticationService;

    @Getter
    @Setter
    private List<UtilisateurSlim> superieurHierachiques;

    @Getter
    @Setter
    private List<ProfilAccesEntity> profils;

    @Getter
    @Setter
    private List<UtilisateurEntity> utilisateurs;


    @Getter
    @Setter
    private Integer[] profilSelectedId;

    @Getter
    @Setter
    private UtilisateurEntity utilisateur;
    private Principal principal;

    @PostConstruct
    public void init() {
        try {
            this.getAllUtilisateurs();
            // System.out.println(utilisateurs.get(0).toString());
            this.profils = gestionSecuriteFacade.getActiveProfils();
            principal = authenticationService.getPrincipal();
            this.resetUtilisateur();
        } catch (Exception e) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", e.getMessage());
        }
    }

    public UtilisateurEntity getUtilisateurConnected() {
        if (!Objects.isNull(principal.getId())) {
            applicationSession.put(SessionConstant.USER_CONNECTED, principal);
            principal = authenticationService.getPrincipal();
            utilisateur = gestionSecuriteFacade.getUtilisateurById(principal.getId());

        }
        return utilisateur;
    }

    /**
     * Editer un utilisateur (nouveau | existant)
     */
    public void saveUtilisateur() {
        try {

            UtilisateurEntity newUtilisateur = gestionSecuriteFacade.saveUtilisateur(this.utilisateur);
            gestionSecuriteFacade.traceLog(TypeLog.INFO, "Edition d'utilisateur", "Création | Modification d'un utilisateur", principal.getLogin());

            ListUtils.addItem(this.utilisateurs, newUtilisateur);
            if (utilisateur.getId() != null) {
                FlashMessage.flash(FlashMessage.INFO, "Succès !", "Utilisateur modifié avec succès.");

            } else {
                FlashMessage.flash(FlashMessage.INFO, "Succès !", "Utilisateur enregistré avec succès. Merci de vous connecter à votre adresse mail pour avoir vos paramètres de connexion ");
            }
            this.getAllUtilisateurs();
            this.resetUtilisateur();
            PrimeFaces.current().executeScript("PF('editUtilisateurSidebar').hide();");

        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
        }

    }

    private void getAllUtilisateurs() {
        this.utilisateurs = gestionSecuriteFacade.getAllUtilisateurs();

    }

    /**
     * Supprimer un utilisateur (existant)
     */
    public void deleteUtilisateur() {
        try {
            this.utilisateur = gestionSecuriteFacade.deleteUtilisateur(utilisateur);
            gestionSecuriteFacade.traceLog(TypeLog.INFO, "Suppression d'utilisateur", "Suppression de l'utilisateur " + utilisateur.getLogin(), principal.getLogin());

            ListUtils.addItem(this.utilisateurs, this.utilisateur);
            this.getAllUtilisateurs();
            this.resetUtilisateur();
            FlashMessage.flash(FlashMessage.INFO, "Succès !", "Utilisateur suspendu avec succès.");
            PrimeFaces.current().executeScript("PF('editUtilisateurSidebar').hide();");
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
        }
    }


    /**
     * Rechercher tous mes profils d'accès d'un utilisateur.
     */
    public void getUtilisateurProfilAcces() {
        List<ProfilAccesSummary> utilisateurProfils = gestionSecuriteFacade
                .getProfilAccesSummaryByUtilisateurId(utilisateur.getId());
        this.profilSelectedId = new Integer[utilisateurProfils.size()];
        this.profilSelectedId = (Integer[]) utilisateurProfils.stream()
                .map(item -> item.getId())
                .toArray(size -> new Integer[size]);
    }


    /**
     * Mettre à jour les profils d'accès d'un utilisateur.
     */
    public void setupProfilAccess() {
        try {
            if (null == utilisateur.getId()) {
                FlashMessage.flash(FlashMessage.INFO, "Info !", "Aucun utilisateur à modifier selectionné.");
                return;
            }
            this.utilisateur = gestionSecuriteFacade.updateProfilAccess(utilisateur, profilSelectedId);
            gestionSecuriteFacade.traceLog(TypeLog.INFO, "Rattachement de profil utilisateur", "Rattachement d'un utilisateur à un profil d'accès", principal.getLogin());

            ListUtils.addItem(this.utilisateurs, this.utilisateur);
            FlashMessage.flash(FlashMessage.INFO, "Succès !", "Profil(s) utilisateur enregistré(s) avec succès.");
            PrimeFaces.current().executeScript("PF('editUtilisateurSidebar').hide();");
        } catch (Exception e) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Réinitialiser le formulaire d'edition d'un utilisateur.
     */
    public void resetUtilisateur() {
        this.profilSelectedId = null;
        this.utilisateur = new UtilisateurEntity();
        this.superieurHierachiques = new ArrayList<>();
    }

    /**
     * Ouvrir l'interface de creation d'un nouvel utilisateur.
     */
    public void openNewUtilisateur() {
        try {
            this.resetUtilisateur();
            PrimeFaces.current().ajax().update("utilisateurEditForm:editUtilisateurContent");
            PrimeFaces.current().executeScript("PF('editUtilisateurSidebar').show();");
        } catch (Exception e) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Ouvrir l'interface d'edition d'un utilisateur existant.
     */
    public void openEditUtilisateur() {
        try {

            this.getUtilisateurProfilAcces();
//            this.getUtilisateursByAgence();
            PrimeFaces.current().ajax().update("utilisateurEditForm:editUtilisateurContent");
            PrimeFaces.current().executeScript("PF('editUtilisateurSidebar').show();");
        } catch (Exception e) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", e.getMessage());
            e.printStackTrace();
        }
    }
}
