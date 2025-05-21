package org.etix.adapters.driving.recipients;

import lombok.AllArgsConstructor;
import org.etix.domain.ports.driving.FedaPay.FedaPayRepo;
import org.etix.domain.request.CinetPayRequest;
import org.etix.domain.request.FedaPayRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor

public class FedayPay implements FedaPayRepo {
    private final static String BASE_URL = "https://api-checkout.cinetpay.com/v2";

    private final RestTemplate restClient = new RestTemplate();


    @Override
    public void initierPaiement(FedaPayRequest fedaPayRequest) {
        try {
            String url = UriComponentsBuilder
                    .fromUriString(BASE_URL + "/payment")
                    .toUriString();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> payload = new HashMap<>();
            payload.put("amount", fedaPayRequest.getAmount());
            payload.put("description", fedaPayRequest.getDescription());
            payload.put("currency", fedaPayRequest.getCurrency());


            // Ajout des informations d'authentification dans le BODY
            payload.put("apikey", "pk_sandbox_hNO31PWCieS4VeKT0uszDZJk");
            payload.put("secret", "sk_sandbox_90UI4B9zGGuk8OxdgLg1lrOR");


//            // ✅ URL de retour obligatoires
//            payload.put("success_url", "https://tonsite.com/success");
//            payload.put("cancel_url", "https://tonsite.com/cancel");

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
            ResponseEntity<String> response = new RestTemplate().postForEntity(url, request, String.class);

            System.out.println("Réponse Fedapay: " + response.getBody());

        } catch (HttpClientErrorException e) {
            System.out.println("Erreur API Fedapay: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de l'appel à PayTech.");
        }
    }
}
