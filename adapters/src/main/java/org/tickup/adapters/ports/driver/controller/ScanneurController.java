package org.tickup.adapters.ports.driver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tickup.adapters.ports.driver.apiException.ResponseApi;
import org.tickup.adapters.ports.driver.facades.ScanneurFacade;
import org.tickup.adapters.requestModel.ScanneurRequest;
import org.tickup.domain.requests.ScanneursRequest;

@RestController
@Slf4j
@RequestMapping("/api/v1/scanneurs")
@Tag(name = "Gestion des scanneurs", description = "API pour la gestion des scanneurs de l'application")

@AllArgsConstructor
public class ScanneurController {

    private final ScanneurFacade scanneurFacade;


    @PostMapping("/scanneur")
    @Operation(summary = "Créer un usager scanneur", description = "Crée un nouvel usager scanneur dans le système")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usager créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur")
    })
    public ResponseEntity<ResponseApi> saveScanneur(@RequestBody ScanneurRequest request) {
        log.info("ScanneurRequest : {}", request);
        ScanneursRequest domain = ScanneurRequest.toDomain(request);
        log.info("ScanneurRequest domain : {}", domain);
        scanneurFacade.saveScanneur(domain);
        return new ResponseEntity<>(new ResponseApi("Success", HttpStatus.OK.value(), null), HttpStatus.OK);
    }
}