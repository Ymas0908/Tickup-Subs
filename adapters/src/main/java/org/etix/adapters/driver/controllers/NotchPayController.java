package org.etix.adapters.driver.controllers;

import lombok.AllArgsConstructor;
import org.etix.adapters.driver.api.ResponseApi;
import org.etix.adapters.driver.facades.NotchPayFacade;
import org.etix.domain.request.NotchPayRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class NotchPayController {


    private final NotchPayFacade notchPayFacade;

    @PostMapping("/initierpaiement")
    public ResponseEntity<ResponseApi> initierPaiement(@RequestBody NotchPayRequest notchPayRequest) {
        System.out.println("notchPayRequest:::::: " + notchPayRequest);
        notchPayFacade.initierPaiement(notchPayRequest);
        return new ResponseEntity<>(new ResponseApi("Paiement effectué", 200, null), HttpStatus.OK);

    }
}