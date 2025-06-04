package org.etix.domain.models;

import org.etix.domain.models.enumerations.StatutEvenement;
import org.etix.domain.models.enumerations.TypeEvenement;

import java.sql.Blob;
import java.time.LocalDateTime;

public class Evenement {
    private Integer id;
    private String nom;
    private String reference;
    private StatutEvenement statutEvenement;
    private String urlImage;
    private String libelle;
    private String description;
    private String lieu;
    private String prixTicketGP;
    private String prixTicketVIP;
    private String prixTicketVVIP;
    private LocalDateTime dateEvenement;
    private LocalDateTime dateHeureCreation;
    private TypeEvenement typeEvenement;

    public Evenement() {
    }

    public Evenement(Integer id, String nom,String reference, StatutEvenement statutEvenement,String urlImage,String libelle, String description, String lieu, String prixTicketGP, String prixTicketVIP, String prixTicketVVIP, LocalDateTime dateEvenement, LocalDateTime dateHeureCreation, TypeEvenement typeEvenement) {
        this.id = id;
        this.nom = nom;
        this.reference = reference;
        this.statutEvenement = statutEvenement;
        this.urlImage = urlImage;
        this.libelle = libelle;
        this.description = description;
        this.lieu = lieu;
        this.prixTicketGP = prixTicketGP;
        this.prixTicketVIP = prixTicketVIP;
        this.prixTicketVVIP = prixTicketVVIP;
        this.dateEvenement = dateEvenement;
        this.dateHeureCreation = dateHeureCreation;
        this.typeEvenement = typeEvenement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public StatutEvenement getStatutEvenement() {
        return statutEvenement;
    }

    public void setStatutEvenement(StatutEvenement statutEvenement) {
        this.statutEvenement = statutEvenement;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getPrixTicketGP() {
        return prixTicketGP;
    }

    public void setPrixTicketGP(String prixTicketGP) {
        this.prixTicketGP = prixTicketGP;
    }

    public String getPrixTicketVIP() {
        return prixTicketVIP;
    }

    public void setPrixTicketVIP(String prixTicketVIP) {
        this.prixTicketVIP = prixTicketVIP;
    }

    public String getPrixTicketVVIP() {
        return prixTicketVVIP;
    }

    public void setPrixTicketVVIP(String prixTicketVVIP) {
        this.prixTicketVVIP = prixTicketVVIP;
    }

    public LocalDateTime getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(LocalDateTime dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public LocalDateTime getDateHeureCreation() {
        return dateHeureCreation;
    }

    public void setDateHeureCreation(LocalDateTime dateHeureCreation) {
        this.dateHeureCreation = dateHeureCreation;
    }

    public TypeEvenement getTypeEvenement() {
        return typeEvenement;
    }

    public void setTypeEvenement(TypeEvenement typeEvenement) {
        this.typeEvenement = typeEvenement;
    }

    private Evenement(Builder builder) {
        id = builder.id;
        nom = builder.nom;
        reference = builder.reference;
        statutEvenement = builder.statutEvenement;
        urlImage = builder.urlImage;
        libelle = builder.libelle;
        description = builder.description;
        lieu = builder.lieu;
        prixTicketGP = builder.prixTicketGP;
        prixTicketVIP = builder.prixTicketVIP;
        prixTicketVVIP = builder.prixTicketVVIP;
        dateEvenement = builder.dateEvenement;
        dateHeureCreation = builder.dateHeureCreation;
        typeEvenement = builder.typeEvenement;
    }

    public static final class Builder {
        private Integer id;
        private String nom;
        private String reference;
        private StatutEvenement statutEvenement;
        private String urlImage;
        private String libelle;
        private String description;
        private String lieu;
        private String prixTicketGP;
        private String prixTicketVIP;
        private String prixTicketVVIP;
        private LocalDateTime dateEvenement;
        private LocalDateTime dateHeureCreation;
        private TypeEvenement typeEvenement;

        public Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public Builder reference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder statutEvenement(StatutEvenement statutEvenement) {
            this.statutEvenement = statutEvenement;
            return this;
        }

        public Builder urlImage(String urlImage) {
            this.urlImage = urlImage;
            return this;
        }

        public Builder libelle(String libelle) {
            this.libelle = libelle;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder lieu(String lieu) {
            this.lieu = lieu;
            return this;
        }

        public Builder prixTicketGP(String prixTicketGP) {
            this.prixTicketGP = prixTicketGP;
            return this;
        }

        public Builder prixTicketVIP(String prixTicketVIP) {
            this.prixTicketVIP = prixTicketVIP;
            return this;
        }

        public Builder prixTicketVVIP(String prixTicketVVIP) {
            this.prixTicketVVIP = prixTicketVVIP;
            return this;
        }

        public Builder dateEvenement(LocalDateTime dateEvenement) {
            this.dateEvenement = dateEvenement;
            return this;
        }

        public Builder dateHeureCreation(LocalDateTime dateHeureCreation) {
            this.dateHeureCreation = dateHeureCreation;
            return this;
        }

        public Builder typeEvenement(TypeEvenement typeEvenement) {
            this.typeEvenement = typeEvenement;
            return this;
        }

        public Evenement build() {
            return new Evenement(this);
        }
    }
}
