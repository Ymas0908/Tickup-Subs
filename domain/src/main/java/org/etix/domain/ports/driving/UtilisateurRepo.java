package org.etix.domain.ports.driving;



import org.etix.domain.models.Security.ProfilAcces;
import org.etix.domain.models.Security.Utilisateur;
import org.etix.domain.models.Security.UtilisateurSlim;

import java.util.List;
import java.util.Optional;


public interface UtilisateurRepo {


    /**
     * Retourne la liste des utilisateurs
     *
     * @return
     */
    List<Utilisateur> getUtilisateurs();

    /**
     * Retourne la liste des utilisateurs
     *
     * @return
     */
    List<UtilisateurSlim> getUtilisateursDto();

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


    /**
     * Mettre à jour les profils d'accès d'un utilisateur
     *
     * @param utilisateur
     * @param profilSelectedId
     * @return
     */
    Utilisateur updateProfilAccess(Utilisateur utilisateur, Integer[] profilSelectedId);


    List<UtilisateurSlim> utilisateuersSlimDtoSelonProfil(ProfilAcces profilAcces);


    Optional<Utilisateur> findByLogin(String login);

    Utilisateur getUtilisateurByLoginAndPassword(String login, String password);

    Utilisateur getUtilisateurConnected();
}
