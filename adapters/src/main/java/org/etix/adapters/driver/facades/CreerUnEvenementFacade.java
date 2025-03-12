package org.etix.adapters.driver.facades;

import lombok.AllArgsConstructor;
import org.etix.adapters.entities.EvenementEntity;
//import org.etix.domain.models.enumerations.TypeEvenement;
import org.etix.domain.ports.driver.EvenementPort;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class CreerUnEvenementFacade {

    private final EvenementPort evenementPort;


    public EvenementEntity creerUnEvenement(EvenementEntity evenement) {
        return EvenementEntity.toEntity(evenementPort.saveEvenement(evenement.toDomain()));

    }

    public EvenementEntity modifierUnEvenement(EvenementEntity evenement) {
        return EvenementEntity.toEntity(evenementPort.updateEvenement(evenement.toDomain()));
    }

    public void supprimerUnEvenement(EvenementEntity evenement) {
        evenementPort.deleteEvenement(evenement.toDomain());
    }

//    public List<EvenementEntity> getLesEvenementsByTypeEvenement(TypeEvenement typeEvenement) {
//        return evenementPort.getLesEvenementsByTypeEvenement(typeEvenement)
//                .stream()
//                    .map(EvenementEntity::toEntity)
//                        .toList();
//    }

    public List<EvenementEntity> getAllEvenements() {
        return evenementPort.getAllEvenements()
                .stream()
                    .map(EvenementEntity::toEntity)
                        .toList();
    }

    public List<EvenementEntity> getLesEvenementsByNom(String nom) {
        return evenementPort.getLesEvenementsByNom(nom)
                    .stream().map(EvenementEntity::toEntity)
                         .toList();
    }



}
