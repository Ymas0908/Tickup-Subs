package org.etix.adapters.driver.controllers;

import lombok.AllArgsConstructor;
import org.etix.adapters.driver.api.ResponseApi;
import org.etix.adapters.driver.facades.CreerUnEvenementFacade;
import org.etix.adapters.entities.EvenementEntity;
import org.etix.domain.models.Evenement;
//import org.etix.domain.models.enumerations.TypeEvenement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
@AllArgsConstructor

@RestController
@RequestMapping("etix/evenement/v1")
public class EvenementController {

    private final CreerUnEvenementFacade creerUnEvenementFacade;

    @Operation(summary = "Créer un,evènement ",
            description = "Cette méthode permet de créer un,evènement"

    )
    @PostMapping("/creerUnEvenement/")
    public ResponseEntity<ResponseApi> creerUnEvenement(@RequestBody Evenement evenement) {
        return new ResponseEntity<>(new ResponseApi("Succès", 200, creerUnEvenementFacade.creerUnEvenement(EvenementEntity.toEntity(evenement))), HttpStatus.OK);
    }


    @Operation(summary = "Collecter les évènements ",
            description = "Cette.visitMethod permet de collecter les evènements",
            parameters = {
                    @Parameter(name = "idEvenement", description = "idEvenement", required = true),
            }
    )
    @PutMapping("/modifierUnEvenement/")
    public ResponseEntity<ResponseApi> modifierUnEvenement(@RequestBody Evenement evenement) {
        return new ResponseEntity<>(new ResponseApi("Succès", 200, creerUnEvenementFacade.modifierUnEvenement(EvenementEntity.toEntity(evenement))), HttpStatus.OK);
    }


    @Operation(summary = "Supprimer un,evènement ",
            description = "Cette.visitMethod permet de supprimer un,evènement",
            parameters = {
                    @Parameter(name = "idEvenement", description = "idEvenement", required = true),
            }
    )
    @DeleteMapping("/supprimerUnEvenement/")
    public ResponseEntity<ResponseApi> supprimerUnEvenement(@RequestBody Evenement evenement) {
        creerUnEvenementFacade.supprimerUnEvenement(EvenementEntity.toEntity(evenement));
        return new ResponseEntity<>(new ResponseApi("Evenement supprimé", 200, null), HttpStatus.OK);
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
            description = "Cette.visitMethod permet de collecter les evènements",
            parameters = {
                    @Parameter(name = "typeEvenement", description = "typeEvenement", required = true),
            }
    )
    @GetMapping("/getAllEvenements/")
    public ResponseEntity<ResponseApi> getAllEvenements() {
        return new ResponseEntity<>(new ResponseApi("Succès", 200, creerUnEvenementFacade.getAllEvenements()), HttpStatus.OK);

    }


    @GetMapping("/getLesEvenementsByNom/{nom}")
    public ResponseEntity<ResponseApi> getLesEvenementsByNom(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(new ResponseApi("Succès", 200, creerUnEvenementFacade.getLesEvenementsByNom(nom)), HttpStatus.OK);
    }
}
