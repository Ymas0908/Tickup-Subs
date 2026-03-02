package org.etix.adapters.notification;

import lombok.Getter;

@Getter
public class MailResponse {

    private String message;
    private int status;

    public MailResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
