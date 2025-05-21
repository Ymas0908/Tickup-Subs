package org.etix.adapters.driving.recipients;

import lombok.AllArgsConstructor;
import org.etix.domain.ports.driving.MagmaOnePay.MagmaOnePayRepo;
import org.etix.domain.request.MagmaOnePayRequest;
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
public class MagmaOnePay implements MagmaOnePayRepo {

    private final static String BASE_URL = "https://api.magmaonepay.com/v1/payment/init";
    private static final String API_KEY = "sk_test_NMuPrFs109X9x8pxmW4tEG6HAIgDhhNM";


    private final RestTemplate restClient = new RestTemplate();


    @Override
    public void effectuerPaiement(MagmaOnePayRequest magmaOnePayRequest) {
        try {
            String url = UriComponentsBuilder
                    .fromUriString(BASE_URL) // BASE_URL doit être défini ailleurs
                    .toUriString();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + API_KEY);

            Map<String, Object> payload = new HashMap<>();
            payload.put("merchant_transaction_id", magmaOnePayRequest.getMerchantTransactionId());
            payload.put("amount", magmaOnePayRequest.getAmount());
            payload.put("currency", magmaOnePayRequest.getCurrency());
            payload.put("channel", magmaOnePayRequest.getChannel());

            // Champs optionnels - ajoutés s’ils sont présents
            if (magmaOnePayRequest.getDescription() != null) {
                payload.put("description", magmaOnePayRequest.getDescription());
            }
            if (magmaOnePayRequest.getPayee() != null) {
                payload.put("payee", magmaOnePayRequest.getPayee());
            }
            if (magmaOnePayRequest.getPayeeFirstName() != null) {
                payload.put("payee_first_name", magmaOnePayRequest.getPayeeFirstName());
            }
            if (magmaOnePayRequest.getPayeeLastName() != null) {
                payload.put("payee_last_name", magmaOnePayRequest.getPayeeLastName());
            }
            if (magmaOnePayRequest.getWebhookUrl() != null) {
                payload.put("webhook_url", magmaOnePayRequest.getWebhookUrl());
            }
            if (magmaOnePayRequest.getSuccessUrl() != null) {
                payload.put("success_url", magmaOnePayRequest.getSuccessUrl());
            }
            if (magmaOnePayRequest.getErrorUrl() != null) {
                payload.put("error_url", magmaOnePayRequest.getErrorUrl());
            }
            if (magmaOnePayRequest.getCustomField() != null) {
                payload.put("custom_field", magmaOnePayRequest.getCustomField());
            }

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
            ResponseEntity<String> response = new RestTemplate().postForEntity(url, request, String.class);

            System.out.println("Réponse MagmaOnePay: " + response.getBody());

        } catch (HttpClientErrorException e) {
            System.out.println("Erreur API MagmaOnePay: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de l'appel à MagmaOnePay.");
        }
    }
}
