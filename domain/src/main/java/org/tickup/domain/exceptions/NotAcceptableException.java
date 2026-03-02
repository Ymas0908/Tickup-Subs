package org.tickup.domain.exceptions;

public class NotAcceptableException extends RuntimeException {
    private static final long serialVersionUID = 5039396320941162248L;

    protected int status;
    public NotAcceptableException(String message) {
        super(message);
        this.status = 406;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
