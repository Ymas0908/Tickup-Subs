package org.etix.domain.models.Security;



import org.etix.domain.models.enumerations.TypeLog;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LogAcces implements Serializable {

    private static final long serialVersionUID = -8482686345603028663L;


    private Integer id;


    private LocalDateTime createdAt;

    private String libelle;

    private String auteur;

    private String description;

    private Fonctionnalite fonctionnalite;

    private TypeLog type;

    private LogAcces(Builder builder) {
        setId(builder.id);
        setCreatedAt(builder.createdAt);
        setLibelle(builder.libelle);
        setAuteur(builder.auteur);
        setDescription(builder.description);
        setFonctionnalite(builder.fonctionnalite);
        setType(builder.type);
    }

    public LogAcces(Integer id, LocalDateTime createdAt, String libelle, String auteur, String description, Fonctionnalite fonctionnalite, TypeLog type) {
        this.id = id;
        this.createdAt = createdAt;
        this.libelle = libelle;
        this.auteur = auteur;
        this.description = description;
        this.fonctionnalite = fonctionnalite;
        this.type = type;
    }

    public LogAcces() {
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Fonctionnalite getFonctionnalite() {
        return fonctionnalite;
    }

    public void setFonctionnalite(Fonctionnalite fonctionnalite) {
        this.fonctionnalite = fonctionnalite;
    }

    public TypeLog getType() {
        return type;
    }

    public void setType(TypeLog type) {
        this.type = type;
    }

    public static final class Builder {
        private Integer id;
        private LocalDateTime createdAt;
        private String libelle;
        private String auteur;
        private String description;
        private Fonctionnalite fonctionnalite;
        private TypeLog type;

        public Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder libelle(String libelle) {
            this.libelle = libelle;
            return this;
        }

        public Builder auteur(String auteur) {
            this.auteur = auteur;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder fonctionnalite(Fonctionnalite fonctionnalite) {
            this.fonctionnalite = fonctionnalite;
            return this;
        }

        public Builder type(TypeLog type) {
            this.type = type;
            return this;
        }

        public LogAcces build() {
            return new LogAcces(this);
        }
    }
}
