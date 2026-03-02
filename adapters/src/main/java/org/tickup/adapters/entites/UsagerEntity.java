package org.tickup.adapters.entites;

import jakarta.persistence.*;
import lombok.*;
import org.tickup.domain.models.Usager;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "usager")
public class UsagerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private  String refUsager;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String email;
    private String telephone;
    private String terminalId;

    public static UsagerEntity toEntity(Usager usager) {
        return UsagerEntity.builder()
                .id(usager.getIdUsager())
                .refUsager(usager.getRefUsager())
                .nom(usager.getNom())
                .prenom(usager.getPrenom())
                .dateNaissance(usager.getDateNaissance())
                .email(usager.getEmail())
                .telephone(usager.getTelephone())
                .terminalId(usager.getTerminalId())
                .build();
    }

    public Usager toDomain() {
        return new Usager.Builder()
                .idUsager(this.id)
                .refUsager(this.refUsager)
                .nom(this.nom)
                .prenom(this.prenom)
                .dateNaissance(this.dateNaissance)
                .email(this.email)
                .telephone(this.telephone)
                .terminalId(this.terminalId)
                .build();
    }

}
