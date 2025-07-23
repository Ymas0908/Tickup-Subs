package org.etix.domain.models.Security;



import org.etix.domain.models.enumerations.Genre;

import java.io.Serializable;


public class UtilisateurSlim implements Principal, Serializable {

    private static final long serialVersionUID = 5104544334659376326L;

    protected Integer id;


    protected String email;

    protected String login;

    protected String nom;

    protected String prenom;

    protected Genre genre;

    protected String telephone;

    protected Boolean statutActif;

    private UtilisateurSlim(Builder builder) {
        id = builder.id;
        email = builder.email;
        login = builder.login;
        nom = builder.nom;
        prenom = builder.prenom;
        genre = builder.genre;
        telephone = builder.telephone;
        statutActif = builder.statutActif;
    }

    public UtilisateurSlim(Integer id, String email, String login, String nom, String prenom, Genre genre, String telephone, Boolean statutActif) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.telephone = telephone;
        this.statutActif = statutActif;
    }

    public UtilisateurSlim() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getStatutActif() {
        return statutActif;
    }

    public void setStatutActif(Boolean statutActif) {
        this.statutActif = statutActif;
    }


    public static final class Builder {
        private Integer id;
        private String email;
        private String login;
        private String nom;
        private String prenom;
        private Genre genre;
        private String telephone;
        private Boolean statutActif;

        public Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(Integer id) {
            this.id = id;
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

        public Builder genre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public Builder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public Builder statutActif(Boolean statutActif) {
            this.statutActif = statutActif;
            return this;
        }

        public UtilisateurSlim build() {
            return new UtilisateurSlim(this);
        }
    }
}
