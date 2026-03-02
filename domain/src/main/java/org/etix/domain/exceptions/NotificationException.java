/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.etix.domain.exceptions;

/**
 * @author ITCENTREX
 */
public class NotificationException extends RuntimeException {

    private static final long serialVersionUID = 6878638528962614247L;

    public NotificationException(String message) {
        super(message);
    }

    public NotificationException(String message, Throwable ex) {
        super(message, ex);
    }
}
