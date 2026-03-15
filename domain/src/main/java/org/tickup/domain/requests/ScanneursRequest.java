package org.tickup.domain.requests;

public class ScanneursRequest {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String dateNaissance;

    public ScanneursRequest() {
    }

    private ScanneursRequest(Builder builder) {
        setNom(builder.nom);
        setPrenom(builder.prenom);
        setEmail(builder.email);
        setTelephone(builder.telephone);
        setDateNaissance(builder.dateNaissance);
    }


    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static final class Builder {
        private String nom;
        private String prenom;
        private String email;
        private String telephone;
        private String dateNaissance;

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

        public Builder telephone(String val) {
            telephone = val;
            return this;
        }

        public Builder dateNaissance(String val) {
            dateNaissance = val;
            return this;
        }

        public ScanneursRequest build() {
            return new ScanneursRequest(this);
        }
    }
}
