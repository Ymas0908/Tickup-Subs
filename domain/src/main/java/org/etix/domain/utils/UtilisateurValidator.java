package org.etix.domain.utils;



import org.etix.domain.models.Security.StrategieCompte;
import org.etix.domain.models.enumerations.Genre;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

public class UtilisateurValidator {

    /**
     * @param value
     * @return
     */
    public static boolean isValidNom(String value) {
        return null != value && !value.isBlank() && IntRangeOperators.isInOpenRange(value.length(), 1, 50);
    }

    public static boolean isValidDateNaissance(LocalDate dateNaissance) {
        return dateNaissance.isBefore(LocalDate.now());
    }

    /**
     * @param value
     * @return
     */
    public static boolean isValidPrenoms(String value) {
        return null != value && !value.isBlank() && IntRangeOperators.isInOpenRange(value.length(), 1, 80);
    }

    /**
     * @param value
     * @return
     */
    public static boolean isValidCodeExploitant(String value) {
        return null != value && !value.isBlank() && IntRangeOperators.isInOpenRange(value.length(), 2, 10);
    }

    /**
     * @param value
     * @return
     */
    public static boolean isValidLogin(String value) {
        return null != value && !value.isBlank() && IntRangeOperators.isInOpenRange(value.length(), 2, 30);
    }

    /**
     * @param value
     * @return
     */
    public static boolean isValidGenre(Genre value) {
        return null != value && List.of(Genre.values()).contains(value);
    }

    /**
     * @param value
     * @return
     */
    public static boolean isValidStatutActif(Boolean value) {
        return null != value && List.of(Boolean.FALSE, Boolean.TRUE).contains(value);
    }

    /**
     * @param value
     * @return
     */
    public static boolean isValidTelephone(String value) {
        return CommonValidator.isValidTelephone(value);
    }

    /**
     * @param value
     * @return
     */
    public static boolean isValidEmail(String value) {
        if (null == value) return false;
        return CommonValidator.isValidEmail(value);
    }

    /* */

    /**
     * @param strategieCompte
     * @param password
     * @return
     */
    public static boolean isValidPassword(StrategieCompte strategieCompte, String password) {
        return Pattern.compile(strategieCompte.getGeneratedRegex()).matcher(password).matches();
    }

    /**
     * Validation de l'âge
     *
     * @param dateNaissance
     * @return
     */
    public static Boolean isValidAge(LocalDate dateNaissance) {
        int age = LocalDateTime.now().getYear() - dateNaissance.getYear();
        return age >= 16 && age <= 110;
    }

    /* *//**
     * @param value
     * @return
     *//*
    public static boolean isValidGenre(Genre value) {
        return null != value && List.of(Genre.values()).contains(value);
    }*/

    /**
     * @param nni
     * @return
     */

    public static boolean isValidNNI(String nni) {
        return nni != null && nni.matches("^\\d{11}$");
    }

    public static boolean isValidNumeroPiece(String numeroPiece) {
        return numeroPiece != null
                && !numeroPiece.isBlank()
                && numeroPiece.length() >= 8
                && numeroPiece.length() <= 15;
    }


}
