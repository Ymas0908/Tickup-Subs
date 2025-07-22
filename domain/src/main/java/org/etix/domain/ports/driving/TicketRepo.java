package org.etix.domain.ports.driving;

import org.etix.domain.models.Ticket;

import java.util.List;

public interface TicketRepo {

    Ticket saveTicket(Ticket ticket);

     List<Ticket> collecterLesTicketsParEvenement(String ReferenceEvenement);

     List<Ticket> getAllTicket();

    Ticket getTicketByReference(String reference);

    Integer getNombresTotalTickets();
}
