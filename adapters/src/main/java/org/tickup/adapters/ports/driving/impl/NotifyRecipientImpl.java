package org.tickup.adapters.ports.driving.impl;

import org.tickup.domain.apiRequest.MailRequest;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.tickup.domain.ports.driving.recipients.NotifyRecipient;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;


@Component
@Slf4j
public class NotifyRecipientImpl implements NotifyRecipient {
    private static final String JAVA_MAIL_FILE = "classpath:mail/html/";
    private final RestTemplate restTemplate = new RestTemplate();

//    @Value("${GIM_NOTIFY_URL}")
    private String GIM_NOTIFY_URL;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public Boolean envoyerMail(MailRequest mRequest) {
        return null;
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
