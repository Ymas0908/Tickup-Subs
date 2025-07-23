/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.etix.domain.ports.driving;



import org.etix.domain.models.Security.StrategieCompte;

import java.util.List;
import java.util.Optional;

public interface StrategieCompteRepo {

    public final static String DEFAULT_STRATEGIE_COMPTE = "DEFAULT";

    /**
     * Retourne la liste des strategieComptes
     *
     * @return
     */
    List<StrategieCompte> getStrategieComptes();

    /**
     * Creer | modifier une strategieCompte
     *
     * @param strategieCompte
     * @return
     */
    StrategieCompte saveStrategieCompte(StrategieCompte strategieCompte);

    /**
     * Creer une nouvelle strategieCompte
     *
     * @param strategieCompte
     * @return
     */
    StrategieCompte createStrategieCompte(StrategieCompte strategieCompte);

    /**
     * Modifier une strategieCompte existante
     *
     * @param strategieCompte
     * @return
     */
    StrategieCompte updateStrategieCompte(StrategieCompte strategieCompte);

    /**
     * Supprime une strategieCompte
     *
     * @param strategieCompte
     */
    void deleteStrategieCompte(StrategieCompte strategieCompte);

    /**
     * @param id
     * @return
     */
    Optional<StrategieCompte> findById(Integer id);

    /**
     * @param id
     * @return
     */
    StrategieCompte getById(Integer id);

    /**
     * @param object
     * @return
     */
    Optional<StrategieCompte> findByLibelleIgnoreCase(String libelle);
}
