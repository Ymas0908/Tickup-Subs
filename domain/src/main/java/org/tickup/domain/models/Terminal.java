package org.tickup.domain.models;




import org.tickup.domain.models.enums.StatutTerminal;

import java.time.LocalDateTime;


public class Terminal {

    private Integer id;
    private String numeroSerie;
    private String marque;
    private String model;
    private String adresseMac;
    private String imei;
    private StatutTerminal statutTerminal;
    private LocalDateTime dateHeureCreation;
    private LocalDateTime dateHeureMAJour;
    private String reference;
    private String addressIp;


    public Terminal() {
    }

    private Terminal(Builder builder) {
        setId(builder.id);
        setNumeroSerie(builder.numeroSerie);
        setMarque(builder.marque);
        setModel(builder.model);
        setAdresseMac(builder.adresseMac);
        setImei(builder.imei);
        setStatutTerminal(builder.statutTerminal);
        setDateHeureCreation(builder.dateHeureCreation);
        setDateHeureMAJour(builder.dateHeureMAJour);
        setReference(builder.reference);
        setAddressIp(builder.addressIp);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAdresseMac() {
        return adresseMac;
    }

    public void setAdresseMac(String adresseMac) {
        this.adresseMac = adresseMac;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public StatutTerminal getStatutTerminal() {
        return statutTerminal;
    }

    public void setStatutTerminal(StatutTerminal statutTerminal) {
        this.statutTerminal = statutTerminal;
    }


    public LocalDateTime getDateHeureCreation() {
        return dateHeureCreation;
    }

    public void setDateHeureCreation(LocalDateTime dateHeureCreation) {
        this.dateHeureCreation = dateHeureCreation;
    }

    public LocalDateTime getDateHeureMAJour() {
        return dateHeureMAJour;
    }

    public void setDateHeureMAJour(LocalDateTime dateHeureMAJour) {
        this.dateHeureMAJour = dateHeureMAJour;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAddressIp() {
        return addressIp;
    }

    public void setAddressIp(String addressIp) {
        this.addressIp = addressIp;
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "id=" + id +
                ", numeroSerie='" + numeroSerie + '\'' +
                ", marque='" + marque + '\'' +
                ", model='" + model + '\'' +
                ", adresseMac='" + adresseMac + '\'' +
                ", imei='" + imei + '\'' +
                ", statutTerminal=" + statutTerminal +
                ", dateHeureCreation=" + dateHeureCreation +
                ", dateHeureMAJour=" + dateHeureMAJour +
                ", reference='" + reference + '\'' +
                ", addressIp='" + addressIp + '\'' +
                '}';
    }

    public static final class Builder {
        private Integer id;
        private String numeroSerie;
        private String marque;
        private String model;
        private String adresseMac;
        private String imei;
        private StatutTerminal statutTerminal;
        private LocalDateTime dateHeureCreation;
        private LocalDateTime dateHeureMAJour;
        private String reference;
        private String addressIp;

        public Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder numeroSerie(String val) {
            numeroSerie = val;
            return this;
        }

        public Builder marque(String val) {
            marque = val;
            return this;
        }

        public Builder model(String val) {
            model = val;
            return this;
        }

        public Builder adresseMac(String val) {
            adresseMac = val;
            return this;
        }


        public Builder imei(String val) {
            imei = val;
            return this;
        }

        public Builder statutTerminal(StatutTerminal val) {
            statutTerminal = val;
            return this;
        }


        public Builder dateHeureCreation(LocalDateTime val) {
            dateHeureCreation = val;
            return this;
        }

        public Builder dateHeureMAJour(LocalDateTime val) {
            dateHeureMAJour = val;
            return this;
        }

        public Builder reference(String val) {
            reference = val;
            return this;
        }

        public Builder addressIp(String val) {
            addressIp = val;
            return this;
        }

        public Terminal build() {
            return new Terminal(this);
        }
    }
}
