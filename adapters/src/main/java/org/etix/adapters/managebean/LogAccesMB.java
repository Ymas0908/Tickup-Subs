package org.etix.adapters.managebean;



import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.etix.adapters.entities.Security.LogAccesEntity;
import org.etix.adapters.notification.FlashMessage;
import org.etix.adapters.driver.facades.GestionSecuriteFacade;
import org.etix.domain.models.enumerations.TypeLog;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @author ITCENTREX
 */

@ManagedBean
@ViewScoped
@Named(value = "logAccesMB")
public class LogAccesMB implements Serializable {

    private static final long serialVersionUID = 2055561905397068116L;

    @Autowired
    private GestionSecuriteFacade logAccesService;

    @Getter
    @Setter
    private List<LogAccesEntity> logAccess;

    @Getter
    @Setter
    private List<TypeLog> typeLogAccess;

    @Getter
    @Setter
    private LogAccesEntity logAcces;

    @Getter
    @Setter
    private TypeLog typeLog;


    @Getter
    @Setter
    private LocalDateTime dateA;
    @Getter
    @Setter
    private LocalDateTime dateB;

    @PostConstruct
    public void init() {
        try {

            this.typeLogAccess = Arrays.asList(TypeLog.values());
            this.dateB = LocalDateTime.now();
            this.dateA = dateB.minusDays(7);

            this.typeLog = null;
            this.logAccess = logAccesService.getLogAccessDtoByDateBetweenAndTypeLog(dateA, dateB, typeLog);
            this.resetLogAcces();
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue!", ex.getMessage());
        }
    }

    public void showSelectedLogAccess() {
        try {
            if (logAcces == null || logAcces.getId() == null) {
                FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", "Log d'accès à afficher est introuvable.");
                return;
            }
            PrimeFaces.current().executeScript("PF('requiredParamNotFoundDlg').show()");
            PrimeFaces.current().ajax().update("resultatRecherche:requiredParamNotFoundID");
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void resetLogAcces() {
        this.logAcces = new LogAccesEntity();
    }

    public void searchFeature() {
        try {
            this.logAccess = logAccesService.getLogAccessDtoByDateBetweenAndTypeLog(dateA, dateB, typeLog);
        } catch (Exception ex) {
            FlashMessage.flash(FlashMessage.ERROR, "Erreur survenue !", ex.getMessage());
            ex.printStackTrace();
        }
    }

    public String generateLogExportName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return "Log d'accès - du  " + dateA.format(formatter) + " au " + dateB.format(formatter);
    }
}
