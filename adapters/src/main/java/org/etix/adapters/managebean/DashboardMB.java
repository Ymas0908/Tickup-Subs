package org.etix.adapters.managebean;


import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.etix.adapters.driver.facades.DashboardFacade;
import org.etix.adapters.notification.FlashMessage;
import org.etix.domain.models.enumerations.TypeEvenement;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

import static org.etix.domain.utils.DateUtils.getFormattedDateRange;


@ViewScoped
@Named
public class DashboardMB {

    // Constantes pour les couleurs des graphiques
    private static final String COLOR_CONCERT = "rgb(255, 99, 132)";
    private static final String COLOR_SHOWCASE = "rgb(54, 162, 235)";
    private static final String COLOR_THEATRE = "rgb(255, 205, 86)";
    private static final String COLOR_CINEMA = "rgb(255, 205, 86)";
    private static final String COLOR_MATCH = "rgb(255, 99, 132)";
    private static final String COLOR_RELEASE_PARTY = "rgb(54, 162, 235)";
    private static final String COLOR_EXPOSITION = "rgb(255, 205, 86)";
    private static final String COLOR_FESTIVAL = "rgb(255, 99, 132)";
    private static final String COLOR_EVENT_AVENIR = "rgb(76, 86, 163)";
    private static final String COLOR_EVENT_EN_COURS = "rgb(255, 194, 102)";
    private static final String COLOR_EVENT_TERMINE = "rgb(255, 99, 132)";
    private static final String COLOR_CURRENT_YEAR = "rgb(75, 192, 192)";
    private static final String COLOR_LAST_YEAR = "rgb(255, 99, 132)";

    // Données du tableau de bord
    @Getter
    @Setter
    private DashboardData data = new DashboardData();

    // Modèles de graphiques
    @Getter private DonutChartModel donutModel;
    @Getter private LineChartModel lineModel;
    @Getter private PieChartModel pieModel;

    @Autowired
    private DashboardFacade dashboardFacade;

    @PostConstruct
    public void init() {
        try {
            // Configuration de la période par défaut
            data.dateFin = LocalDate.now();
            data.dateDebut = data.dateFin.minusDays(6);

            // Chargement des données
            loadDashboardData();

            // Création des graphiques
            createCharts();
        } catch (Exception e) {
            e.printStackTrace();
            FlashMessage.flash(FlashMessage.ERROR, "Erreur d'initialisation", "Impossible de charger les données du tableau de bord.");
        }
    }

    private void loadDashboardData() {
        // Formatage de la période
        withErrorHandling(
                () -> data.formattedPeriod = getFormattedDateRange(data.dateDebut, data.dateFin, "d MMMM", Locale.FRENCH),
                "format.date.error"
        );

        // Chargement des données principales
        data.nbreEvenement = fetchData(
                () -> dashboardFacade.getNombresEvenements(),
                "events.error"
        );

        data.nbreTickets = fetchData(
                () -> dashboardFacade.getNombresTotalTickets(),
                "tickets.error"
        );


        // Chargement des évenements par type
        data.cinemaEvents = fetchData(
                () -> dashboardFacade.getNombresEvenementsByType( TypeEvenement.CINEMA),
                "events.cinema.error"
        );

        data.theatreEvents = fetchData(
                () -> dashboardFacade.getNombresEvenementsByType( TypeEvenement.THEATRE),
                "events.theatre.error"
        );

        data.concertEvents = fetchData(
                () -> dashboardFacade.getNombresEvenementsByType( TypeEvenement.CONCERT),
                "events.concert.error"
        );

        data.festivalEvents = fetchData(
                () -> dashboardFacade.getNombresEvenementsByType( TypeEvenement.FESTIVAL),
                "events.festival.error"
        );

        data.expositionEvents = fetchData(
                () -> dashboardFacade.getNombresEvenementsByType( TypeEvenement.EXPOSITION),
                "events.festival.error"
        );
        data.releaseEvents = fetchData(
                () -> dashboardFacade.getNombresEvenementsByType( TypeEvenement.RELEASE_PARTY),
                "events.festival.error"
        );
        data.matchEvents = fetchData(
                () -> dashboardFacade.getNombresEvenementsByType( TypeEvenement.MATCH),
                "events.festival.error"
        );
        data.showcaseEvents = fetchData(
                () -> dashboardFacade.getNombresEvenementsByType( TypeEvenement.SHOWCASE),
                "events.showcase.error"
        );



        // Chargement des données annuelles
        int currentYear = Year.now().getValue();
        data.nbreEventsThisYear = fetchData(
                () -> dashboardFacade.getNombreTransactions(LocalDate.of(currentYear, 1, 1), LocalDate.of(currentYear, 12, 31)),
                "events.year.error"
        );

        data.nbreEventsLastYear = fetchData(
                () -> dashboardFacade.getNombreTransactions(LocalDate.of(currentYear - 1, 1, 1), LocalDate.of(currentYear - 1, 12, 31)),
                "events.year.error"
        );
    }

    private void createCharts() {
        createDonutModel();
        createLineModel();
        createPieModel();
    }

    private <T> T fetchData(Supplier<T> supplier, String errorKey) {
        return withErrorHandling(supplier, errorKey);
    }

    private void withErrorHandling(Runnable action, String errorKey) {
        withErrorHandling(() -> {
            action.run();
            return null;
        }, errorKey);
    }

    private <T> T withErrorHandling(Supplier<T> supplier, String errorKey) {
        try {
            return supplier.get();
        } catch (Exception e) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur", resolveErrorMessage(errorKey));
            return null;
        }
    }

    private String resolveErrorMessage(String key) {
        // Implémentez une logique de gestion des messages internationalisés
        Map<String, String> messages = Map.of(
                "format.date.error", "Erreur lors du formatage de la plage de dates.",
                "events.error", "Erreur lors du chargement des données des événements.",
                "events.cinema.error", "Erreur lors du chargement des événements de type Cinema.",
                "events.theatre.error", "Erreur lors du chargement des événements de type Theatre.",
                "events.concert.error", "Erreur lors du chargement des événements de type Concert.",
                "events.festival.error", "Erreur lors du chargement des événements de type Festival.",
                "transactions.year.error", "Erreur lors du chargement des données annuelles."
        );
        return messages.getOrDefault(key, "Erreur inconnue");
    }

    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData chartData = new ChartData();

        // Configuration du dataset
        DonutChartDataSet dataSet = new DonutChartDataSet();
        dataSet.setData(List.of(
                data.concertEvents,
                data.festivalEvents,
                data.theatreEvents,
                data.showcaseEvents,
                data.releaseEvents,
                data.matchEvents,
                data.expositionEvents, data.cinemaEvents));
        dataSet.setBackgroundColor(List.of
                (COLOR_CONCERT,
                        COLOR_FESTIVAL,
                        COLOR_THEATRE,
                        COLOR_SHOWCASE,
                        COLOR_RELEASE_PARTY,
                        COLOR_MATCH,
                        COLOR_EXPOSITION,
                        COLOR_CINEMA));

        chartData.addChartDataSet(dataSet);
        chartData.setLabels(List.of("CONCERT", "FESTIVAL", "THEATRE", "SHOWCASE", "RELEASE_PARTY", "MATCH", "EXPOSITION", "CINEMA"));
        donutModel.setData(chartData);
    }

    public void createLineModel() {
        lineModel = new LineChartModel();
        ChartData chartData = new ChartData();

        // Récupération des données
        int currentYear = Year.now().getValue();
        List<Number> currentYearData = fetchMonthlyData(currentYear);
        List<Number> lastYearData = fetchMonthlyData(currentYear - 1);

        // Création des datasets
        LineChartDataSet lastYearSet = createLineDataSet(
                "Evénements " + (currentYear - 1),
                COLOR_LAST_YEAR,
                lastYearData
        );

        LineChartDataSet currentYearSet = createLineDataSet(
                "Evénements " + currentYear,
                COLOR_CURRENT_YEAR,
                currentYearData
        );



        // Configuration des labels
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM", Locale.FRENCH);
        List<String> labels = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            labels.add(formatter.format(LocalDate.of(currentYear, month, 1)));
        }

        // Assemblage du graphique
        chartData.addChartDataSet(currentYearSet);
        chartData.addChartDataSet(lastYearSet);
        chartData.setLabels(labels);
        lineModel.setData(chartData);
    }

    private List<Number> fetchMonthlyData(int year) {
        return withErrorHandling(
                () -> dashboardFacade.getDetailsEvenementsParMois(year),
                "events.monthly.error"
        );
    }

    private LineChartDataSet createLineDataSet(String label, String color, List<Number> values) {
        LineChartDataSet dataSet = new LineChartDataSet();
        dataSet.setLabel(label);
        dataSet.setData(new ArrayList<>(values));
        dataSet.setBorderColor(color);
        dataSet.setFill(false);
        dataSet.setTension(0.4);
        return dataSet;
    }

    public void createPieModel() {
        pieModel = new PieChartModel();
        ChartData chartData = new ChartData();

        // Configuration du dataset
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(List.of(
                data.theatreEvents,
                data.concertEvents,
                data.festivalEvents,
                data.cinemaEvents,
                data.matchEvents,
                data.releaseEvents,
                data.expositionEvents,
                data.showcaseEvents));
        dataSet.setBackgroundColor(List.of(
                COLOR_THEATRE,
                COLOR_CONCERT,
                COLOR_FESTIVAL,
                COLOR_CINEMA,
                COLOR_MATCH,
                COLOR_RELEASE_PARTY,
                COLOR_EXPOSITION,
                COLOR_SHOWCASE));

        chartData.addChartDataSet(dataSet);
        chartData.setLabels(List.of(TypeEvenement.CINEMA.toString(),
                TypeEvenement.CONCERT.toString(),
                TypeEvenement.EXPOSITION.toString(),
                TypeEvenement.MATCH.toString(),
                TypeEvenement.RELEASE_PARTY.toString(),
                TypeEvenement.FESTIVAL.toString(),
                TypeEvenement.SHOWCASE.toString(),
                TypeEvenement.THEATRE.toString()));
        pieModel.setData(chartData);
    }

    // Classe interne pour structurer les données
    @Getter
    @Setter
    public static class DashboardData {
        private LocalDate dateDebut;
        private LocalDate dateFin;
        private String formattedPeriod;
        private Integer evenementsAvenirs;
        private Integer evenementsEnCours;
        private Integer evenementsTermines;
        private Integer nbreEvenement;
        private Integer nbreTickets;
        private Integer festivalEvents;
        private Integer concertEvents;
        private Integer showcaseEvents;
        private Integer theatreEvents;
        private Integer cinemaEvents;
        private Integer matchEvents;
        private Integer releaseEvents;
        private Integer expositionEvents;
        private Integer nbreEventsThisYear;
        private Integer nbreEventsLastYear;
    }
}