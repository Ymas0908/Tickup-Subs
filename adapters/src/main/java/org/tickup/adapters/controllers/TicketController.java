package org.tickup.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tickup.adapters.ports.driver.facades.TicketFacade;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    @Autowired
    private TicketFacade ticketFacade;

    @PostMapping("/generate")
    public ResponseEntity<String> generateAndSendTicket(@RequestBody TicketRequest request) {
        try {
            // Validation des champs
            if (request.getEventName() == null || request.getEventName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Le nom de l'événement est requis");
            }
            if (request.getParticipantName() == null || request.getParticipantName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Le nom du participant est requis");
            }
            if (request.getTicketNumber() == null || request.getTicketNumber().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Le numéro du ticket est requis");
            }
            if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("L'email est requis");
            }

            // Utiliser la facade pour générer et envoyer le ticket
            ticketFacade.generateAndSendTicket(
                request.getEventName(),
                request.getParticipantName(),
                request.getTicketNumber(),
                request.getEventDate() != null ? request.getEventDate() : "Date non spécifiée",
                request.getLocation() != null ? request.getLocation() : "Lieu non spécifié",
                request.getCategory() != null ? request.getCategory() : "Standard",
                request.getPrice(),
                request.getEmail()
            );

            return ResponseEntity.ok("Ticket généré et envoyé avec succès à " + request.getEmail());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erreur: " + e.getMessage());
        }
    }

    // DTO pour la requête
    public static class TicketRequest {
        private String eventName;
        private String participantName;
        private String ticketNumber;
        private String eventDate;
        private String location;
        private String category;
        private double price;
        private String email;

        // Getters and Setters
        public String getEventName() { return eventName; }
        public void setEventName(String eventName) { this.eventName = eventName; }
        
        public String getParticipantName() { return participantName; }
        public void setParticipantName(String participantName) { this.participantName = participantName; }
        
        public String getTicketNumber() { return ticketNumber; }
        public void setTicketNumber(String ticketNumber) { this.ticketNumber = ticketNumber; }
        
        public String getEventDate() { return eventDate; }
        public void setEventDate(String eventDate) { this.eventDate = eventDate; }
        
        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }
        
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}
