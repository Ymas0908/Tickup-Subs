package org.etix.adapters.notification;


import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

/**
 * @author ITCENTREX
 */
public class FlashMessage {

    public static String INFO = "INFO";
    public static String WARN = "WARN0";
    public static String ERROR = "ERROR";


    public static void flash(String type, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(getSeverityLevel(type), summary, detail));
    }


    private static FacesMessage.Severity getSeverityLevel(String type) {
        switch (type) {
            case "ERROR":
                return FacesMessage.SEVERITY_ERROR;
            case "WARN":
                return FacesMessage.SEVERITY_WARN;
            case "INFO":
                return FacesMessage.SEVERITY_INFO;
            default:
                return FacesMessage.SEVERITY_INFO;
        }
    }
}
