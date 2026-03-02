package org.tickup.domain.services;




import org.tickup.domain.ddd.DomaineService;
import org.tickup.domain.models.Utilisateur;
import org.tickup.domain.ports.driving.GererUtilisateurPortDriver;
import org.tickup.domain.ports.driver.UtilisateurPortDriving;

import java.util.List;
import java.util.Optional;

@DomaineService
public class GestionUtilisateurService implements GererUtilisateurPortDriver {
    private final UtilisateurPortDriving utilisateurPortDriving;

    public GestionUtilisateurService(UtilisateurPortDriving utilisateurPortDriving) {
        this.utilisateurPortDriving = utilisateurPortDriving;
    }

    @Override
    public Utilisateur findUtilisateurSlimById(Integer id) {
        return utilisateurPortDriving.findUtilisateurSlimById(id);
    }

    @Override
    public List<Utilisateur> getUtilisateurs() {
        return null;
    }



    @Override
    public List<Utilisateur> getAllUtilisateursDto() {
        return null;
    }

    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Utilisateur reinitialiseMotPasse(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Utilisateur deleteUtilisateur(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Utilisateur getUtilisateurById(Integer valueOf) {
        return null;
    }

    @Override
    public Optional<Utilisateur> findUtilisateurByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public Utilisateur getUtilisateurByLoginAndPassword(String login, String password) {
        return null;
    }

    @Override
    public Utilisateur getUtilisateurConnected() {
        return utilisateurPortDriving.getUtilisateurConnected();
    }
}
