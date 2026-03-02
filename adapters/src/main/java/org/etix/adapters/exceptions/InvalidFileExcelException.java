package org.etix.adapters.exceptions;

public class InvalidFileExcelException extends RuntimeException {


    private static final long serialVersionUID = 4196687929187351621L;

    public InvalidFileExcelException(String message) {
        super(message);
    }

    public InvalidFileExcelException(String message, Exception ex) {
        super(message, ex);
    }
}
