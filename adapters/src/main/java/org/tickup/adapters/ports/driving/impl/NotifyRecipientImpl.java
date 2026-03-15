package org.tickup.adapters.ports.driving.impl;

import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.tickup.domain.apiRequest.MailRequest;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.tickup.domain.ports.driving.recipients.NotifyRecipient;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
public class NotifyRecipientImpl implements NotifyRecipient {
    private static final String JAVA_MAIL_FILE = "classpath:mail/html/";
    private final RestTemplate restTemplate = new RestTemplate();

//    @Value("${app.notification.email.service-url}")
//    private String emailServiceUrl;

    private final String emailServiceUrl = "http://localhost:9003/api/v1";

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public Boolean envoyerMail(MailRequest mRequest) {

        try {

            String notifyUrl = "http://localhost:9001/api/v1/email/send";

            log.info("Appel API Notification : {}", notifyUrl);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<MailRequest> entity = new HttpEntity<>(mRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    notifyUrl,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            log.info("Réponse API Notification : {}", response.getBody());

            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Mail envoyé avec succès");
                return true;
            }

            log.error("Erreur lors de l'envoi du mail : {}", response.getBody());
            return false;

        } catch (HttpClientErrorException e) {

            log.error("Erreur HTTP Client : {}", e.getResponseBodyAsString());
            return false;

        } catch (HttpServerErrorException e) {

            log.error("Erreur HTTP Server : {}", e.getResponseBodyAsString());
            return false;

        } catch (Exception e) {

            log.error("Erreur appel API notification", e);
            return false;
        }
    }

    @Override
    public String formatMail(String name, Map<String, String> params) throws IOException {
        try {
            // Vérification du nom du fichier
            if (name == null) {
                throw new IOException("Le nom du fichier ne peut être null");
            }

            // Chargement de la ressource
            Resource resource = resourceLoader.getResource("classpath:mail/html/" + name);
            if (!resource.exists()) {
                throw new IOException(String.format("Le fichier %s est introuvable.", name));
            }

            // Lecture du contenu du fichier
            String fileContent;
            try (InputStream inputStream = resource.getInputStream()) {
                fileContent = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            }

            // Remplacement des placeholders par les valeurs des paramètres
            for (Map.Entry<String, String> param : params.entrySet()) {
                fileContent = fileContent.replace(param.getKey(), param.getValue());
            }

            return fileContent;
        } catch (Exception e) {
            // Journalisation de l'erreur


            // Relance de l'exception
//            log.error("Une erreur est survenue au niveau du serveur", e);
            return null;
        }

    }



}
