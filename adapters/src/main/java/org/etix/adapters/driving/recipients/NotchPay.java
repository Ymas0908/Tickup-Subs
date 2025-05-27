package org.etix.adapters.driving.recipients;


import lombok.AllArgsConstructor;
import org.etix.domain.ports.driving.NotchPayRepo;
import org.etix.domain.request.NotchPayRequest;
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

public class NotchPay implements NotchPayRepo {
    private final static String BASE_URL = "https://api.notchpay.co";
    private final static String API_KEY = "pk_test.VanTqN2cG7XBKxiimbND3WkItfc5q9pL5rRPHyaQFO1OOtRF581CxLh6a9L36Bgqdg0uiiLENGrhKVZgjWJPCh5bZeSIw4BjBliZ9xb59JGM8dA6Ns2OAdTjVcqbF";

    private final RestTemplate restClient = new RestTemplate();


    @Override
    public void initierPaiement(NotchPayRequest notchPayRequest) {
        try {
            String url = UriComponentsBuilder
                    .fromUriString(BASE_URL + "/api/v1/initierpaiement")
                    .toUriString();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(API_KEY);

            Map<String, Object> customer = new HashMap<>();
            customer.put("name", notchPayRequest.getCustomer().getName());
            customer.put("email", notchPayRequest.getCustomer().getEmail());
            customer.put("phone", notchPayRequest.getCustomer().getPhone());


            Map<String, Object> payload = new HashMap<>();
            payload.put("amount", notchPayRequest.getAmount());
            payload.put("reference", notchPayRequest.getReference());
            payload.put("customer", notchPayRequest.getCustomer());
            payload.put("description", notchPayRequest.getDescription());
            payload.put("currency", notchPayRequest.getCurrency());
            payload.put("callback_url", notchPayRequest.getCallbackUrl());


            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
            ResponseEntity<String> response = new RestTemplate().postForEntity(url, request, String.class);

            System.out.println("Réponse NotchPay: " + response.getBody());

        } catch (HttpClientErrorException e) {
            System.out.println("Erreur API NotchPay: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de l'appel à NotchPay.");
        }
    }

}

