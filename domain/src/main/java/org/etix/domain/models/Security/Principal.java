package org.etix.domain.models.Security;


import org.etix.domain.models.enumerations.Genre;

public interface Principal {

    Integer getId();


    String getEmail();

    String getLogin();

    String getNom();

    String getPrenom();

    Genre getGenre();

    String getTelephone();

    Boolean getStatutActif();


    default String userInfos() {
        return String.format("%s %s (%s)", getNom(), getPrenom(), getEmail());
    }

    default String fullName() {
        return String.format("%s %s", getNom(), getPrenom());
    }

}
