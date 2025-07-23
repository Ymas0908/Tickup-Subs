/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.etix.domain.ports.driving;



import org.etix.domain.models.Security.Fonctionnalite;
import org.etix.domain.models.Security.ProfilAcces;
import org.etix.domain.models.Security.ProfilAccesSummary;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Kassi
 */
public interface ProfilAccessRepo {

    /**
     * @param id
     * @return
     */
    ProfilAcces getById(Integer id);

    /**
     * Retourne la liste des profils
     *
     * @return
     */
    List<ProfilAcces> getProfils();


    /**
     * Creer | modifier une profil
     *
     * @param profil
     * @return
     */
    ProfilAcces saveProfil(ProfilAcces profil);

    /**
     * Creer une nouvelle profil
     *
     * @param profil
     * @return
     */
    ProfilAcces createProfil(ProfilAcces profil);

    /**
     * Modifier une profil existante
     *
     * @param profil
     * @return
     */
    ProfilAcces updateProfil(ProfilAcces profil);

    /**
     * Supprime une profil
     *
     * @param profil
     */
    void deleteProfil(ProfilAcces profil);


    /**
     * @param roleName
     * @param adminFeatures
     * @return
     */
    ProfilAcces createProfilIfNotExists(String roleName, Set<Fonctionnalite> adminFeatures);

    /**
     * @param name
     * @return
     */
    Optional<ProfilAcces> rechercherParName(String name);

    /**
     * Liste des profils actifs
     *
     * @return
     */
    List<ProfilAcces> getActiveProfils();

    /**
     * @param libelle
     * @return
     */
    ProfilAcces findProfilByLibelle(String libelle);


    /**
     * @return
     */
    List<ProfilAccesSummary> getActiveProfilsSummary();

    /**
     * @return
     */
    List<ProfilAcces> findByIdIn(Integer[] profilIds);

    /**
     * @param utilisateurId
     * @return
     */
    List<ProfilAccesSummary> getProfilAccesSummaryByUtilisateurId(Integer utilisateurId);


}
