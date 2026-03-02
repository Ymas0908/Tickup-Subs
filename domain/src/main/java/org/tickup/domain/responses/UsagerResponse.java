package org.tickup.domain.responses;

public class UsagerResponse {
    
    private Integer idUsager;
    private String nom;
    private String prenom;
    private String email;
    private String numeroTelephone;
    private String terminalId;


    public UsagerResponse() {
    }

    public UsagerResponse(Integer idUsager,String nom, String prenom, String email, String numeroTelephone, String terminalId) {
        this.idUsager = idUsager;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numeroTelephone = numeroTelephone;
        this.terminalId = terminalId;
    }


    public Integer getIdUsager() {
        return idUsager;
    }

    public void setIdUsager(Integer idUsager) {
        this.idUsager = idUsager;
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

    private UsagerResponse(Builder builder) {
        setIdUsager(builder.idUsager);
        setNom(builder.nom);
        setPrenom(builder.prenom);
        setEmail(builder.email);
        setNumeroTelephone(builder.numeroTelephone);
        setTerminalId(builder.terminalId);
    }


    public static final class Builder {
        private String nom;
        private String prenom;
        private String email;
        private String numeroTelephone;
        private String terminalId;
        private Integer idUsager;

        public Builder() {
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

        public UsagerResponse build() {
            return new UsagerResponse(this);
        }

        public Builder idUsager(Integer val) {
            idUsager = val;
            return this;
        }
    }
}
