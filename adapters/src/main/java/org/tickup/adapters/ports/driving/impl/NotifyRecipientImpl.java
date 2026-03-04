package org.tickup.adapters.ports.driving.impl;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.tickup.domain.apiRequest.MailRequest;
import org.tickup.domain.ports.driving.recipients.NotifyRecipient;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
public class NotifyRecipientImpl implements NotifyRecipient {

    private static final String JAVA_MAIL_FILE = "classpath:mail.html/";

    private final ResourceLoader resourceLoader;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${mail.sender.email}")
    private String senderMail;

    @Value("${spring.mail.port}")
    private String mailPort;
    @Value("${spring.mail.username}")
    private String mailUsername;
    @Value("${spring.mail.password}")
    private String mailPassword;

    public NotifyRecipientImpl(ResourceLoader resourceLoader, JavaMailSender mailSender) {
        this.resourceLoader = resourceLoader;
        this.mailSender = mailSender;
    }

    @Override
    public Boolean envoyerMail(MailRequest mRequest) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(senderMail);
            helper.setTo(mRequest.getRecipients());
            helper.setSubject("Tickup - Inscription");
            helper.setText(mRequest.getContent(), true); // true = HTML

            mailSender.send(message);
            log.info("Mail envoyé avec succès à {}", mRequest.getRecipients());
            return true;
        } catch (Exception e) {
            log.error("Erreur lors de l'envoi du mail", e);
            return false;
        }
    }

    @Override
    public String formatMail(String name, Map<String, String> params) throws IOException {
        if (name == null) throw new IOException("Le nom du fichier ne peut être null");

        Resource resource = resourceLoader.getResource(JAVA_MAIL_FILE + name);
        if (!resource.exists()) throw new IOException(String.format("Le fichier %s est introuvable.", name));

        String fileContent;
        try (InputStream inputStream = resource.getInputStream()) {
            fileContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }

        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                fileContent = fileContent.replace(param.getKey(), param.getValue());
            }
        }

        return fileContent;
    }
}
