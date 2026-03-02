package org.tickup.adapters.ports.driving.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tickup.adapters.entites.UtilisateurEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity, Integer> {


    @Query("SELECT DISTINCT u FROM UtilisateurEntity u  WHERE u.login=:login")
    Optional<UtilisateurEntity> findByLogin(@Param("login") String login);



 



    /**
     * Compter le nombre UtilisateurEntity avec le login
     *
     * @param login
     * @return
     */
    int countByLogin(String login);


//    /**
//     * Compter le nombre UtilisateurEntity avec le code d'exploitant
//     *
//     * @param codeExploitant
//     * @return
//     */
//    int countByCodeExploitant(String codeExploitant);


    @Query("SELECT u FROM UtilisateurEntity u WHERE u.deletedAt is null")
    List<UtilisateurEntity> getUtilisateursToDisplay();

    @Query("SELECT u FROM UtilisateurEntity u ")
    List<UtilisateurEntity> getUtilisateurs();

    UtilisateurEntity findByLoginAndPassword(String login, String password);

    @Query("SELECT DISTINCT u FROM UtilisateurEntity u WHERE u.id=:principalId")
    UtilisateurEntity rechercherUtilisateurParPrincipal(@Param("principalId") Integer principalId);
}
