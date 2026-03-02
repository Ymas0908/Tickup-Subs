package org.tickup.domain.ports.driving;





import org.tickup.domain.models.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface GererUtilisateurPortDriver {

    public final static String DEFAULT_STRATEGIE_COMPTE = "DEFAULT";

    Utilisateur findUtilisateurSlimById(Integer id);

    List<Utilisateur> getUtilisateurs();


    List<Utilisateur> getAllUtilisateursDto();

    Utilisateur saveUtilisateur(Utilisateur utilisateur);

    Utilisateur reinitialiseMotPasse(Utilisateur utilisateur);

    Utilisateur createUtilisateur(Utilisateur utilisateur);

    Utilisateur updateUtilisateur(Utilisateur utilisateur);

    Utilisateur deleteUtilisateur(Utilisateur utilisateur);

    Utilisateur getUtilisateurById(Integer valueOf);

    Optional<Utilisateur> findUtilisateurByLogin(String login);

    Utilisateur getUtilisateurByLoginAndPassword(String login, String password);

    Utilisateur getUtilisateurConnected();
}
