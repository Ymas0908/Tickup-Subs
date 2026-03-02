/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.etix.domain.exceptions;

/**
 * @author ITCENTREX
 */
public class EntityNotExistsException extends RuntimeException {

    private static final long serialVersionUID = 4895993902340758983L;
    protected int status;

    public EntityNotExistsException(String message) {
        super(message);
        this.status = 404;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
