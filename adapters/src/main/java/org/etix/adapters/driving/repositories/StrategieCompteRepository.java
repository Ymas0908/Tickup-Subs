/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.etix.adapters.driving.repositories;


import org.etix.adapters.entities.Security.StrategieCompteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StrategieCompteRepository extends JpaRepository<StrategieCompteEntity, Integer> {

    Optional<StrategieCompteEntity> findByReference(String reference);

    Optional<StrategieCompteEntity> findByLibelleIgnoreCase(String libelle);

    int countByLibelleIgnoreCase(String libelle);

}
