package org.etix.adapters.driver.facades;


import lombok.AllArgsConstructor;
import org.etix.domain.models.enumerations.TypeEvenement;
import org.etix.domain.ports.driver.Dashboard;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Component
public class DashboardFacade {

    private final Dashboard dashboard;

    /**
     * Récupère le nombre total d'évènements .
     *
     * @return Le nombre total d'évènements.
     */
    public Integer getNombresEvenements() {
        return dashboard.getNombresEvenements();
    }

    /**
     * Récupère le nombre total de tickets.

     */
    public Integer getNombresTotalTickets() {
        return dashboard.getNombresTotalTickets();
    }

    public Integer getNombresEvenementsByType(TypeEvenement typeEvenement) {
        return dashboard.getNombresEvenementsByType(typeEvenement);
    }

    /**
     * Permet de collecter les evenements par mois
     * @param year
     * @return
     */

    public List<Number> getDetailsEvenementsParMois(Integer year){
        return dashboard.getNombresEvenementByYear(year);
    }

    public Integer getNombreTransactions(LocalDate dateDebut, LocalDate dateFin) {
        return dashboard.getNombreEvenementsSurPeriode(dateDebut, dateFin);
    }
}
