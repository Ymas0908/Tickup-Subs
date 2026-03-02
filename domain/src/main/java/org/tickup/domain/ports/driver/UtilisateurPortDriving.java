package org.tickup.domain.ports.driver;



import org.tickup.domain.models.Utilisateur;

import java.util.List;
import java.util.Optional;
public interface UtilisateurPortDriving {

    /**
     * Rerchercher un utilisateur sans prendre tous les attributs
     *
     * @param id
     * @return
     */
    Utilisateur findUtilisateurSlimById(Integer id);

    /**
     * Retourne la liste des utilisateurs
     *
     * @return
     */
    List<Utilisateur> getUtilisateurs();



    /**
     * la liste de tous les utilisateurs
     *
     * @return
     */
    List<Utilisateur> getAllUtilisateursDto();

    /**
     * Creer | modifier un utilisateur
     *
     * @param utilisateur
     * @return
     */
    Utilisateur saveUtilisateur(Utilisateur utilisateur);

    Utilisateur reinitialiseMotPasse(Utilisateur utilisateur);

    /**
     * Creer un nouvel utilisateur
     *
     * @param utilisateur
     * @return
     */
    Utilisateur createUtilisateur(Utilisateur utilisateur);

    /**
     * Modifier un utilisateur existante
     *
     * @param utilisateur
     * @return
     */
    Utilisateur updateUtilisateur(Utilisateur utilisateur);


    /**
     * Supprime un utilisateur
     *
     * @param utilisateur
     * @return
     */
    Utilisateur deleteUtilisateur(Utilisateur utilisateur);

    /**
     * @param valueOf
     * @return
     */
    Utilisateur getById(Integer valueOf);


    Optional<Utilisateur> findByLogin(String login);

    Utilisateur getUtilisateurByLoginAndPassword(String login, String password);

    Utilisateur getUtilisateurConnected();

    void save(Utilisateur utilisateur);
}
