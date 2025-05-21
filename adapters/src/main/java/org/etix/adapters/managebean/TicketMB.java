package org.etix.adapters.managebean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.etix.adapters.driver.facades.CreerUnTicketFacade;
import org.etix.adapters.entities.EvenementEntity;
import org.etix.adapters.entities.TicketEntity;
import org.etix.adapters.notification.FlashMessage;
import org.etix.domain.models.enumerations.TypeTicket;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
@Getter
@Setter
public class TicketMB implements Serializable {

    private TicketEntity ticket;
    private TypeTicket typeTicket;
    private List<TypeTicket> typeTicketList = new ArrayList<>();
    private List<TicketEntity> ticketList = new ArrayList<>();
    private List<EvenementEntity> evenementList = new ArrayList<>();
    private EvenementEntity evenement;

    @Autowired
    private CreerUnTicketFacade creerUnTicketFacade;

    @PostConstruct
    public void init() {
        ticketList = new ArrayList<>();
        ticket = new TicketEntity();
        typeTicketList = TypeTicket.getTypes(); // Obtient les types de tickets disponibles (GP, VIP, VVIP)
        evenementList = new ArrayList<>();
        this.collecterLesTickets();
    }

    public void creerUnTicket() {
        try {
            ticket = creerUnTicketFacade.creerUnTicket(ticket);  // Appel à la méthode de création du tick
            ticket = new TicketEntity();
            FlashMessage.flash(FlashMessage.INFO, "Succès", "Le ticket a bien été créé.");

        } catch (Exception e) {
            e.printStackTrace();
            FlashMessage.flash(FlashMessage.ERROR, "Erreur", "Une erreur s'est produite lors de la création du ticket.");
        }
    }

    public void collecterLesTickets() {
        ticketList = creerUnTicketFacade.getAllTickets();
    }

}
