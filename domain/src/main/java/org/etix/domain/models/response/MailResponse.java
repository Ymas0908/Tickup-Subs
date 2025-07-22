package org.etix.domain.models.response;

public class MailResponse {

    private String message;
    private int status;

    public MailResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
