package org.etix.domain.models.Security;


import java.time.LocalDateTime;

public class ProfilAccesSummary {
    private Integer id;
    private String code;
    private String libelle;
    private String description;
    private Boolean statutActif;
    private LocalDateTime createdAt;
    private LocalDateTime dateDernModification;

    public ProfilAccesSummary(Integer id, String code, String libelle, String description, Boolean statutActif, LocalDateTime createdAt, LocalDateTime dateDernModification) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.description = description;
        this.statutActif = statutActif;
        this.createdAt = createdAt;
        this.dateDernModification = dateDernModification;
    }

    public ProfilAccesSummary() {
    }

    private ProfilAccesSummary(Builder builder) {
        setId(builder.id);
        setCode(builder.code);
        setLibelle(builder.libelle);
        setDescription(builder.description);
        setStatutActif(builder.statutActif);
        setCreatedAt(builder.createdAt);
        setDateDernModification(builder.dateDernModification);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    public static final class Builder {
        private Integer id;
        private String code;
        private String libelle;
        private String description;
        private Boolean statutActif;
        private LocalDateTime createdAt;
        private LocalDateTime dateDernModification;

        public Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(Integer id) {
            this.id = id;
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

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder dateDernModification(LocalDateTime dateDernModification) {
            this.dateDernModification = dateDernModification;
            return this;
        }

        public ProfilAccesSummary build() {
            return new ProfilAccesSummary(this);
        }
    }
}
