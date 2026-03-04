package org.tickup.adapters.ports.driver.facades;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tickup.domain.apiRequest.MailRequest;
import org.tickup.domain.ports.driving.recipients.NotifyRecipient;
import org.tickup.domain.services.TicketService;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class TicketFacade {

    @Autowired
    private TicketService ticketService;
    
    @Autowired
    private NotifyRecipient notifyRecipient;

    public void generateAndSendTicket(String eventName,
                                   String participantName,
                                   String ticketNumber,
                                   String eventDate,
                                   String location,
                                   String category,
                                   double price,
                                   String email) {
        try {
            log.info("Génération du ticket pour l'événement: {} - Participant: {}", eventName, participantName);
            
            // Générer le ticket PDF
            byte[] ticketBytes = ticketService.generateTicket(
                eventName, participantName, ticketNumber, eventDate, location, category, price
            );
            
            log.info("Ticket PDF généré avec succès, taille: {} bytes", ticketBytes.length);
            
            // Générer le QR code pour l'email
            String qrCodeBase64 = ticketService.generateQrCodeForEmail(ticketNumber);
            log.info("QR code généré pour l'email");
            
            // Créer l'email
            MailRequest mailRequest = new MailRequest();
            mailRequest.setRecipients(new String[]{email});
            mailRequest.setObject("🎟️ Votre ticket pour " + eventName);
            
            // Créer le contenu HTML de l'email
            String emailContent = createEmailContent(eventName, participantName, ticketNumber, 
                eventDate, location, category, price, qrCodeBase64);
            mailRequest.setContent(emailContent);
            
            // Envoyer l'email
            boolean emailSent = notifyRecipient.envoyerMail(mailRequest);
            
            if (emailSent) {
                log.info("Ticket généré et envoyé avec succès à: {}", email);
            } else {
                log.error("Erreur lors de l'envoi de l'email à: {}", email);
                throw new RuntimeException("L'envoi de l'email a échoué");
            }
            
        } catch (Exception e) {
            log.error("Erreur lors de la génération/ envoi du ticket: {}", e.getMessage(), e);
            throw new RuntimeException("Erreur lors de la génération du ticket: " + e.getMessage());
        }
    }
    
    private String createEmailContent(String eventName, String participantName, String ticketNumber,
                                   String eventDate, String location, String category, double price, String qrCodeBase64) {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<title>Votre ticket Tickup</title>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }" +
                ".container { max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px; background-color: white; }" +
                ".header { text-align: center; color: #0d47a1; margin-bottom: 20px; }" +
                ".ticket-info { background-color: #f5f5f5; padding: 15px; border-radius: 5px; margin: 10px 0; }" +
                ".qr-code { text-align: center; margin: 20px 0; padding: 20px; background-color: #e3f2fd; border-radius: 10px; }" +
                ".qr-code img { max-width: 200px; height: auto; border: 3px solid #0d47a1; border-radius: 10px; }" +
                ".footer { text-align: center; color: #666; margin-top: 20px; font-size: 12px; }" +
                ".success { background-color: #d4edda; border: 1px solid #c3e6cb; color: #155724; padding: 10px; border-radius: 5px; margin-bottom: 20px; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<div class='header'>" +
                "<h1>🎟️ Votre ticket Tickup</h1>" +
                "</div>" +
                "<div class='success'>" +
                "✅ <strong>Ticket généré avec succès !</strong>" +
                "</div>" +
                "<p>Bonjour <strong>" + participantName + "</strong>,</p>" +
                "<p>Votre ticket pour l'événement <strong>" + eventName + "</strong> a été généré avec succès.</p>" +
                "<div class='ticket-info'>" +
                "<h3>📋 Informations du ticket</h3>" +
                "<p><strong>Numéro du ticket:</strong> " + ticketNumber + "</p>" +
                "<p><strong>Date de l'événement:</strong> " + (eventDate != null ? eventDate : "Date non spécifiée") + "</p>" +
                "<p><strong>Lieu:</strong> " + (location != null ? location : "Lieu non spécifié") + "</p>" +
                "<p><strong>Catégorie:</strong> " + (category != null ? category : "Standard") + "</p>" +
                "<p><strong>Prix:</strong> " + price + " FCFA</p>" +
                "</div>" +
                "<div class='qr-code'>" +
                "<h3>📱 Code QR du ticket</h3>" +
                "<p>Scannez ce code QR à l'entrée de l'événement</p>" +
                "<img src='data:image/png;base64," + qrCodeBase64 + "' alt='QR Code Ticket' />" +
                "<p><em>Numéro du ticket: " + ticketNumber + "</em></p>" +
                "</div>" +
                "<div style='background-color: #fff3cd; border: 1px solid #ffeaa7; padding: 15px; border-radius: 5px; margin: 20px 0;'>" +
                "<h4>⚠️ Instructions importantes</h4>" +
                "<ul>" +
                "<li>Présentez ce ticket (numéro ou QR code) à l'entrée</li>" +
                "<li>Conservez bien votre numéro de ticket: <strong>" + ticketNumber + "</strong></li>" +
                "<li>Arrivez 30 minutes avant le début de l'événement</li>" +
                "</ul>" +
                "</div>" +
                "<div class='footer'>" +
                "<p>&copy; 2026 Tickup - Tous droits réservés</p>" +
                "<p>Support: support@tickup.com | Tel: +225 00 00 00 00</p>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";
    }
}
