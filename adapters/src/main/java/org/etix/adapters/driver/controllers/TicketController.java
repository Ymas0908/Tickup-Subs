package org.etix.adapters.driver.controllers;

import lombok.AllArgsConstructor;
import org.etix.adapters.driver.api.ResponseApi;
import org.etix.adapters.driver.facades.CreerUnTicketFacade;
import org.etix.adapters.entities.TicketEntity;
import org.etix.domain.models.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TicketController {

    private final CreerUnTicketFacade creerUnTicketFacade;


    @PostMapping("/tickets")
    public ResponseEntity<ResponseApi> creerUnTicket(@RequestBody Ticket ticket) {
        try {
            return new ResponseEntity<>(new ResponseApi("Succès", 200, creerUnTicketFacade.creerUnTicket(TicketEntity.toEntity(ticket))), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseApi("Erreur", 404, null), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tickets")
    public ResponseEntity<ResponseApi> getAllTickets() {
        try {
            return new ResponseEntity<>(new ResponseApi("Succès, tous les tickéts ont été collectés", 200, creerUnTicketFacade.getAllTickets()), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }





    @GetMapping("/evenements/{referenceEvenement}/tickets")
    public ResponseEntity<ResponseApi> getTicketById(@PathVariable String referenceEvenement) {
        try {
            return new ResponseEntity<>(new ResponseApi("Succès", 200, creerUnTicketFacade.getTicketByEvenement(referenceEvenement)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseApi("Erreur", 404, null), HttpStatus.NOT_FOUND);
        }
    }
}
