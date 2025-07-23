package org.etix.adapters.driving.repositories;


import org.etix.adapters.entities.Security.ProfilAccesEntity;
import org.etix.domain.models.Security.ProfilAccesSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author AMINATA
 */
@Repository
public interface ProfilAccessRepository extends JpaRepository<ProfilAccesEntity, Integer> {

    @Override
    @Query("SELECT DISTINCT p FROM ProfilAccesEntity p  ORDER BY p.libelle ASC")
    List<ProfilAccesEntity> findAll();

    @Query("SELECT DISTINCT p FROM ProfilAccesEntity p WHERE p.libelle=:libelle")
    Optional<ProfilAccesEntity> findByLibelle(@Param("libelle") String libelle);

    @Query("SELECT DISTINCT p FROM ProfilAccesEntity p WHERE p.libelle=:libelle")
    Optional<ProfilAccesEntity> findProfilByLibelle(String libelle);

    @Query("SELECT DISTINCT p FROM ProfilAccesEntity p WHERE UPPER(p.libelle)=UPPER(:libelle)")
    Optional<ProfilAccesEntity> findByLibelleIgnoreCase(@Param("libelle") String libelle);

    @Query("SELECT DISTINCT p FROM ProfilAccesEntity p WHERE p.statutActif=:statutActif")
    List<ProfilAccesEntity> findByActive(@Param("statutActif") Boolean active);

    /*    */

    /**
     * Rechercher les profils d'accès (forme résumé pour présentation liste) par
     * leur statut actif
     *
     * @return
     */

    @Query("SELECT DISTINCT new org.etix.domain.models.Security.ProfilAccesSummary (p.id, p.code, p.libelle, p.description, p.statutActif, p.createdAt, p.dateDernModification) "
            + "FROM ProfilAccesEntity p WHERE p.statutActif=:statutActif ORDER BY p.libelle ASC")
    List<ProfilAccesSummary> findAllProfilAccesSummaryDtoByStatutActif(@Param("statutActif") Boolean statutActif);

    @Query("SELECT DISTINCT p FROM ProfilAccesEntity p LEFT JOIN FETCH p.fonctionnalites  ORDER BY p.libelle ASC")
    List<ProfilAccesEntity> findAllProfilsWithFonctionnalites();

    @Query("SELECT DISTINCT p FROM ProfilAccesEntity p LEFT JOIN FETCH p.strategieCompte LEFT JOIN FETCH p.fonctionnalites WHERE p.id IN :profilIds")
    List<ProfilAccesEntity> findByIdIn(@Param("profilIds") Integer[] profilIds);

    /**
     * @param utilisateurId
     * @return
     */
    @Query("SELECT DISTINCT u.profils FROM UtilisateurEntity u WHERE  u.id=:utilisateurId")
    List<ProfilAccesEntity> findProfilAccesSummaryByUtilisateurId(Integer utilisateurId);

}
