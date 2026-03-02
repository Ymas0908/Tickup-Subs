package org.etix.domain.exceptions;

public class ExpressionEvaluationException extends RuntimeException {

    private static final long serialVersionUID = 7567715988288003928L;
    protected int status;

    public ExpressionEvaluationException(String message) {
        super(message);
        this.status = 403;
    }

    public ExpressionEvaluationException(String message, Exception ex) {
        super(message, ex);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
