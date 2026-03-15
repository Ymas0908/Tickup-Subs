package org.tickup.adapters.entites;

import jakarta.persistence.*;
import lombok.*;
import org.tickup.domain.models.Utilisateur;
import org.tickup.domain.models.UtilisateurScanneur;
import org.tickup.domain.models.enums.Role;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "utilisateurscanneur")
public class UtlitisateurScanneurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime dateDernConnexion;
    private LocalDateTime dateDernModification;
    private String login;
    private String password;
    private LocalDateTime datePasswordExpire;
    @Setter
    private Boolean statutActif;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean isFisrtConnection;
    @Column(unique = true, length = 25)
    private String refScanneur;

    public UtlitisateurScanneurEntity() {
    }

    public static UtlitisateurScanneurEntity toEntity(UtilisateurScanneur utilisateurScanneur) {
        return UtlitisateurScanneurEntity.builder()
                .id(utilisateurScanneur.getId())
                .createdAt(utilisateurScanneur.getCreatedAt())
                .deletedAt(utilisateurScanneur.getDeletedAt())
                .dateDernConnexion(utilisateurScanneur.getDateDernConnexion())
                .dateDernModification(utilisateurScanneur.getDateDernModification())
                .login(utilisateurScanneur.getLogin())
                .password(utilisateurScanneur.getPassword())
                .datePasswordExpire(utilisateurScanneur.getDatePasswordExpire())
                .statutActif(utilisateurScanneur.getStatutActif())
                .role(utilisateurScanneur.getRole())
                .isFisrtConnection(utilisateurScanneur.isFisrtConnection())
                .refScanneur(utilisateurScanneur.getRefScanneur())
                .build();
    }

    public UtilisateurScanneur toDomain() {
        return new UtilisateurScanneur.Builder()
                .id(id)
                .createdAt(createdAt)
                .deletedAt(deletedAt)
                .dateDernConnexion(dateDernConnexion)
                .dateDernModification(dateDernModification)
                .login(login)
                .password(password)
                .datePasswordExpire(datePasswordExpire)
                .statutActif(statutActif)
                .role(role)
                .isFisrtConnection(isFisrtConnection)
                .refScanneur(refScanneur)
                .build();
    }
}
