package org.tickup.domain.models;

public class Scanneur {
    private Integer idScanneur;
    private String terminalId;
    private  String refScanneur;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String email;
    private String telephone;

    public Scanneur() {
    }

    private Scanneur(Builder builder) {
        setIdScanneur(builder.idScanneur);
        setTerminalId(builder.terminalId);
        setRefScanneur(builder.refScanneur);
        setNom(builder.nom);
        setPrenom(builder.prenom);
        setDateNaissance(builder.dateNaissance);
        setEmail(builder.email);
        setTelephone(builder.telephone);
    }


    public Integer getIdScanneur() {
        return idScanneur;
    }

    public void setIdScanneur(Integer idScanneur) {
        this.idScanneur = idScanneur;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getRefScanneur() {
        return refScanneur;
    }

    public void setRefScanneur(String refScanneur) {
        this.refScanneur = refScanneur;
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
        private Integer idScanneur;
        private String terminalId;
        private String refScanneur;
        private String nom;
        private String prenom;
        private String dateNaissance;
        private String email;
        private String telephone;

        public Builder() {
        }

        public Builder idScanneur(Integer val) {
            idScanneur = val;
            return this;
        }

        public Builder terminalId(String val) {
            terminalId = val;
            return this;
        }

        public Builder refScanneur(String val) {
            refScanneur = val;
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

        public Scanneur build() {
            return new Scanneur(this);
        }
    }
}
