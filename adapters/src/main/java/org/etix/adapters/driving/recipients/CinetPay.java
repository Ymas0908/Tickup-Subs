//package org.etix.adapters.driving.recipients;
//
//
//import lombok.AllArgsConstructor;
//import org.etix.domain.ports.driving.CineyPay.CinetPayRepo;
//import org.etix.domain.request.CinetPayRequest;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//@AllArgsConstructor
//
//public class CinetPay implements CinetPayRepo {
//    private final static String BASE_URL = "https://api-checkout.cinetpay.com/v2";
//
//    private final RestTemplate restClient = new RestTemplate();
//
//
//    @Override
//    public void initierPaiement(CinetPayRequest cinetPayRequest) {
//        try {
//            String url = UriComponentsBuilder
//                    .fromUriString(BASE_URL + "/payment")
//                    .toUriString();
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            Map<String, Object> payload = new HashMap<>();
//            payload.put("amount", cinetPayRequest.getAmount());
//            payload.put("description", cinetPayRequest.getDescription());
//            payload.put("currency", cinetPayRequest.getCurrency());
//            payload.put("transaction_id", cinetPayRequest.getTransaction_id());
//            payload.put("channels", cinetPayRequest.getChannels());
//            payload.put("apikey", cinetPayRequest.getApikey());
//            payload.put("site_id", cinetPayRequest.getSite_id());
//
//            // Ajout des informations d'authentification dans le BODY
//            payload.put("apikey", "200349002765f8799f5df790.27967324");
//            payload.put("secret", "1255514423673df0903ffc27.73762750");
//            payload.put("currency", "XOF");
//            payload.put("site_id", "5883393");
//
////            // ✅ URL de retour obligatoires
////            payload.put("success_url", "https://tonsite.com/success");
////            payload.put("cancel_url", "https://tonsite.com/cancel");
//
//            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
//            ResponseEntity<String> response = new RestTemplate().postForEntity(url, request, String.class);
//
//            System.out.println("Réponse PayTech: " + response.getBody());
//
//        } catch (HttpClientErrorException e) {
//            System.out.println("Erreur API PayTech: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
//            throw e;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Une erreur s'est produite lors de l'appel à PayTech.");
//        }
//    }
//
//}
//
