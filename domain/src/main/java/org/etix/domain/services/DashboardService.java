package org.etix.domain.services;



import org.etix.domain.ddd.DomaineService;
import org.etix.domain.models.enumerations.TypeEvenement;
import org.etix.domain.ports.driver.Dashboard;
import org.etix.domain.ports.driving.EvenementRepo;
import org.etix.domain.ports.driving.TicketRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@DomaineService
public class DashboardService implements Dashboard {
    private final EvenementRepo evenementRepo;
    private final TicketRepo ticketRepo;


    public DashboardService(EvenementRepo evenementRepo, TicketRepo ticketRepo) {
        this.evenementRepo = evenementRepo;
        this.ticketRepo = ticketRepo;
    }


    @Override
    public Integer getNombresEvenements() {
        // TODO: Must be nullable if no rows found
        Integer nombresEvenements = evenementRepo.getNombresEvenements();
        return Objects.requireNonNullElse(nombresEvenements, 0);
    }

    @Override
    public Integer getNombresTotalTickets() {
        Integer nombresTickets = ticketRepo.getNombresTotalTickets();
        return Objects.requireNonNullElse(nombresTickets, 0);
    }

    @Override
    public Integer getNombresEvenementsByType(TypeEvenement typeEvenement) {
        Integer nombre = evenementRepo.getNombresEvenementsByType(typeEvenement);
        return Objects.requireNonNullElse(nombre, 0);
    }

    public Integer getNombreEvenementsSurPeriode(LocalDate dateDebut, LocalDate dateFin) {
        // Check if is a valid periode
        checkPeriode(dateDebut, dateFin);

        LocalDateTime startDate = dateDebut.atStartOfDay();
        LocalDateTime endDate = dateFin.atTime(23, 59, 59);

        Integer nombreEvenements = evenementRepo.getTotalEvenementsParPeriode(startDate, endDate.plusDays(1));
        return Objects.requireNonNullElse(nombreEvenements, 0);
    }
    private static void checkPeriode(LocalDate dateDebut, LocalDate dateFin) {
        if (dateDebut == null) throw new IllegalArgumentException("La date de début est obligatoire");
        if (dateFin == null) throw new IllegalArgumentException("La date de fin est obligatoire");

        if (dateDebut.isAfter(dateFin))
            throw new IllegalArgumentException("La date de debut doit être avant la date de fin");
    }

    @Override
    public List<Number> getNombresEvenementByYear(Integer year) {
        if(year == null) throw new IllegalArgumentException("L'année est obligatoire");

        List<Number> evenements = new ArrayList<>();

        for (int month = 1; month <= 12; month++) {
            LocalDate start = LocalDate.of(year, month, 1);
            LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

            Integer evenementsSurPeriode = this.getNombreEvenementsSurPeriode(start, end);
            evenements.add(evenementsSurPeriode != null ? evenementsSurPeriode : 0);
        }
        return evenements;
    }
}
