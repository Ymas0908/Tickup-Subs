package org.etix.domain.services;

import org.etix.domain.ddd.DomaineService;
import org.etix.domain.models.Evenement;
//import org.etix.domain.models.enumerations.TypeEvenement;
import org.etix.domain.ports.driver.EvenementPort;
import org.etix.domain.ports.driving.EvenementRepo;

import java.time.LocalDateTime;
import java.util.List;

@DomaineService
public class EvenementService  implements EvenementPort {

    private final EvenementRepo evenementRepo;

    public EvenementService(EvenementRepo evenementRepo) {
        this.evenementRepo = evenementRepo;
    }

    @Override
    public Evenement saveEvenement(Evenement evenement) {
//        if (evenement.getTypeEvenement() == null) {
//            throw new IllegalArgumentException("L'evenement doit avoir un type");
//        }
        if (evenement.getNom() == null) {
            throw new IllegalArgumentException("L'evenement doit avoir un nom");
        }
        evenement.setDateHeureCreation(LocalDateTime.now());
        return evenementRepo.saveEvenement(evenement);
    }

    @Override
    public void deleteEvenement(Evenement evenement) {

        evenementRepo.deleteEvenement(evenement);
    }

    @Override
    public Evenement updateEvenement(Evenement idEvenement) {
        idEvenement.setDateHeureCreation(LocalDateTime.now());
        return evenementRepo.saveEvenement(idEvenement);
    }

//    @Override
//    public List<Evenement> getLesEvenementsByTypeEvenement(TypeEvenement typeEvenement) {
//        return evenementRepo.getLesEvenementsByTypeEvenement(typeEvenement);
//    }

    @Override
    public List<Evenement> getLesEvenementsByNom(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException("L'évènement doit avoir un nom");
        }
        return evenementRepo.getLesEvenementsByNom(nom);
    }

    @Override
    public List<Evenement> getAllEvenements() {
        return evenementRepo.getAllEvenements();
    }
}
