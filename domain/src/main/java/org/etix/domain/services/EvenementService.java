package org.etix.domain.services;

import org.etix.domain.ddd.DomaineService;
import org.etix.domain.models.Evenement;
//import org.etix.domain.models.enumerations.TypeEvenement;
import org.etix.domain.ports.driver.EvenementPort;
import org.etix.domain.ports.driving.EvenementRepo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@DomaineService
public class EvenementService  implements EvenementPort {

    private final EvenementRepo evenementRepo;

    public EvenementService(EvenementRepo evenementRepo) {
        this.evenementRepo = evenementRepo;
    }

    @Override
    public Evenement saveEvenement(Evenement evenement) {
        String refEvenement = "REF-" + UUID.randomUUID();
        conditionEvenement(evenement);
        evenement.setReference(refEvenement);
        evenement.setDateHeureCreation(LocalDateTime.now());
        return evenementRepo.saveEvenement(evenement);
    }


    /***
     * Conditions sur l'evenement
     * @param evenement
     */
    void conditionEvenement(Evenement evenement) {
        if (evenement.getNom() == null) {
            throw new RuntimeException("L'evenement doit avoir un nom");
        }
        if (evenement.getLieu() == null) {
            throw new RuntimeException("L'evenement doit avoir un lieu");
        }
        if (evenement.getTypeEvenement() == null) {
            throw new RuntimeException("L'evenement doit avoir un type");
        }
    }

    @Override
    public void deleteEvenement(Integer idEvenement) {
        evenementRepo.deleteEvenement(idEvenement);
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
    public List<Evenement> getLesEvenementsByLibelle(String libelle) {
        return evenementRepo.getLesEvenementsByLibelle(libelle);
    }

    @Override
    public List<Evenement> getAllEvenements() {
        return evenementRepo.getAllEvenements();
    }


    /**
     * Upload d'un fichier et rattachement au document identifié par idDocument.
     */
    @Override
    public void uploadFile(MultipartFile file, Integer idEvenement) {
        try {
            // Récupérer le document à partir de l'idDocument
            Evenement event = evenementRepo.getEvenementById(idEvenement);

            String basePath = System.getProperty("user.dir");
            String url = "/domain/src/main/resources/";
            // Définir le répertoire de destination
            String directoryPath = basePath + url;

            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Créer un nom de fichier unique
            String originalFilename = file.getOriginalFilename();
            String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;

            // Créer un objet File pour le fichier final à sauvegarder
            File fileToSave = new File(directory, uniqueFilename);

            // Copier le contenu du fichier dans le fichier de destination
            file.transferTo(fileToSave);

            // Sauvegarder le chemin du fichier dans le document
            event.setUrlImage(url + uniqueFilename);
            evenementRepo.updateEvenement(event);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la sauvegarde du fichier", e);
        }
    }

    @Override
    public Object getUrlImageEvenement(String refEvenement){
        return evenementRepo.getUrlImageEvenement(refEvenement);
    }
}
