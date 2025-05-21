package org.etix.adapters.driver.controllers;

import lombok.AllArgsConstructor;
import org.etix.adapters.driver.api.ResponseApi;
import org.etix.adapters.driver.facades.MagmaOnePayFacade;
import org.etix.domain.request.MagmaOnePayRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class MagmaOnePayController {

    private final MagmaOnePayFacade magmaOnePayFacade;

    @PostMapping("/effectuer-paiement")
    public ResponseEntity<ResponseApi> effectuerPaiement(@RequestBody MagmaOnePayRequest magmaOnePayRequest) {
        System.out.println("magmaOnePayRequest " + magmaOnePayRequest);
        magmaOnePayFacade.effectuerPaiement(magmaOnePayRequest);
        return new ResponseEntity<>(new ResponseApi("Paiement effectué", 200,
                null), HttpStatus.OK);

    }
}
