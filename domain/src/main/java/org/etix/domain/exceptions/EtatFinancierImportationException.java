/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.etix.domain.exceptions;

/**
 * @author TRAOREDaouda
 */
public class EtatFinancierImportationException extends RuntimeException {


    private static final long serialVersionUID = 306527267382666525L;

    public EtatFinancierImportationException(String message) {
        super(message);
    }

    public EtatFinancierImportationException(String message, Throwable e) {
        super(message, e);
    }

}
