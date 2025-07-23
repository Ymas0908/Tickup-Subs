package org.etix.domain.ports.driving;



import org.etix.domain.models.Security.Fonctionnalite;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author ITCENTREX
 */
public interface FonctionnaliteRepo {

    /**
     * Retourne la liste des Fonctionnalités
     *
     * @return
     */
    List<Fonctionnalite> getFonctionnalites();


    /**
     * @param dateA
     * @param dateB
     * @return
     */
    List<Fonctionnalite> searchFeature(LocalDateTime dateA, LocalDateTime dateB);

    /**
     * Creer | modifier une fonctionnalité
     *
     * @param fonctionnalite
     * @return
     */
    Fonctionnalite saveFonctionnalite(Fonctionnalite fonctionnalite);

    /**
     * Creer une nouvelle fonctionnalité
     *
     * @param fonctionnalite
     * @return
     */
    Fonctionnalite createFonctionnalite(Fonctionnalite fonctionnalite);

    /**
     * Modifier une fonctionnalite existante
     *
     * @param fonctionnalite
     * @return
     */
    Fonctionnalite updateFonctionnalite(Fonctionnalite fonctionnalite);

    /**
     * Supprime une fonctionnalité
     *
     * @param fonctionnalite
     */
    void deleteFonctionnalite(Fonctionnalite fonctionnalite);

    /**
     * @param Id
     * @return
     */
    Fonctionnalite getById(Integer Id);

    /**
     * @param id
     * @return
     */
    Optional<Fonctionnalite> findById(Integer id);

    /**
     * @param libelle
     * @return
     */
    Fonctionnalite rechercherParLibelle(String libelle);


    /**
     * @param profilsId
     * @return
     */
    List<Fonctionnalite> getFonctionnalitesByProfilIdsIn(Set<Integer> profilsId);

}
