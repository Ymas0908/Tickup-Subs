package org.etix.adapters.driving.impl;

import lombok.AllArgsConstructor;
import org.etix.adapters.driving.repositories.EvenementRepository;
import org.etix.adapters.entities.EvenementEntity;
import org.etix.domain.models.Evenement;
//import org.etix.domain.models.enumerations.TypeEvenement;
import org.etix.domain.ports.driving.EvenementRepo;
import org.springframework.stereotype.Component;

import java.util.List;
@AllArgsConstructor
@Component
public class EvenementImpl implements EvenementRepo {

    private final EvenementRepository evenementRepository;
    @Override
    public Evenement saveEvenement(Evenement evenement) {
        return evenementRepository.save(EvenementEntity.toEntity(evenement)).toDomain();
    }

    @Override
    public void deleteEvenement(Evenement evenement) {
        evenementRepository.delete(EvenementEntity.toEntity(evenement));
    }

    @Override
    public Evenement updateEvenement(Evenement idEvenement) {
        return evenementRepository.save(EvenementEntity.toEntity(idEvenement)).toDomain();
    }

    @Override
    public Evenement getEvenementById(Integer idEvenement) {
        return evenementRepository.findById(idEvenement)
                .orElseThrow(() -> new RuntimeException("Evenement introuvable")).toDomain();
    }

    @Override
    public List<Evenement> getLesEvenementsByNom(String nom) {
        return evenementRepository.findByNom(nom).stream().map(EvenementEntity::toDomain).toList();
    }

//    @Override
//    public List<Evenement> getLesEvenementsByTypeEvenement(TypeEvenement typeEvenement) {
//        return evenementRepository.findByTypeEvenement(typeEvenement)
//                .stream()
//                    .map(EvenementEntity::toDomain)
//                        .toList();
//    }

    @Override
    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll()
                .stream()
                    .map(EvenementEntity::toDomain)
                        .toList();
    }

    @Override
    public List<Evenement> getLesEvenementsByLibelle(String libelle) {
        return evenementRepository.findByLibelle(libelle).stream().map(EvenementEntity::toDomain).toList();
    }

    @Override
    public Object getUrlImageEvenement(Integer idEvenement) {
        return evenementRepository.getUrlImageEvenement(idEvenement);
    }
}
