package org.etix.adapters.config.Security;

import lombok.Getter;
import org.etix.adapters.entities.Security.FonctionnaliteEntity;
import org.etix.adapters.entities.Security.UtilisateurEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationUtilisateur implements UserDetails {

    private static final long serialVersionUID = 8549458138380981159L;

    @Getter
    private final UtilisateurEntity utilisateur;
    @Getter
    private FonctionnaliteEntity fonctionnaliteAccueil;
    private List<SimpleGrantedAuthority> fonctionnalites = new ArrayList<>();

    public ApplicationUtilisateur(UtilisateurEntity utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ApplicationUtilisateur(UtilisateurEntity utilisateur, List<FonctionnaliteEntity> fonctionnalites) {
        this(utilisateur);
        this.fonctionnalites = fonctionnalites.stream()
                .map(item -> new SimpleGrantedAuthority(
                        item.getCode().startsWith("ROLE_") ? item.getCode() : "ROLE_" + item.getCode()))
                .collect(Collectors.toList());
    }

    public ApplicationUtilisateur(UtilisateurEntity utilisateur, List<FonctionnaliteEntity> fonctionnalites,
                                  FonctionnaliteEntity fonctionnaliteAccueil) {
        this(utilisateur, fonctionnalites);
        this.fonctionnaliteAccueil = fonctionnaliteAccueil;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return fonctionnalites;
    }

    @Override
    public String getPassword() {
        return utilisateur.getPassword();
    }

    @Override
    public String getUsername() {
        return utilisateur.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
