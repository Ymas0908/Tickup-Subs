package org.etix.adapters.entities.Security;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.etix.domain.models.Security.LogAcces;
import org.etix.domain.models.enumerations.TypeLog;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "logacces")
public class LogAccesEntity implements Serializable {

    private static final long serialVersionUID = -8482686345603028663L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    private LocalDateTime createdAt;

    @Column(name = "libelle", length = 150, nullable = false)
    private String libelle;

    @Column(name = "auteur", length = 200, nullable = true)
    private String auteur;

    @Column(name = "description", nullable = false)
    private String description;

    //cle etrangère
    @ManyToOne
    @JoinColumn(name = "fonctionnalite_id", nullable = true)
    private FonctionnaliteEntity fonctionnalite;


    @Enumerated(EnumType.STRING)
    @Column(name = "type_log", nullable = false)
    private TypeLog type;

    public static LogAccesEntity toEntity(LogAcces logacces) {
        return new LogAccesEntityBuilder()
                .id(logacces.getId())
                .createdAt(logacces.getCreatedAt())
                .libelle(logacces.getLibelle())
                .auteur(logacces.getAuteur())
                .description(logacces.getDescription())
                .fonctionnalite(logacces.getFonctionnalite() != null ? FonctionnaliteEntity.toEntity(logacces.getFonctionnalite()) : null)
                .type(logacces.getType())
                .build();
    }

    public LogAcces toDomain() {
        return new LogAcces.Builder()
                .id(id)
                .createdAt(createdAt)
                .libelle(libelle)
                .auteur(auteur)
                .description(description)
                .fonctionnalite(fonctionnalite != null ? fonctionnalite.toDomain() : null)
                .type(type)
                .build();
    }
}
