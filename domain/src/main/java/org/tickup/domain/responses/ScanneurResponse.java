package org.tickup.domain.responses;

public class ScanneurResponse {
    private Integer idScanneur;
    private String nom;
    private String prenom;
    private String email;
    private String numeroTelephone;
    private String terminalId;

    public ScanneurResponse() {
    }

    private ScanneurResponse(Builder builder) {
        setIdScanneur(builder.idScanneur);
        setNom(builder.nom);
        setPrenom(builder.prenom);
        setEmail(builder.email);
        setNumeroTelephone(builder.numeroTelephone);
        setTerminalId(builder.terminalId);
    }


    public Integer getIdScanneur() {
        return idScanneur;
    }

    public void setIdScanneur(Integer idScanneur) {
        this.idScanneur = idScanneur;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public static final class Builder {
        private Integer idScanneur;
        private String nom;
        private String prenom;
        private String email;
        private String numeroTelephone;
        private String terminalId;

        public Builder() {
        }

        public Builder idScanneur(Integer val) {
            idScanneur = val;
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

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder numeroTelephone(String val) {
            numeroTelephone = val;
            return this;
        }

        public Builder terminalId(String val) {
            terminalId = val;
            return this;
        }

        public ScanneurResponse build() {
            return new ScanneurResponse(this);
        }
    }
}
