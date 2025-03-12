package org.etix.adapters.driver.facades;

import lombok.AllArgsConstructor;
import org.etix.adapters.entities.TicketEntity;
import org.etix.domain.ports.driver.TicketPort;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class CreerUnTicketFacade {

    private  TicketPort ticketPort;

    public TicketEntity creerUnTicket(TicketEntity ticket) {
        return TicketEntity.toEntity(ticketPort.creerTicket(ticket.toDomain()));
    }


    public List<TicketEntity> getTicketByEvenement(Integer idEvenement) {
        return ticketPort.getTicketByEvenement(idEvenement).stream().map(TicketEntity::toEntity).toList();
    }

    public List<TicketEntity> getAllTickets() {
        return ticketPort.getAllTicket().stream().map(TicketEntity::toEntity).toList();
    }





}
