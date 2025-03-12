package org.etix.adapters.managebean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.etix.adapters.driver.facades.CreerUnEvenementFacade;
import org.etix.adapters.entities.EvenementEntity;
import org.etix.adapters.notification.FlashMessage;
//import org.etix.domain.models.enumerations.TypeEvenement;
import org.etix.domain.models.enumerations.TypeEvenement;
import org.etix.domain.models.enumerations.TypeTicket;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@Getter
@Setter
public class CreerEvenementMB implements Serializable {


    @Autowired
    private CreerUnEvenementFacade creerUnEvenementFacade;

    private List<EvenementEntity> evenementsList = new ArrayList<>();
    private EvenementEntity evenement;
    private TypeTicket typeTicket;
    private List<TypeTicket> typeTicketList = new ArrayList<>();
    private TypeEvenement typeEvenement;
    private List<TypeEvenement> typeEvenementList = new ArrayList<>();




    @PostConstruct
    public void init() {
        evenement = new EvenementEntity();
        typeTicketList = TypeTicket.getTypes();
        evenementsList = new ArrayList<>();
        typeEvenementList = TypeEvenement.getTypes();
        this.collecterLesEvenements();
    }


    public void creerUnEvenement() {
        try {
            creerUnEvenementFacade.creerUnEvenement(evenement);
            resetCreerUnEvenement();
            System.out.println("Evenement créé" + evenement);
            FlashMessage.flash(FlashMessage.INFO, "Succès", "L'evenement à bien été crée.");
            this.collecterLesEvenements();
        } catch (Exception e) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur", "Une erreur s'est produite.");
            throw new RuntimeException(e);
        }
    }

    public void modifierUnEvenement() {
        try {
            creerUnEvenementFacade.modifierUnEvenement(evenement);
            System.out.println("Evenement modifié" + evenement);
            PrimeFaces.current().ajax().update("formEvenement");
            FlashMessage.flash(FlashMessage.INFO, "Succès", "L'evenement à bien été modifié.");
            this.collecterLesEvenements();
        } catch (Exception e) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur", "Une erreur s'est produite.");
            throw new RuntimeException(e);
        }
    }


    public void supprimerUnEvenement() {
      try {
          creerUnEvenementFacade.supprimerUnEvenement(evenement);
          this.collecterLesEvenements();
          PrimeFaces.current().ajax().update("formEvenement");
          System.out.println("Evenement supprimé" + evenement.getNom());
          FlashMessage.flash(FlashMessage.INFO, "Succès", "L'evenement à bien été supprimé.");

      } catch (Exception e) {
          FlashMessage.flash(FlashMessage.ERROR, "Erreur", "Une erreur s'est produite.");
      }
    }
    public void collecterLesEvenements() {
        evenementsList = new ArrayList<>();
        evenementsList = creerUnEvenementFacade.getAllEvenements();

    }

    public void resetCreerUnEvenement() {
        evenement = new EvenementEntity();
    }



}
