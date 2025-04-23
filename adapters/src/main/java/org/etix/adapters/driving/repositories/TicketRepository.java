package org.etix.adapters.driving.repositories;

import org.etix.adapters.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

    @Query("SELECT t FROM TicketEntity t WHERE t.evenement.id = :idEvenement")
    List<TicketEntity>findByEvenementId(Integer idEvenement);

    @Query("SELECT t FROM TicketEntity t WHERE t.reference = :reference")
    TicketEntity findByReference(String reference);

}
