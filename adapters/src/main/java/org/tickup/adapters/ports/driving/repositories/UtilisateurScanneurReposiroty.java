package org.tickup.adapters.ports.driving.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tickup.adapters.entites.UtlitisateurScanneurEntity;

import java.util.List;
import java.util.Optional;

public interface UtilisateurScanneurReposiroty  extends JpaRepository<UtlitisateurScanneurEntity, Integer> {
    @Query("SELECT DISTINCT u FROM UtlitisateurScanneurEntity u  WHERE u.login=:login")
    Optional<UtlitisateurScanneurEntity> findByLogin(@Param("login") String login);







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


    @Query("SELECT u FROM UtlitisateurScanneurEntity u WHERE u.deletedAt is null")
    List<UtlitisateurScanneurEntity> getUtilisateursToDisplay();

    @Query("SELECT u FROM UtlitisateurScanneurEntity u ")
    List<UtlitisateurScanneurEntity> getUtilisateurs();

    UtlitisateurScanneurEntity findByLoginAndPassword(String login, String password);

    @Query("SELECT DISTINCT u FROM UtlitisateurScanneurEntity u WHERE u.id=:principalId")
    UtlitisateurScanneurEntity rechercherUtilisateurScanneurParPrincipal(@Param("principalId") Integer principalId);
}
