package org.etix.domain.exceptions;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -363078757196653173L;
    protected int status;

    public BadRequestException(String message) {
        super(message);
        this.status = 400;
    }

    public BadRequestException(String message, Exception ex) {
        super(message, ex);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
