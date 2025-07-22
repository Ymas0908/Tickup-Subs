package org.etix.adapters.driving.impl;

import lombok.AllArgsConstructor;
import org.etix.adapters.driving.repositories.TicketRepository;
import org.etix.adapters.entities.TicketEntity;
import org.etix.domain.models.Ticket;
import org.etix.domain.ports.driving.TicketRepo;
import org.springframework.stereotype.Component;

import java.util.List;
@AllArgsConstructor
@Component
public class TicketImpl implements TicketRepo {

    private final TicketRepository ticketRepository;
    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(TicketEntity.toEntity(ticket)).toDomain();
    }

    @Override
    public List<Ticket> collecterLesTicketsParEvenement(String referenceEvenement) {
        return ticketRepository.findByReferenceEvenement(referenceEvenement).stream().map(TicketEntity::toDomain).toList();
    }

    @Override
    public List<Ticket> getAllTicket() {
        return ticketRepository.findAll().stream().map(TicketEntity::toDomain).toList();
    }

    @Override
    public Ticket getTicketByReference(String reference) {
        return ticketRepository.findByReference(reference).toDomain();
    }

    @Override
    public Integer getNombresTotalTickets() {
        return ticketRepository.getTotalTickets();
    }
}
