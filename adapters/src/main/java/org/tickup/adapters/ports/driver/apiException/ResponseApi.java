package org.tickup.adapters.ports.driver.apiException;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Réponse de l'API Rest
 *
 * @author Luc
 */
@Setter
@Getter
public class ResponseApi implements Serializable {


    private static final long serialVersionUID = 1678596161217027130L;

    // SETTERS
    // GETTERS
    private String message; // Le message de retour
    private int status; // Le satus
    private Object body; // Le contenu de le réponse
    private Date timestamp; // La date et l'heure

    public ResponseApi() {
        this.timestamp = new Date();
    }

    public ResponseApi(String message) {
        this.message = message;
    }


    public ResponseApi(String message, int status, Object body) {
        this.message = message;
        this.status = status;
        this.body = body;
        this.timestamp = new Date();
    }

}
