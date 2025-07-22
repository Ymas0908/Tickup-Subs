package org.etix.domain.ports.driver;



import org.etix.domain.models.enumerations.TypeEvenement;

import java.time.LocalDate;
import java.util.List;

public interface Dashboard {


    Integer getNombresEvenements();
    Integer getNombresTotalTickets();


    Integer getNombresEvenementsByType(TypeEvenement typeEvenement);
    List<Number> getNombresEvenementByYear(Integer year);

    Integer getNombreEvenementsSurPeriode(LocalDate dateDebut, LocalDate dateFin);
}
