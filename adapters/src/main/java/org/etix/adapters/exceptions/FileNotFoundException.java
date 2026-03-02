package org.etix.adapters.exceptions;

public class FileNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -363078757196653173L;

    public FileNotFoundException(String message) {
        super(message);
    }

    public FileNotFoundException(String message, Exception ex) {
        super(message, ex);
    }
}
