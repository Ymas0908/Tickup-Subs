package org.etix.domain.ports.driving;

import org.etix.domain.models.Evenement;
//import org.etix.domain.models.enumerations.TypeEvenement;

import java.util.List;

public interface EvenementRepo {
    Evenement saveEvenement(Evenement evenement);
    void deleteEvenement(Integer idEvenement);
    Evenement updateEvenement(Evenement idEvenement);

    Evenement getEvenementById(Integer idEvenement);
    Evenement getEvenementByReference(String reference);

    List<Evenement> getLesEvenementsByNom(String nom);

//    List<Evenement> getLesEvenementsByTypeEvenement(TypeEvenement typeEvenement);

    List<Evenement> getAllEvenements();

    List<Evenement> getLesEvenementsByLibelle(String libelle);

    Object getUrlImageEvenement(String refEvenement);
}
