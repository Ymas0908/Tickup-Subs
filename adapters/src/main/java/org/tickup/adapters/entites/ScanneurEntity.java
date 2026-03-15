package org.tickup.adapters.entites;

import jakarta.persistence.*;
import lombok.*;
import org.tickup.domain.models.Scanneur;
import org.tickup.domain.models.Usager;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "scanneur")
public class ScanneurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private  String refScanneur;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String email;
    private String telephone;
    private String terminalId;

    public static ScanneurEntity toEntity(Scanneur scanneur) {
        return ScanneurEntity.builder()
                .id(scanneur.getIdScanneur())
                .refScanneur(scanneur.getRefScanneur())
                .nom(scanneur.getNom())
                .prenom(scanneur.getPrenom())
                .dateNaissance(scanneur.getDateNaissance())
                .email(scanneur.getEmail())
                .telephone(scanneur.getTelephone())
                .terminalId(scanneur.getTerminalId())
                .build();
    }

    public Scanneur toDomain() {
        return new Scanneur.Builder()
                .idScanneur(this.id)
                .refScanneur(this.refScanneur)
                .nom(this.nom)
                .prenom(this.prenom)
                .dateNaissance(this.dateNaissance)
                .email(this.email)
                .telephone(this.telephone)
                .terminalId(this.terminalId)
                .build();
    }

}
