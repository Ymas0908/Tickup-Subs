package org.etix.domain.exceptions;

public class RequiredParamsNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2570686177477801868L;

    public RequiredParamsNotFoundException(String message) {
        super(message);
    }

    public RequiredParamsNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
