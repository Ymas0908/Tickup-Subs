/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.etix.domain.models.Security;



import org.etix.domain.models.enumerations.Genre;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Utilisateur implements Serializable {


    private static final long serialVersionUID = 6839172631570706350L;


    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime dateDernConnexion;
    private LocalDateTime dateDernModification;
    private String email;
    private String login;
    private String nom;
    private String prenom;
    private String password;
    private LocalDateTime datePasswordExpire;
    private Boolean statutActif;
    private Genre genre;
    private String telephone;
    private Utilisateur superieurHierachique;

    private Set<ProfilAcces> profils = new HashSet<ProfilAcces>();

    public Utilisateur(Integer id, LocalDateTime createdAt, LocalDateTime deletedAt, LocalDateTime dateDernConnexion, LocalDateTime dateDernModification, String email, String login, String nom, String prenom, String password, LocalDateTime datePasswordExpire, Boolean statutActif, Genre genre, String telephone, Utilisateur superieurHierachique, Set<ProfilAcces> profils) {
        this.id = id;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.dateDernConnexion = dateDernConnexion;
        this.dateDernModification = dateDernModification;
        this.email = email;
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.datePasswordExpire = datePasswordExpire;
        this.statutActif = statutActif;
        this.genre = genre;
        this.telephone = telephone;
        this.superieurHierachique = superieurHierachique;

        this.profils = profils;
    }

    private Utilisateur(Builder builder) {
        setId(builder.id);
        setCreatedAt(builder.createdAt);
        setDeletedAt(builder.deletedAt);
        setDateDernConnexion(builder.dateDernConnexion);
        setDateDernModification(builder.dateDernModification);
        setEmail(builder.email);
        setLogin(builder.login);
        setNom(builder.nom);
        setPrenom(builder.prenom);
        setPassword(builder.password);
        setDatePasswordExpire(builder.datePasswordExpire);
        setStatutActif(builder.statutActif);
        setGenre(builder.genre);
        setTelephone(builder.telephone);
        setSuperieurHierachique(builder.superieurHierachique);

        setProfils(builder.profils);
    }

    String userInfos() {
        return String.format("%s %s (%s)", getNom(), getPrenom(), getEmail());
    }

    public String fullName() {
        return String.format("%s %s", getNom(), getPrenom());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public LocalDateTime getDateDernConnexion() {
        return dateDernConnexion;
    }

    public void setDateDernConnexion(LocalDateTime dateDernConnexion) {
        this.dateDernConnexion = dateDernConnexion;
    }

    public LocalDateTime getDateDernModification() {
        return dateDernModification;
    }

    public void setDateDernModification(LocalDateTime dateDernModification) {
        this.dateDernModification = dateDernModification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDatePasswordExpire() {
        return datePasswordExpire;
    }

    public void setDatePasswordExpire(LocalDateTime datePasswordExpire) {
        this.datePasswordExpire = datePasswordExpire;
    }

    public Boolean getStatutActif() {
        return statutActif;
    }

    public void setStatutActif(Boolean statutActif) {
        this.statutActif = statutActif;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Utilisateur getSuperieurHierachique() {
        return superieurHierachique;
    }

    public void setSuperieurHierachique(Utilisateur superieurHierachique) {
        this.superieurHierachique = superieurHierachique;
    }


    public Set<ProfilAcces> getProfils() {
        return profils;
    }

    public void setProfils(Set<ProfilAcces> profils) {
        this.profils = profils;
    }


    public static final class Builder {
        private Integer id;
        private LocalDateTime createdAt;
        private LocalDateTime deletedAt;
        private LocalDateTime dateDernConnexion;
        private LocalDateTime dateDernModification;
        private String email;
        private String login;
        private String codeExploitant;
        private String nom;
        private String prenom;
        private String password;
        private LocalDateTime datePasswordExpire;
        private Boolean statutActif;
        private Genre genre;
        private String telephone;
        private Utilisateur superieurHierachique;

        private Set<ProfilAcces> profils;

        public Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder deletedAt(LocalDateTime deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }

        public Builder dateDernConnexion(LocalDateTime dateDernConnexion) {
            this.dateDernConnexion = dateDernConnexion;
            return this;
        }

        public Builder dateDernModification(LocalDateTime dateDernModification) {
            this.dateDernModification = dateDernModification;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }


        public Builder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public Builder prenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder datePasswordExpire(LocalDateTime datePasswordExpire) {
            this.datePasswordExpire = datePasswordExpire;
            return this;
        }

        public Builder statutActif(Boolean statutActif) {
            this.statutActif = statutActif;
            return this;
        }

        public Builder genre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public Builder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public Builder superieurHierachique(Utilisateur superieurHierachique) {
            this.superieurHierachique = superieurHierachique;
            return this;
        }


        public Builder profils(Set<ProfilAcces> profils) {
            this.profils = profils;
            return this;
        }

        public Utilisateur build() {
            return new Utilisateur(this);
        }
    }
}
