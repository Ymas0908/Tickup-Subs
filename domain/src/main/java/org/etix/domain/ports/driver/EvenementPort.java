package org.etix.domain.ports.driver;

import org.etix.domain.models.Evenement;
//import org.etix.domain.models.enumerations.TypeEvenement;

import java.util.List;

public interface EvenementPort {

    Evenement saveEvenement(Evenement evenement);
    void deleteEvenement(Evenement evenement);
    Evenement updateEvenement(Evenement idEvenement);

//    List<Evenement> getLesEvenementsByTypeEvenement(TypeEvenement typeEvenement);

    List<Evenement> getLesEvenementsByNom(String nom);

    List<Evenement> getAllEvenements();


 }
