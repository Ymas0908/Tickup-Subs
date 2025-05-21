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

public class PaytechPayement implements PaytechPayementRepo {
    private final static String BASE_URL = "https://paytech.sn/api";
    private final static String API_KEY = "0915c8c6b5edddd5566715152a85925d8eb320fab43482ef4a311c6119013cb2";

    private final RestTemplate restClient = new RestTemplate();


    @Override
    public void initierPaiement(PaytechPaymentRequest paytechPaymentRequest) {
        try {
            String url = BASE_URL + "/payment/request-payment";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + API_KEY);

            Map<String, Object> payload = new HashMap<>();
            payload.put("item_name", paytechPaymentRequest.getItem_name());
            payload.put("item_price", paytechPaymentRequest.getItem_price());
            payload.put("command_name", paytechPaymentRequest.getCommand_name());
            payload.put("ref_command", paytechPaymentRequest.getRef_command());

            // URLs obligatoires (à remplacer par tes URLs réelles)
            payload.put("success_url", "https://tonsite.com/success");
            payload.put("cancel_url", "https://tonsite.com/cancel");

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

            ResponseEntity<String> response = new RestTemplate().postForEntity(url, request, String.class);

            System.out.println("Réponse PayTech: " + response.getBody());

        } catch (HttpClientErrorException e) {
            System.out.println("Erreur API PayTech: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de l'appel à PayTech.");
        }
    }

}

