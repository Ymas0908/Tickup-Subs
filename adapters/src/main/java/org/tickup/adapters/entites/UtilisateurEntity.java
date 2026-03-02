package org.tickup.adapters.entites;

import jakarta.persistence.*;
import lombok.*;
import org.tickup.domain.models.Utilisateur;
import org.tickup.domain.models.enums.Role;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "utilisateur")
public class UtilisateurEntity {
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
    private String refUsager;

    public UtilisateurEntity() {
    }

    public static UtilisateurEntity toEntity(Utilisateur utilisateur) {
        return UtilisateurEntity.builder()
                .id(utilisateur.getId())
                .createdAt(utilisateur.getCreatedAt())
                .deletedAt(utilisateur.getDeletedAt())
                .dateDernConnexion(utilisateur.getDateDernConnexion())
                .dateDernModification(utilisateur.getDateDernModification())
                .login(utilisateur.getLogin())
                .password(utilisateur.getPassword())
                .datePasswordExpire(utilisateur.getDatePasswordExpire())
                .statutActif(utilisateur.getStatutActif())
                .role(utilisateur.getRole())
                .isFisrtConnection(utilisateur.isFisrtConnection())
                .refUsager(utilisateur.getrefUsager())
                .build();
    }

    public Utilisateur toDomain() {
        return new Utilisateur.Builder()
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
                .refUsager(refUsager)
                .build();
    }

//    public UtilisateurSlim toDomainSlim() {
//        return new UtilisateurSlim.Builder()
//                .id(id)
//                .codeExploitant(codeExploitant)
//                .email(email)
//                .login(login)
//                .nom(nom)
//                .prenom(prenom)
//                .build();
//    }

}
