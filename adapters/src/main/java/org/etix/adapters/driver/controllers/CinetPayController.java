//package org.etix.adapters.driver.controllers;
//
//import lombok.AllArgsConstructor;
//import org.etix.adapters.driver.api.ResponseApi;
//import org.etix.adapters.driver.facades.CineyPayFacade;
//import org.etix.adapters.driver.facades.PaytechPaymentFacade;
//import org.etix.domain.request.CinetPayRequest;
//import org.etix.domain.request.PaytechPaymentRequest;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.*;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping("/api/v1")
//public class CinetPayController {
//
//
//    private final CineyPayFacade cineyPayFacade;
//
//    @PostMapping("/initierpaiement")
//    public ResponseEntity<ResponseApi> initierPaiement(@RequestBody CinetPayRequest cinetPayRequest) {
//        System.out.println("paytechPaymentRequest " + cinetPayRequest);
//        cineyPayFacade.initierPaiement(cinetPayRequest);
//        return new ResponseEntity<>(new ResponseApi("Paiement effectué", 200,
//                null), HttpStatus.OK);
//
//    }
//}