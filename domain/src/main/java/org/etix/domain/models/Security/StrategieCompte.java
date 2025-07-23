package org.etix.domain.models.Security;



import org.etix.domain.utils.Constant;
import org.etix.domain.utils.PasswordRegExGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;


public class StrategieCompte implements Serializable {

    private static final long serialVersionUID = 4821791910295299999L;

    private static final String SPECIAL_CHARS = "#?!@$%^&*-";
    protected LocalDateTime createdAt;
    protected LocalDateTime dateDernModification;

    private Integer id;
    private String libelle;

    private String reference;

    private Integer dureeAlertePassword;

    private Integer dureeValiditePassword;

    private Integer longueurMinimalePassword;

    private Integer nbreCaractereMajuscule;

    private Integer nbrCaractereSpeciaux;

    private Integer nbrChiffre;

    private Integer nbrTentativeEchec;

    private String generatedRegex;

    public StrategieCompte(LocalDateTime createdAt, LocalDateTime dateDernModification, Integer id, String libelle, String reference, Integer dureeAlertePassword, Integer dureeValiditePassword, Integer longueurMinimalePassword, Integer nbreCaractereMajuscule, Integer nbrCaractereSpeciaux, Integer nbrChiffre, Integer nbrTentativeEchec, String generatedRegex) {
        this.createdAt = createdAt;
        this.dateDernModification = dateDernModification;
        this.id = id;
        this.libelle = libelle;
        this.reference = reference;
        this.dureeAlertePassword = dureeAlertePassword;
        this.dureeValiditePassword = dureeValiditePassword;
        this.longueurMinimalePassword = longueurMinimalePassword;
        this.nbreCaractereMajuscule = nbreCaractereMajuscule;
        this.nbrCaractereSpeciaux = nbrCaractereSpeciaux;
        this.nbrChiffre = nbrChiffre;
        this.nbrTentativeEchec = nbrTentativeEchec;
        this.generatedRegex = generatedRegex;
    }

    public StrategieCompte() {
    }

    private StrategieCompte(Builder builder) {
        setCreatedAt(builder.createdAt);
        setDateDernModification(builder.dateDernModification);
        setId(builder.id);
        setLibelle(builder.libelle);
        setReference(builder.reference);
        setDureeAlertePassword(builder.dureeAlertePassword);
        setDureeValiditePassword(builder.dureeValiditePassword);
        setLongueurMinimalePassword(builder.longueurMinimalePassword);
        setNbreCaractereMajuscule(builder.nbreCaractereMajuscule);
        setNbrCaractereSpeciaux(builder.nbrCaractereSpeciaux);
        setNbrChiffre(builder.nbrChiffre);
        setNbrTentativeEchec(builder.nbrTentativeEchec);
        setGeneratedRegex(builder.generatedRegex);
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getDureeAlertePassword() {
        return dureeAlertePassword;
    }

    public void setDureeAlertePassword(Integer dureeAlertePassword) {
        this.dureeAlertePassword = dureeAlertePassword;
    }

    public Integer getDureeValiditePassword() {
        return dureeValiditePassword;
    }

    public void setDureeValiditePassword(Integer dureeValiditePassword) {
        this.dureeValiditePassword = dureeValiditePassword;
    }

    public Integer getLongueurMinimalePassword() {
        return longueurMinimalePassword;
    }

    public void setLongueurMinimalePassword(Integer longueurMinimalePassword) {
        this.longueurMinimalePassword = longueurMinimalePassword;
    }

    public Integer getNbreCaractereMajuscule() {
        return nbreCaractereMajuscule;
    }

    public void setNbreCaractereMajuscule(Integer nbreCaractereMajuscule) {
        this.nbreCaractereMajuscule = nbreCaractereMajuscule;
    }

    public Integer getNbrCaractereSpeciaux() {
        return nbrCaractereSpeciaux;
    }

    public void setNbrCaractereSpeciaux(Integer nbrCaractereSpeciaux) {
        this.nbrCaractereSpeciaux = nbrCaractereSpeciaux;
    }

    public Integer getNbrChiffre() {
        return nbrChiffre;
    }

    public void setNbrChiffre(Integer nbrChiffre) {
        this.nbrChiffre = nbrChiffre;
    }

    public Integer getNbrTentativeEchec() {
        return nbrTentativeEchec;
    }

    public void setNbrTentativeEchec(Integer nbrTentativeEchec) {
        this.nbrTentativeEchec = nbrTentativeEchec;
    }

    public String getGeneratedRegex() {
        return generatedRegex;
    }

    public void setGeneratedRegex(String generatedRegex) {
        this.generatedRegex = generatedRegex;
    }


    public static final class Builder {
        private LocalDateTime createdAt;
        private LocalDateTime dateDernModification;
        private Integer id;
        private String libelle;
        private String reference;
        private Integer dureeAlertePassword;
        private Integer dureeValiditePassword;
        private Integer longueurMinimalePassword;
        private Integer nbreCaractereMajuscule;
        private Integer nbrCaractereSpeciaux;
        private Integer nbrChiffre;
        private Integer nbrTentativeEchec;
        private String generatedRegex;

        public Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder dateDernModification(LocalDateTime dateDernModification) {
            this.dateDernModification = dateDernModification;
            return this;
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder libelle(String libelle) {
            this.libelle = libelle;
            return this;
        }

        public Builder reference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder dureeAlertePassword(Integer dureeAlertePassword) {
            this.dureeAlertePassword = dureeAlertePassword;
            return this;
        }

        public Builder dureeValiditePassword(Integer dureeValiditePassword) {
            this.dureeValiditePassword = dureeValiditePassword;
            return this;
        }

        public Builder longueurMinimalePassword(Integer longueurMinimalePassword) {
            this.longueurMinimalePassword = longueurMinimalePassword;
            return this;
        }

        public Builder nbreCaractereMajuscule(Integer nbreCaractereMajuscule) {
            this.nbreCaractereMajuscule = nbreCaractereMajuscule;
            return this;
        }

        public Builder nbrCaractereSpeciaux(Integer nbrCaractereSpeciaux) {
            this.nbrCaractereSpeciaux = nbrCaractereSpeciaux;
            return this;
        }

        public Builder nbrChiffre(Integer nbrChiffre) {
            this.nbrChiffre = nbrChiffre;
            return this;
        }

        public Builder nbrTentativeEchec(Integer nbrTentativeEchec) {
            this.nbrTentativeEchec = nbrTentativeEchec;
            return this;
        }

        public Builder generatedRegex(String generatedRegex) {
            this.generatedRegex = generatedRegex;
            return this;
        }

        public StrategieCompte build() {
            return new StrategieCompte(this);
        }

        /**
         * @return
         */
        public String generateRegex() {
            return PasswordRegExGenerator.generateRegex(longueurMinimalePassword, nbreCaractereMajuscule,
                    nbrCaractereSpeciaux, nbrChiffre, SPECIAL_CHARS);
        }

        /**
         * @return
         */
        public String generateReference() {
            return String.format("LM%s-NM%s-NS%s-NC%s-NT%s-DV%s-DA%s", this.longueurMinimalePassword,
                    this.nbreCaractereMajuscule, this.nbrCaractereSpeciaux, this.nbrChiffre,
                    this.nbrTentativeEchec, this.dureeValiditePassword, this.dureeAlertePassword);
        }

        public boolean isRemovable() {
            return id != null && !Constant.DEFAULT_CODE.equals(libelle);
        }

        public boolean isEditable() {
            return !Constant.DEFAULT_CODE.equals(libelle);
        }

    }
}
