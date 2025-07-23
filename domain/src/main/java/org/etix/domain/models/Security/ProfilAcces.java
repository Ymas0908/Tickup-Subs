package org.etix.domain.models.Security;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ProfilAcces implements Serializable {

    private static final long serialVersionUID = 1304589877424054227L;

    private Integer id;


    private LocalDateTime createdAt;

    private LocalDateTime dateDernModification;

    private String code;

    private String libelle;

    private String description;

    private Boolean statutActif;


    private StrategieCompte strategieCompte;


    private Fonctionnalite fonctionnaliteAccueil;

    private Set<Fonctionnalite> fonctionnalites = new HashSet<Fonctionnalite>();

    private ProfilAcces(Builder builder) {
        setId(builder.id);
        setCreatedAt(builder.createdAt);
        setDateDernModification(builder.dateDernModification);
        setCode(builder.code);
        setLibelle(builder.libelle);
        setDescription(builder.description);
        setStatutActif(builder.statutActif);
        setStrategieCompte(builder.strategieCompte);
        setFonctionnaliteAccueil(builder.fonctionnaliteAccueil);
        setFonctionnalites(builder.fonctionnalites);
    }

    public ProfilAcces() {
    }

    public ProfilAcces(Integer id, LocalDateTime createdAt, LocalDateTime dateDernModification, String code, String libelle, String description, Boolean statutActif, StrategieCompte strategieCompte, Fonctionnalite fonctionnaliteAccueil, Set<Fonctionnalite> fonctionnalites) {
        this.id = id;
        this.createdAt = createdAt;
        this.dateDernModification = dateDernModification;
        this.code = code;
        this.libelle = libelle;
        this.description = description;
        this.statutActif = statutActif;
        this.strategieCompte = strategieCompte;
        this.fonctionnaliteAccueil = fonctionnaliteAccueil;
        this.fonctionnalites = fonctionnalites;
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

    public LocalDateTime getDateDernModification() {
        return dateDernModification;
    }

    public void setDateDernModification(LocalDateTime dateDernModification) {
        this.dateDernModification = dateDernModification;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Boolean getStatutActif() {
        return statutActif;
    }

    public void setStatutActif(Boolean statutActif) {
        this.statutActif = statutActif;
    }

    public StrategieCompte getStrategieCompte() {
        return strategieCompte;
    }

    public void setStrategieCompte(StrategieCompte strategieCompte) {
        this.strategieCompte = strategieCompte;
    }

    public Fonctionnalite getFonctionnaliteAccueil() {
        return fonctionnaliteAccueil;
    }

    public void setFonctionnaliteAccueil(Fonctionnalite fonctionnaliteAccueil) {
        this.fonctionnaliteAccueil = fonctionnaliteAccueil;
    }

    public Set<Fonctionnalite> getFonctionnalites() {
        return fonctionnalites;
    }

    public void setFonctionnalites(Set<Fonctionnalite> fonctionnalites) {
        this.fonctionnalites = fonctionnalites;
    }

    public static final class Builder {
        private Integer id;
        private LocalDateTime createdAt;
        private LocalDateTime dateDernModification;
        private String code;
        private String libelle;
        private String description;
        private Boolean statutActif;
        private StrategieCompte strategieCompte;
        private Fonctionnalite fonctionnaliteAccueil;
        private Set<Fonctionnalite> fonctionnalites;

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

        public Builder dateDernModification(LocalDateTime dateDernModification) {
            this.dateDernModification = dateDernModification;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
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

        public Builder statutActif(Boolean statutActif) {
            this.statutActif = statutActif;
            return this;
        }

        public Builder strategieCompte(StrategieCompte strategieCompte) {
            this.strategieCompte = strategieCompte;
            return this;
        }

        public Builder fonctionnaliteAccueil(Fonctionnalite fonctionnaliteAccueil) {
            this.fonctionnaliteAccueil = fonctionnaliteAccueil;
            return this;
        }

        public Builder fonctionnalites(Set<Fonctionnalite> fonctionnalites) {
            this.fonctionnalites = fonctionnalites;
            return this;
        }

        public ProfilAcces build() {
            return new ProfilAcces(this);
        }
    }
}
