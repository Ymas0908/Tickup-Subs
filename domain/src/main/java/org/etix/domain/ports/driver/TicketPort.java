package org.etix.domain.ports.driver;

import org.etix.domain.models.Ticket;

import java.util.List;

public interface TicketPort {


   Ticket creerTicket(Ticket ticket);

    List<Ticket> getTicketByEvenement(Integer idEvenement);

    List<Ticket> getAllTicket();


    Ticket getTicketByReference(String reference);
}
