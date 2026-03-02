package org.etix.domain.exceptions;

public class FileAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -363078757196653173L;

    public FileAlreadyExistsException(String message) {
        super(message);
    }

    public FileAlreadyExistsException(String message, Exception ex) {
        super(message, ex);
    }
}
