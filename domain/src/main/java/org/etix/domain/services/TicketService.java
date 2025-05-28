package org.etix.domain.services;

import org.etix.domain.ddd.DomaineService;
import org.etix.domain.models.Evenement;
import org.etix.domain.models.Ticket;
import org.etix.domain.ports.driver.TicketPort;
import org.etix.domain.ports.driving.EvenementRepo;
import org.etix.domain.ports.driving.TicketRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@DomaineService
public class TicketService implements TicketPort {

    private final TicketRepo ticketRepo;
    private final EvenementRepo evenementRepo;

    public TicketService(TicketRepo ticketRepo , EvenementRepo evenementRepo) {
        this.evenementRepo = evenementRepo;
        this.ticketRepo = ticketRepo;
    }


    @Override
    public Ticket creerTicket(Ticket ticket) {
        // Vérifier que le ticket a un type

        conditionnerTicket(ticket);
        ticket.setDateHeureCreation(LocalDateTime.now());
        ticket.setQuantite(ticket.getQuantite());
        ticket.setReference( ticket.getEvenement().getLibelle() +UUID.randomUUID().toString());

        // Persister le ticket
        return ticketRepo.saveTicket(ticket);  // ticketRepo.save(ticket) persiste le ticket dans la base de données
    }

    void conditionnerTicket(Ticket ticket) {
        if (ticket.getTypeTicket() == null) {
            throw new IllegalArgumentException("Le ticket doit avoir un type");
        }

        // Vérifier que le ticket est lié à un événement
        if (ticket.getEvenement() == null) {
            throw new IllegalArgumentException("Le ticket doit être lié à un évènement");
        }

        // Assigner le prix du ticket en fonction du type de ticket et de l'événement
        switch (ticket.getTypeTicket()) {
            case GP:
                ticket.setPrix(ticket.getEvenement().getPrixTicketGP());  // Assigner le prix GP
                break;
            case VIP:
                ticket.setPrix(ticket.getEvenement().getPrixTicketVIP());  // Assigner le prix VIP
                break;
            case VVIP:
                ticket.setPrix(ticket.getEvenement().getPrixTicketVVIP());  // Assigner le prix VVIP
                break;
            default:
                throw new IllegalArgumentException("Type de ticket inconnu");
        }
    }

    @Override
    public List<Ticket> collecterLesTicketsParEvenement(String ReferenceEvenement) {
        Evenement evenement = evenementRepo.getEvenementByReference(ReferenceEvenement);
        return ticketRepo.collecterLesTicketsParEvenement(evenement.getReference());
    }

    @Override
    public List<Ticket> getAllTicket() {
        return ticketRepo.getAllTicket();
    }

    @Override
    public Ticket getTicketByReference(String reference) {
        return ticketRepo.getTicketByReference(reference);
    }
}
