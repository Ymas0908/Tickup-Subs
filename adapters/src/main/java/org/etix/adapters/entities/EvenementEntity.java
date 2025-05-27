package org.etix.adapters.entities;

import jakarta.persistence.*;
import lombok.*;
import org.etix.domain.models.Evenement;
import org.etix.domain.models.enumerations.TypeEvenement;
//import org.etix.domain.models.enumerations.TypeEvenement;

import java.sql.Blob;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Table(name = "evenements")
@Entity
public class EvenementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String reference;
    private String urlImage;
    private String libelle;
    private String description;
    private String lieu;
    private String prixTicketGP;
    private String prixTicketVIP;
    private String prixTicketVVIP;
    private LocalDateTime dateEvenement;
    private LocalDateTime dateHeureCreation;
    @Enumerated(EnumType.STRING)
    private TypeEvenement typeEvenement;

    public static EvenementEntity toEntity(Evenement evenement) {
        return EvenementEntity.builder()
                .id(evenement.getId())
                .nom(evenement.getNom())
                .reference(evenement.getReference())
                .urlImage(evenement.getUrlImage())
                .libelle(evenement.getLibelle())
                .lieu(evenement.getLieu())
                .prixTicketGP(evenement.getPrixTicketGP())
                .prixTicketVIP(evenement.getPrixTicketVIP())
                .prixTicketVVIP(evenement.getPrixTicketVVIP())
                .description(evenement.getDescription())
                .dateEvenement(evenement.getDateEvenement())
                .dateHeureCreation(evenement.getDateHeureCreation())
                .typeEvenement(evenement.getTypeEvenement())
                .build();
    }

    public Evenement toDomain() {
        return new Evenement.Builder()
                .id(this.id)
                .nom(this.nom)
                .reference(this.reference)
                .urlImage(this.urlImage)
                .libelle(this.libelle)
                .lieu(this.lieu)
                .prixTicketGP(this.prixTicketGP)
                .prixTicketVIP(this.prixTicketVIP)
                .prixTicketVVIP(this.prixTicketVVIP)
                .description(this.description)
                .dateEvenement(this.dateEvenement)
                .dateHeureCreation(this.dateHeureCreation)
                .typeEvenement(this.typeEvenement)
                .build();
    }

}
