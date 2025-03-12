package org.etix.domain.ports.driving;

import org.etix.domain.models.Ticket;

import java.util.List;

public interface TicketRepo {

    Ticket saveTicket(Ticket ticket);

     List<Ticket> getTicketByEvenement(Integer idEvenement);

     List<Ticket> getAllTicket();
}
