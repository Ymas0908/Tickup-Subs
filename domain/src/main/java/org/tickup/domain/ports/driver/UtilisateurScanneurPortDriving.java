package org.tickup.domain.ports.driver;

import org.tickup.domain.models.UtilisateurScanneur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurScanneurPortDriving {
    /**
     * Rerchercher un UtilisateurScanneur sans prendre tous les attributs
     *
     * @param id
     * @return
     */
    UtilisateurScanneur findUtilisateurScanneurSlimById(Integer id);

    /**
     * Retourne la liste des UtilisateurScanneurs
     *
     * @return
     */
    List<UtilisateurScanneur> getUtilisateurScanneurs();



    /**
     * la liste de tous les UtilisateurScanneurs
     *
     * @return
     */
    List<UtilisateurScanneur> getAllUtilisateurScanneursDto();

    /**
     * Creer | modifier un UtilisateurScanneur
     *
     * @param UtilisateurScanneur
     * @return
     */
    UtilisateurScanneur saveUtilisateurScanneur(UtilisateurScanneur UtilisateurScanneur);

    UtilisateurScanneur reinitialiseMotPasse(UtilisateurScanneur UtilisateurScanneur);

    /**
     * Creer un nouvel UtilisateurScanneur
     *
     * @param UtilisateurScanneur
     * @return
     */
    UtilisateurScanneur createUtilisateurScanneur(UtilisateurScanneur UtilisateurScanneur);

    /**
     * Modifier un UtilisateurScanneur existante
     *
     * @param UtilisateurScanneur
     * @return
     */
    UtilisateurScanneur updateUtilisateurScanneur(UtilisateurScanneur UtilisateurScanneur);


    /**
     * Supprime un UtilisateurScanneur
     *
     * @param UtilisateurScanneur
     * @return
     */
    UtilisateurScanneur deleteUtilisateurScanneur(UtilisateurScanneur UtilisateurScanneur);

    /**
     * @param valueOf
     * @return
     */
    UtilisateurScanneur getById(Integer valueOf);


    Optional<UtilisateurScanneur> findByLogin(String login);

    UtilisateurScanneur getUtilisateurScanneurByLoginAndPassword(String login, String password);

    UtilisateurScanneur getUtilisateurScanneurConnected();

    void save(UtilisateurScanneur UtilisateurScanneur);
}
