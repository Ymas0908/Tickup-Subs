package org.etix.adapters.driving.repositories;

import org.etix.adapters.entities.EvenementEntity;
//import org.etix.domain.models.enumerations.TypeEvenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EvenementRepository extends JpaRepository<EvenementEntity, Integer> {

//    @Query("SELECT e FROM EvenementEntity e WHERE e.typeEvenement = :typeEvenement")
//    List<EvenementEntity> findByTypeEvenement(TypeEvenement typeEvenement);

    @Query("SELECT e FROM EvenementEntity e WHERE e.nom = :nom")
    List<EvenementEntity> findByNom(String nom);


    @Query("SELECT e FROM EvenementEntity e WHERE e.libelle = :libelle")
    List<EvenementEntity> findByLibelle(String libelle);

    @Query("SELECT e FROM EvenementEntity e WHERE e.urlImage = :idEvenement")
    Object getUrlImageEvenement(Integer idEvenement);
}
