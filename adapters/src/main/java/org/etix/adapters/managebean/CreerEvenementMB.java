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
import org.etix.adapters.utils.ListUtils;
import org.etix.domain.models.enumerations.TypeEvenement;
import org.etix.domain.models.enumerations.TypeTicket;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.LocalDateTime;
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
            if (evenement.getDateEvenement().isBefore(LocalDateTime.now())) {
                FlashMessage.flash(FlashMessage.ERROR, "Erreur", "Impossible de créer un événement dans le passé.");
                return;
            }

            creerUnEvenementFacade.creerUnEvenement(evenement);

            ListUtils.addItem(new ArrayList<>(evenementsList), evenement);
            resetCreerUnEvenement();
            System.out.println("Événement créé : " + evenement);
            FlashMessage.flash(FlashMessage.INFO, "Succès", "L'événement a bien été créé.");
            this.collecterLesEvenements();
        } catch (Exception e) {
            e.printStackTrace();
            FlashMessage.flash(FlashMessage.ERROR, "Erreur", "Une erreur s'est produite : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void modifierUnEvenement() {
        try {
            creerUnEvenementFacade.modifierUnEvenement(evenement);
            System.out.println("Evenement modifié" + evenement);
            PrimeFaces.current().ajax().update("formEvenement");
            FlashMessage.flash(FlashMessage.INFO, "Succès", "L'évenèment à bien été modifié.");
            this.collecterLesEvenements();
        } catch (Exception e) {
            e.printStackTrace();
            FlashMessage.flash(FlashMessage.ERROR, "Erreur", "Une erreur s'est produite."+e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public void supprimerUnEvenement() {
      try {
          creerUnEvenementFacade.deleteEvenement(evenement.getId());
          System.out.println("Evenement supprimé" + evenement.getNom());
          FlashMessage.flash(FlashMessage.INFO, "Succès", "L'évènement à bien été supprimé.");
          PrimeFaces.current().ajax().update("formEvenement");
          this.collecterLesEvenements();
      } catch (Exception e) {
          e.printStackTrace();
          FlashMessage.flash(FlashMessage.ERROR, "Erreur", "Une erreur s'est produite." + e.getMessage());
      }
    }
    public void collecterLesEvenements() {
        evenementsList = new ArrayList<>();
        evenementsList = creerUnEvenementFacade.getAllEvenements();

    }

    public void resetCreerUnEvenement() {
        evenement = new EvenementEntity();
    }

    public int countByStatus(String status) {
        return (int) evenementsList.stream()
                .filter(e -> e.getStatutEvenement().name().equals(status))
                .count();
    }

    public void refreshStats() {
        this.collecterLesEvenements();
    }



}
