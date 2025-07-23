/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.etix.domain.models.Security;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Fonctionnalite implements Serializable {

    private static final long serialVersionUID = -7932089617544590363L;


    protected Integer id;

    protected LocalDateTime createdAt;

    protected LocalDateTime deletedAt;

    private Boolean statutActif;

    private String libelle;

    private String pageUrl;

    private String description;

    private String code;

    public Fonctionnalite(Integer id, LocalDateTime createdAt, LocalDateTime deletedAt, Boolean statutActif, String libelle, String pageUrl, String description, String code) {
        this.id = id;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.statutActif = statutActif;
        this.libelle = libelle;
        this.pageUrl = pageUrl;
        this.description = description;
        this.code = code;
    }

    public Fonctionnalite() {
    }

    private Fonctionnalite(Builder builder) {
        setId(builder.id);
        setCreatedAt(builder.createdAt);
        setDeletedAt(builder.deletedAt);
        setStatutActif(builder.statutActif);
        setLibelle(builder.libelle);
        setPageUrl(builder.pageUrl);
        setDescription(builder.description);
        setCode(builder.code);
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

    public Boolean getStatutActif() {
        return statutActif;
    }

    public void setStatutActif(Boolean statutActif) {
        this.statutActif = statutActif;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public static final class Builder {
        private Integer id;
        private LocalDateTime createdAt;
        private LocalDateTime deletedAt;
        private Boolean statutActif;
        private String libelle;
        private String pageUrl;
        private String description;
        private String code;

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

        public Builder deletedAt(LocalDateTime deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }

        public Builder statutActif(Boolean statutActif) {
            this.statutActif = statutActif;
            return this;
        }

        public Builder libelle(String libelle) {
            this.libelle = libelle;
            return this;
        }

        public Builder pageUrl(String pageUrl) {
            this.pageUrl = pageUrl;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Fonctionnalite build() {
            return new Fonctionnalite(this);
        }
    }
}
