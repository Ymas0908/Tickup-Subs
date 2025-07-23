/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.etix.adapters.entities.Security;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.etix.domain.models.Security.ProfilAcces;
import org.etix.domain.models.Security.ProfilAccesSummary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "profil_acces")
public class ProfilAccesEntity implements Serializable {

    private static final long serialVersionUID = 1304589877424054227L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    private LocalDateTime createdAt;

    @Column(name = "date_dern_modification")
    private LocalDateTime dateDernModification;

    @Column(name = "code", length = 25, nullable = false)
    private String code;

    @Column(name = "libelle", length = 150, nullable = false)
    private String libelle;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "statut_actit", nullable = false)
    private Boolean statutActif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "strategie_compte_id")
    private StrategieCompteEntity strategieCompte;

    @ManyToOne
    @JoinColumn(name = "fonctionnalite_accueil_id")
    private FonctionnaliteEntity fonctionnaliteAccueil;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "profil_acces_fonctionnalite",
            joinColumns = @JoinColumn(name = "profil_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fonctionnalite_id", referencedColumnName = "id"))
    private Set<FonctionnaliteEntity> fonctionnalites = new HashSet<FonctionnaliteEntity>();

    public static ProfilAccesEntity toEntity(ProfilAcces profilacces) {
        return new ProfilAccesEntityBuilder()
                .id(profilacces.getId())
                .createdAt(profilacces.getCreatedAt())
                .dateDernModification(profilacces.getDateDernModification())
                .code(profilacces.getCode())
                .libelle(profilacces.getLibelle())
                .description(profilacces.getDescription())
                .statutActif(profilacces.getStatutActif())
                .strategieCompte(profilacces.getStrategieCompte() != null ? StrategieCompteEntity.toEntity(profilacces.getStrategieCompte()) : null)
                .fonctionnaliteAccueil(profilacces.getFonctionnaliteAccueil() != null ? FonctionnaliteEntity.toEntity(profilacces.getFonctionnaliteAccueil()) : null)
                .fonctionnalites(profilacces.getFonctionnalites() != null ? profilacces.getFonctionnalites().stream().map(FonctionnaliteEntity::toEntity).collect(Collectors.toSet()) : null)
                .build();
    }

    public ProfilAcces toDomain() {
        return new ProfilAcces.Builder()
                .id(id)
                .createdAt(createdAt)
                .dateDernModification(dateDernModification)
                .code(code)
                .libelle(libelle)
                .description(description)
                .statutActif(statutActif)
                .strategieCompte(strategieCompte != null ? strategieCompte.toDomain() : null)
                .fonctionnaliteAccueil(fonctionnaliteAccueil != null ? fonctionnaliteAccueil.toDomain() : null)
                .fonctionnalites(fonctionnalites != null ? fonctionnalites.stream().map(FonctionnaliteEntity::toDomain).collect(Collectors.toSet()) : null)
                .build();
    }

    public ProfilAccesSummary toDomainSummary() {
        return new ProfilAccesSummary.Builder()
                .id(id)
                .createdAt(createdAt)
                .dateDernModification(dateDernModification)
                .code(code)
                .libelle(libelle)
                .description(description)
                .statutActif(statutActif)
                .build();
    }
}
