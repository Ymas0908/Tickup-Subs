/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.etix.adapters.entities.Security;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.etix.domain.models.Security.Utilisateur;
import org.etix.domain.models.Security.UtilisateurSlim;
import org.etix.domain.models.enumerations.Genre;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Luc GUIGUEMDE
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "utilisateur")
public class UtilisateurEntity implements Serializable {


    private static final long serialVersionUID = 6839172631570706350L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    private LocalDateTime createdAt;

    @Column(name = "deleted_date")
    private LocalDateTime deletedAt;

    @Column(name = "date_dern_connexion")
    private LocalDateTime dateDernConnexion;

    @Column(name = "date_dern_modification")
    private LocalDateTime dateDernModification;

    @Column(name = "email", nullable = false, length = 180)
    private String email;

    @Column(name = "login", nullable = false, length = 180)
    private String login;


    @Column(name = "nom", nullable = false, length = 80)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 80)
    private String prenom;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "date_password_expire")
    private LocalDateTime datePasswordExpire;

    @Column(name = "statut_actit", nullable = false)
    private Boolean statutActif;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = false)
    private Genre genre;

    @Column(name = "telephone", length = 15)
    private String telephone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "superieur_hierachique_id")
    private UtilisateurEntity superieurHierachique;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utilisateur_profil_acces",
            joinColumns = @JoinColumn(name = "utilisateur_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "profil_id", referencedColumnName = "id"))
    private Set<ProfilAccesEntity> profils = new HashSet<ProfilAccesEntity>();

    public UtilisateurEntity(Integer id, String email, String nom, String prenom) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
    }

    public UtilisateurEntity(Integer id, String email, String nom, String prenom, String login, Genre genre, String telephone, Boolean statutActif) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.genre = genre;
        this.telephone = telephone;
        this.statutActif = statutActif;
    }

    public static UtilisateurEntity toEntity(Utilisateur utilisateur) {
        return new UtilisateurEntityBuilder()
                .id(utilisateur.getId())
                .createdAt(utilisateur.getCreatedAt())
                .deletedAt(utilisateur.getDeletedAt())
                .dateDernConnexion(utilisateur.getDateDernConnexion())
                .dateDernModification(utilisateur.getDateDernModification())
                .email(utilisateur.getEmail())
                .login(utilisateur.getLogin())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .password(utilisateur.getPassword())
                .datePasswordExpire(utilisateur.getDatePasswordExpire())
                .statutActif(utilisateur.getStatutActif())
                .genre(utilisateur.getGenre())
                .telephone(utilisateur.getTelephone())
                .superieurHierachique(utilisateur.getSuperieurHierachique() != null ? UtilisateurEntity.toEntity(utilisateur.getSuperieurHierachique()) : null)
                .profils(utilisateur.getProfils() != null ? utilisateur.getProfils().stream().map(ProfilAccesEntity::toEntity).collect(Collectors.toSet()) : null)
                .build();
    }

    public String fullName() {
        return String.format("%s %s", this.nom, this.prenom);
    }

    public String userInfos() {
        return String.format("%s %s (%s)", this.nom, this.prenom, this.email);
    }

    public Utilisateur toDomain() {
        return new Utilisateur.Builder()
                .id(id)
                .createdAt(createdAt)
                .deletedAt(deletedAt)
                .dateDernConnexion(dateDernConnexion)
                .dateDernModification(dateDernModification)
                .email(email)
                .login(login)
                .nom(nom)
                .prenom(prenom)
                .password(password)
                .datePasswordExpire(datePasswordExpire)
                .statutActif(statutActif)
                .genre(genre)
                .telephone(telephone)
                .superieurHierachique(superieurHierachique != null ? superieurHierachique.toDomain() : null)
                .profils(profils != null ? profils.stream().map(ProfilAccesEntity::toDomain).collect(Collectors.toSet()) : null)
                .build();
    }

    public UtilisateurSlim toDomainSlim() {
        return new UtilisateurSlim.Builder()
                .id(id)
                .email(email)
                .login(login)
                .nom(nom)
                .prenom(prenom)
                .genre(genre)
                .telephone(telephone)
                .statutActif(statutActif)
                .build();
    }

}
