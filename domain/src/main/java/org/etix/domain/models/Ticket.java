package org.etix.domain.models;

import org.etix.domain.models.enumerations.TypeTicket;

import java.time.LocalDateTime;

public class Ticket {
    private Integer id;

    private String reference;
    private String prix;
    private int quantite;
    private TypeTicket typeTicket;
    private LocalDateTime dateHeureCreation;
    private Evenement evenement;
    public Ticket() {
    }

    public Ticket(Integer id,String reference, String prix, int quantite, TypeTicket typeTicket, LocalDateTime dateHeureCreation,Evenement evenement) {
        this.id = id;
        this.reference = reference;
        this.prix = prix;
        this.quantite = quantite;
        this.typeTicket = typeTicket;
        this.dateHeureCreation = dateHeureCreation;
        this.evenement = evenement;
    }

    private Ticket(Builder builder) {
        setId(builder.id);
        setReference(builder.reference);
        setPrix(builder.prix);
        setQuantite(builder.quantite);
        setTypeTicket(builder.typeTicket);
        setDateHeureCreation(builder.dateHeureCreation);
        setEvenement(builder.evenement);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public TypeTicket getTypeTicket() {
        return typeTicket;
    }

    public void setTypeTicket(TypeTicket typeTicket) {
        this.typeTicket = typeTicket;
    }

    public LocalDateTime getDateHeureCreation() {
        return dateHeureCreation;
    }

    public void setDateHeureCreation(LocalDateTime dateHeureCreation) {
        this.dateHeureCreation = dateHeureCreation;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }


    public static final class Builder {
        private Integer id;
        private String reference;
        private String prix;
        private int quantite;
        private TypeTicket typeTicket;
        private LocalDateTime dateHeureCreation;
        private Evenement evenement;

        public Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder reference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder prix(String prix) {
            this.prix = prix;
            return this;
        }

        public Builder quantite(int quantite) {
            this.quantite = quantite;
            return this;
        }

        public Builder typeTicket(TypeTicket typeTicket) {
            this.typeTicket = typeTicket;
            return this;
        }

        public Builder dateHeureCreation(LocalDateTime dateHeureCreation) {
           this.dateHeureCreation = dateHeureCreation;
            return this;
        }

        public Builder evenement(Evenement evenement) {
            this.evenement = evenement;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }
    }
}
