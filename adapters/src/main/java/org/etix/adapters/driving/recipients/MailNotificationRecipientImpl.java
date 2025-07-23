package org.etix.adapters.driving.recipients;



import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.etix.domain.models.request.MailRequest;
import org.etix.domain.models.response.MailResponse;
import org.etix.domain.ports.driving.MailNotificationRecipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

@Component
public class MailNotificationRecipientImpl implements MailNotificationRecipient {

    private static final String JAVA_MAIL_FILE = "classpath:mail/html/";

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailAuthlogin;

    @Override
    public MailResponse envoyer(MailRequest mRequest) {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailAuthlogin);
            helper.setTo(mRequest.getRecipients());
            helper.setSubject(mRequest.getObject());
            helper.setText(mRequest.getContent(), true);

            // On ajoute les fichiers joints si il y en a
            for (String attachement : mRequest.getAttachements()) {
                helper.addAttachment(fileName(attachement), new File(attachement));
            }
            mailSender.send(message);
            return new MailResponse(200, "Email envoyé");
        } catch (MessagingException | MailException ex) {
            ex.printStackTrace();
            return new MailResponse(500, "Envoi echoué: " + ex.getMessage());
        }
    }

    @Override
    public String format(String name, Map<String, String> params) throws IOException {
        String fileContent = null;
        if (name == null) {
            throw new IOException("Le nom du fichier ne peut être null");
        }
        Resource resource = resourceLoader.getResource(JAVA_MAIL_FILE + name);
        if (resource == null || !resource.exists())
            throw new IOException(String.format("Le fichier %s est introuvable.", name));
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        fileContent = sb.toString();
        // String fileContent = IOUtils.toString(resource.getInputStream(),
        // StandardCharsets.UTF_8.name());
        for (Map.Entry<String, String> param : params.entrySet()) {
            fileContent = fileContent.replaceAll(param.getKey(), param.getValue());
        }
        return fileContent;
    }

    /**
     * Extraire le nom d'un fichier dans un chemin complet.
     *
     * @param filePath
     * @return
     */
    public String fileName(String filePath) {
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.length());
    }
}
