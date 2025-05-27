package org.etix.domain.ports.driver;

import org.etix.domain.models.Evenement;
import org.springframework.web.multipart.MultipartFile;
//import org.etix.domain.models.enumerations.TypeEvenement;

import java.util.List;

public interface EvenementPort {

    Evenement saveEvenement(Evenement evenement);
    void deleteEvenement(String reference);
    Evenement updateEvenement(Evenement idEvenement);

//    List<Evenement> getLesEvenementsByTypeEvenement(TypeEvenement typeEvenement);

    List<Evenement> getLesEvenementsByNom(String nom);

    List<Evenement> getLesEvenementsByLibelle(String libelle);

    List<Evenement> getAllEvenements();
    void uploadFile(MultipartFile file, Integer idEvenement);


    Object getUrlImageEvenement(String refEvenement);
}
