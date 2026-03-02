/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.etix.domain.exceptions;

/**
 * @author ITCENTREX
 */
public class EntityAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 5039396320941162248L;

    protected int status;

    public EntityAlreadyExistsException(String message) {
        super(message);
        this.status = 409;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
