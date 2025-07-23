/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.etix.adapters.driving.repositories;


import org.etix.adapters.entities.Security.ProfilAccesEntity;
import org.etix.adapters.entities.Security.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity, Integer> {


    @Override
    @Query("SELECT DISTINCT u FROM UtilisateurEntity u LEFT JOIN FETCH u.profils  WHERE u.id=:id")
    Optional<UtilisateurEntity> findById(@Param("id") Integer id);

    @Query("SELECT DISTINCT u FROM UtilisateurEntity u LEFT JOIN FETCH u.profils p WHERE u.login=:login")
    Optional<UtilisateurEntity> findByLogin(@Param("login") String login);

    /**
     * Rechercher un UtilisateurEntity par son adresse email
     *
     * @param email
     * @return
     */
    @Query("SELECT DISTINCT u FROM UtilisateurEntity u LEFT JOIN FETCH u.profils  WHERE u.email=:email")
    Optional<UtilisateurEntity> findByEmail(@Param("email") String email);

    /**
     * Compter le nombre UtilisateurEntity avec le login
     *
     * @param login
     * @return
     */
    int countByLogin(String login);

    /**
     * Compter le nombre UtilisateurEntity avec l'email
     *
     * @param email
     * @return
     */
    int countByEmail(String email);


    @Query("SELECT DISTINCT u FROM UtilisateurEntity u WHERE :profil MEMBER OF u.profils")
    List<UtilisateurEntity> getUtilisateurByProfil(ProfilAccesEntity profil);


    @Query("SELECT u FROM UtilisateurEntity u WHERE u.deletedAt is null")
    List<UtilisateurEntity> getUtilisateursToDisplay();

    UtilisateurEntity findByLoginAndAndPassword(String login, String password);

    @Query("SELECT DISTINCT u FROM UtilisateurEntity u WHERE u.id=:principalId")
    UtilisateurEntity rechercherUtilisateurParPrincipal(Integer principalId);
}
