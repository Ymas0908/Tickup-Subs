package org.etix.adapters.exceptions;

public class UnauthorizedAccessException extends RuntimeException {

    private static final long serialVersionUID = -5158990535310011378L;


    public UnauthorizedAccessException(String message) {
        super(message);
    }

    public UnauthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
