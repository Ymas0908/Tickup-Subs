package org.etix.adapters.entities;

import jakarta.persistence.*;
import lombok.*;
import org.etix.domain.models.Ticket;
import org.etix.domain.models.enumerations.TypeTicket;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name = "ticket")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String prix;
    private int quantite;
    @Enumerated(EnumType.STRING)
    private TypeTicket typeTicket;
    private LocalDateTime dateHeureCreation;
    @ManyToOne
    @JoinColumn
    private EvenementEntity evenement;

    public static TicketEntity toEntity(Ticket ticket) {

        return TicketEntity.builder()
                .id(ticket.getId())
                .prix(ticket.getPrix())
                .quantite(ticket.getQuantite())
                .typeTicket(ticket.getTypeTicket())
                .dateHeureCreation(ticket.getDateHeureCreation())
                .evenement(ticket.getEvenement() != null ? EvenementEntity.toEntity(ticket.getEvenement()) : null)
                .build();
    }
    public Ticket toDomain() {
        return new Ticket.Builder()
                .id(this.id)
                .prix(this.prix)
                .quantite(this.quantite)
                .typeTicket(this.typeTicket)
                .dateHeureCreation(this.dateHeureCreation)
                .evenement(this.evenement != null ? this.evenement.toDomain() : null)
                .build();
    }
}
