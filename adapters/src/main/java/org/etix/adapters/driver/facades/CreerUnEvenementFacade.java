package org.etix.adapters.driver.facades;

import lombok.AllArgsConstructor;
import org.etix.adapters.entities.EvenementEntity;
//import org.etix.domain.models.enumerations.TypeEvenement;
import org.etix.domain.ports.driver.EvenementPort;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Component
public class CreerUnEvenementFacade {

    private final EvenementPort evenementPort;


    public EvenementEntity creerUnEvenement(EvenementEntity evenement) {
        return EvenementEntity.toEntity(evenementPort.saveEvenement(evenement.toDomain()));

    }
    public void uploadFile(MultipartFile file, Integer idEvenement) {
        evenementPort.uploadFile(file,idEvenement);
    }

    public EvenementEntity modifierUnEvenement(EvenementEntity evenement) {
        return EvenementEntity.toEntity(evenementPort.updateEvenement(evenement.toDomain()));
    }

    public void deleteEvenement(Integer idEvenement) {
        evenementPort.deleteEvenement(idEvenement);


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

    public List<EvenementEntity> getLesEvenementsByLibelle(String libelle) {
        return evenementPort.getLesEvenementsByLibelle(libelle)
                .stream().map(EvenementEntity::toEntity)
                .toList();
    }


    public Object getUrlImageEvenement(String refEvenement) {
        return evenementPort.getUrlImageEvenement(refEvenement);
    }
}
