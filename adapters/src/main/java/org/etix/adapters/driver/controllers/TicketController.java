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


    @PostMapping("/creerUnTicket")
    public ResponseEntity<ResponseApi> creerUnTicket(@RequestBody Ticket ticket) {
        try {
            return new ResponseEntity<>(new ResponseApi("Succès", 200, creerUnTicketFacade.creerUnTicket(TicketEntity.toEntity(ticket))), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseApi("Erreur", 404, null), HttpStatus.NOT_FOUND);
        }
    }

@GetMapping("/getAllTickets/")
    public ResponseEntity<ResponseApi> getAllTickets() {
        try {
            return new ResponseEntity<>(new ResponseApi("Succès", 200, creerUnTicketFacade.getAllTickets()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseApi("Erreur", 404, null), HttpStatus.NOT_FOUND);
        }

    }




    @GetMapping("/getTicketById/{id}")
    public ResponseEntity<ResponseApi> getTicketById(@PathVariable Integer idEvenement) {
        try {
            return new ResponseEntity<>(new ResponseApi("Succès", 200, creerUnTicketFacade.getTicketByEvenement(idEvenement)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseApi("Erreur", 404, null), HttpStatus.NOT_FOUND);
        }
    }
}
