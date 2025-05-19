package org.etix.adapters.driver.controllers;

import lombok.AllArgsConstructor;
import org.etix.adapters.driver.api.ResponseApi;
import org.etix.adapters.driver.facades.CreerUnEvenementFacade;
import org.etix.adapters.entities.EvenementEntity;
import org.etix.domain.models.Evenement;
//import org.etix.domain.models.enumerations.TypeEvenement;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/api/v1")
public class EvenementController {

    private final CreerUnEvenementFacade creerUnEvenementFacade;

    @Operation(summary = "Créer un,evènement ",
            description = "Cette méthode permet de créer un,evènement"

    )
    @PostMapping("/creerUnEvenement/")
    public ResponseEntity<ResponseApi> creerUnEvenement(@RequestBody Evenement evenement) {
        try {
            return new ResponseEntity<>(new ResponseApi("Succès", 200, creerUnEvenementFacade.creerUnEvenement(EvenementEntity.toEntity(evenement))), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseApi> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("idEvenement") Integer idEvenement) {

        long maxFileSize = 100 * 1024 * 1024;

        List<String> allowedContentTypes = List.of("application/pdf", "image/png", "image/jpeg", "image/jpg");

        try {
            if (file == null || file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseApi("Aucun fichier envoyé", HttpStatus.BAD_REQUEST.value(), null));
            }

            if (file.getSize() > maxFileSize) {
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                        .body(new ResponseApi("Le fichier est trop volumineux. Taille maximale : 100 Mo", HttpStatus.PAYLOAD_TOO_LARGE.value(), null));
            }

            String contentType = file.getContentType();
            if (contentType == null ||
                    (!allowedContentTypes.contains(contentType) && !contentType.startsWith("image/"))) {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                        .body(new ResponseApi("Type de fichier non supporté : " + contentType, HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), null));
            }

            creerUnEvenementFacade.uploadFile(file, idEvenement);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseApi("Fichier téléversé avec succès", HttpStatus.CREATED.value(), null));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseApi("Erreur lors du chargement du fichier", HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }


    @Operation(summary = "Modifier un évènement ",
            description = "Cette méthode permet de modifier un évènement",
            parameters = {
                    @Parameter(name = "idEvenement", description = "idEvenement", required = true),
            }
    )
    @PutMapping("/modifierUnEvenement/")
    public ResponseEntity<ResponseApi> modifierUnEvenement(@RequestBody Evenement evenement) {
        try {
            return new ResponseEntity<>(new ResponseApi("Succès", 200, creerUnEvenementFacade.modifierUnEvenement(EvenementEntity.toEntity(evenement))), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Operation(summary = "Supprimer un,evènement ",
            description = "Cette.méthode permet de supprimer un évènement",
            parameters = {
                    @Parameter(name = "idEvenement", description = "idEvenement", required = true),
            }
    )
    @DeleteMapping("/supprimerUnEvenement/")
    public ResponseEntity<ResponseApi> supprimerUnEvenement(@RequestBody Evenement evenement) {
        try {
            creerUnEvenementFacade.supprimerUnEvenement(EvenementEntity.toEntity(evenement));
            return new ResponseEntity<>(new ResponseApi("Evenement supprimé", 200, null), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


//    @Operation(summary = "Collecter les évènements par type ",
//            description = "Cette.visitMethod permet de collecter les evènements par type",
//            parameters = {
//                    @Parameter(name = "typeEvenement", description = "typeEvenement", required = true),
//            }
//    )
//    @GetMapping("/getLesEvenementsByTypeEvenement")
//    public ResponseEntity<ResponseApi> getLesEvenementsByTypeEvenement(@RequestParam("typeEvenement") TypeEvenement typeEvenement) {
//        return new ResponseEntity<>(new ResponseApi("Succès", 200, creerUnEvenementFacade.getLesEvenementsByTypeEvenement(typeEvenement)), HttpStatus.OK);
//    }



    @Operation(summary = "Collecter les évènements ",
            description = "Cette.méthode permet de collecter les evènements",
            parameters = {
                    @Parameter(name = "typeEvenement", description = "typeEvenement", required = true),
            }
    )
    @GetMapping("/getAllEvenements")
    public ResponseEntity<ResponseApi> getAllEvenements() {
        try {
            return new ResponseEntity<>(new ResponseApi("Succès, tous les évènements ont été collectés", 200, creerUnEvenementFacade.getAllEvenements()), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @GetMapping("/getLesEvenementsByNom/{nom}")
    public ResponseEntity<ResponseApi> getLesEvenementsByNom(@PathVariable("nom") String nom) {
        try {
            return new ResponseEntity<>(new ResponseApi("Succès", 200, creerUnEvenementFacade.getLesEvenementsByNom(nom)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/getLesEvenementsByLibelle/{libelle}")
    public ResponseEntity<ResponseApi> getLesEvenementsByLibelle(@PathVariable("libelle") String libelle) {
        try {
            return new ResponseEntity<>(new ResponseApi("Succès", 200, creerUnEvenementFacade.getLesEvenementsByLibelle(libelle)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/getUrlImageEvenement")
    public ResponseEntity<ResponseApi> getUrlImageEvenement(@RequestParam("idEvenement") Integer idEvenement) {
        try {

            return new ResponseEntity<>(new ResponseApi("Succès", 200, creerUnEvenementFacade.getUrlImageEvenement(idEvenement)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    }

