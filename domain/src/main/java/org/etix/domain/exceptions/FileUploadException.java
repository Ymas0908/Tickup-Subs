package org.etix.domain.exceptions;

public class FileUploadException extends RuntimeException {

    private static final long serialVersionUID = -363078757196653173L;

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Exception ex) {
        super(message, ex);
    }
}
