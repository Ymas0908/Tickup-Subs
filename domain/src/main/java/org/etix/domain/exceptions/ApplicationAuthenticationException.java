/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.etix.domain.exceptions;

/**
 * @author ITCENTREX
 */
public class ApplicationAuthenticationException extends RuntimeException {
    private static final long serialVersionUID = 6878638528962614247L;
    protected int status;

    public ApplicationAuthenticationException(String message) {
        super(message);
        this.status = 401;
    }

    public ApplicationAuthenticationException(String message, Throwable ex) {
        super(message, ex);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
