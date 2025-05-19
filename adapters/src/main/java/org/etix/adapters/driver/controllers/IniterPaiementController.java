package org.etix.adapters.driver.controllers;

import lombok.AllArgsConstructor;
import org.etix.adapters.driver.api.ResponseApi;
import org.etix.adapters.driver.facades.PaytechPaymentFacade;
import org.etix.domain.request.PaytechPaymentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class IniterPaiementController {


    private final PaytechPaymentFacade paytechPaymentFacade;

    @PostMapping("/initier-paiement")
    public ResponseEntity<ResponseApi> initierPaiement(@RequestBody PaytechPaymentRequest paytechPaymentRequest) {
        System.out.println("paytechPaymentRequest " + paytechPaymentRequest);
        paytechPaymentFacade.initierPaiement(paytechPaymentRequest);
        return new ResponseEntity<>(new ResponseApi("Paiement effectué", 200,
                null), HttpStatus.OK);

    }
}
