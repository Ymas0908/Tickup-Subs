package org.etix.adapters.driving.repositories;

import org.etix.adapters.entities.EvenementEntity;
//import org.etix.domain.models.enumerations.TypeEvenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface EvenementRepository extends JpaRepository<EvenementEntity, Integer> {

//    @Query("SELECT e FROM EvenementEntity e WHERE e.typeEvenement = :typeEvenement")
//    List<EvenementEntity> findByTypeEvenement(TypeEvenement typeEvenement);

    @Query("SELECT e FROM EvenementEntity e WHERE e.nom = :nom")
    List<EvenementEntity> findByNom(String nom);

    @Query("SELECT e FROM EvenementEntity e WHERE e.reference = :reference")
    EvenementEntity findByReference(String reference);

    @Query("SELECT e FROM EvenementEntity e WHERE e.libelle = :libelle")
    List<EvenementEntity> findByLibelle(String libelle);

    @Query("SELECT e FROM EvenementEntity e WHERE e.urlImage = :refEvenement")
    Object getUrlImageEvenement(String refEvenement);

    @Query("SELECT COUNT(*) FROM EvenementEntity")
    Integer getTotalEvenements();

    @Query("SELECT COUNT(*) FROM EvenementEntity e WHERE e.dateHeureEvenement BETWEEN :startDate AND :endDate")
    Integer getTotalEvenementsParPeriode(LocalDateTime startDate, LocalDateTime endDate);
}
