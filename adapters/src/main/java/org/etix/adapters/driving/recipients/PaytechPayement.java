package org.etix.adapters.driving.recipients;


import lombok.AllArgsConstructor;
import org.etix.domain.ports.driving.Paytech.PaytechPayementRepo;
import org.etix.domain.request.PaytechPaymentRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor

@RestController
public class PaytechPayement implements PaytechPayementRepo {
    private final static String GIM_GAR_SUB_URL = "https://paytech.sn/api";

    private final RestTemplate restClient = new RestTemplate();


    @Override
    public void initierPaiement(PaytechPaymentRequest paytechPaymentRequest) {
        try {
            String url = UriComponentsBuilder
                    .fromUriString(GIM_GAR_SUB_URL + "/payment/request-payment")
                    .toUriString();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> payload = new HashMap<>();
            payload.put("nomProduit", paytechPaymentRequest.getNomProduit());
            payload.put("prixProduit", paytechPaymentRequest.getPrixProduit());
            payload.put("devise", paytechPaymentRequest.getDevise());
            payload.put("descriptionProduit", paytechPaymentRequest.getDescriptionProduit());
            payload.put("refCommande", paytechPaymentRequest.getRefCommande());
            payload.put("callback_url", paytechPaymentRequest.getCallbackUrl());

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
            ResponseEntity<String> response = restClient.postForEntity(url, request, String.class);

            System.out.println("Réponse PayTech: " + response.getBody());

        } catch (HttpServerErrorException | HttpClientErrorException e) {
            System.out.println("Erreur API PayTech: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de l'appel à PayTech.");
        }
    }
}

