package org.tickup.domain.models;

public class Usager {
    private Integer idUsager;
    private String terminalId;
    private  String refUsager;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String email;
    private String telephone;

    public Usager() {
    }

    private Usager(Builder builder) {
        setIdUsager(builder.idUsager);
        setTerminalId(builder.terminalId);
        setRefUsager(builder.refUsager);
        setNom(builder.nom);
        setPrenom(builder.prenom);
        setDateNaissance(builder.dateNaissance);
        setEmail(builder.email);
        setTelephone(builder.telephone);
    }


    public Integer getIdUsager() {
        return idUsager;
    }

    public void setIdUsager(Integer idUsager) {
        this.idUsager = idUsager;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getRefUsager() {
        return refUsager;
    }

    public void setRefUsager(String refUsager) {
        this.refUsager = refUsager;
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

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public static final class Builder {
        private Integer idUsager;
        private String terminalId;
        private String refUsager;
        private String nom;
        private String prenom;
        private String dateNaissance;
        private String email;
        private String telephone;

        public Builder() {
        }

        public Builder idUsager(Integer val) {
            idUsager = val;
            return this;
        }

        public Builder terminalId(String val) {
            terminalId = val;
            return this;
        }

        public Builder refUsager(String val) {
            refUsager = val;
            return this;
        }

        public Builder nom(String val) {
            nom = val;
            return this;
        }

        public Builder prenom(String val) {
            prenom = val;
            return this;
        }

        public Builder dateNaissance(String val) {
            dateNaissance = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder telephone(String val) {
            telephone = val;
            return this;
        }

        public Usager build() {
            return new Usager(this);
        }
    }
}
