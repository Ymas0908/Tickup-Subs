package org.tickup.domain.requests;

public class UsagersRequest {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String dateNaissance;

    public UsagersRequest() {
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    private UsagersRequest(Builder builder) {
        nom = builder.nom;
        prenom = builder.prenom;
        email = builder.email;
        telephone = builder.telephone;
        dateNaissance = builder.dateNaissance;
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

        public UsagersRequest build() {
            return new UsagersRequest(this);
        }
    }
}
