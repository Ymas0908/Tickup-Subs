package org.tickup.adapters.ports.driver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tickup.adapters.ports.driver.apiException.ResponseApi;
import org.tickup.adapters.ports.driver.facades.UsagerFacade;
import org.tickup.adapters.requestModel.UsagerRequest;
import org.tickup.domain.requests.UsagersRequest;

@RestController
@Slf4j
@RequestMapping("/api/v1/usagers")
@Tag(name = "Gestion des usagers", description = "API pour la gestion des utilisateurs de l'application")

@AllArgsConstructor
public class UsagerController {

    private final UsagerFacade usagerFacade;



    @PostMapping("/usager")
    @Operation(summary = "Créer un usager", description = "Crée un nouvel usager dans le système")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usager créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Données invalides"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur")
    })
    public ResponseEntity<ResponseApi> saveUsager(@RequestBody UsagerRequest request) {
        log.info("UsagerRequest : {}", request);
        UsagersRequest domain = UsagerRequest.toDomain(request);
        log.info("UsagerRequest domain : {}", domain);
        usagerFacade.saveUsager(domain);
        return new ResponseEntity<>(new ResponseApi("Success", HttpStatus.OK.value(), null), HttpStatus.OK);
    }

}
