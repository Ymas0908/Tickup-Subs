package org.etix.domain.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CentimesConverter {

    /**
     * Convertit une chaîne de centimes (par exemple "000000001250") en euros avec précision.
     *
     * @param value La chaîne représentant les centimes (ex: "000000001250").
     * @return Un BigDecimal représentant les euros (ex: 12.50).
     * @throws NumberFormatException si l'entrée est invalide.
     */
    public static String convertStringToDecimal(String value) {
        // Supprimer les zéros en tête et convertir en BigDecimal
        BigDecimal centimes = new BigDecimal(value);

        // Diviser par 100 pour avoir les euros
        BigDecimal euros = centimes.divide(new BigDecimal("100"));

        return euros.setScale(2).toString();
    }

    /**
     * Convertit un montant en euros (ex: 12.50) en centimes formatés avec zéros en tête (ex: "000000001250").
     *
     * @param value Le montant en euros.
     * @return Une chaîne de 12 caractères représentant les centimes.
     */
    public static String convertDecimalToString(String value) {
        BigDecimal decimalValue = new BigDecimal(value);
        // Multiplier par 100 et enlever les décimales
        BigDecimal centimes = decimalValue.multiply(new BigDecimal("100")).setScale(0, RoundingMode.HALF_UP);

        // Formater sur 12 chiffres avec padding de zéros à gauche
        return String.format("%012d", centimes.longValueExact());
    }


    /**
     * Convertit une chaîne de type "000000003000" en BigDecimal
     */
    public static String extraireMontant(String montantStr) {
        if (montantStr == null || montantStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Le montant ne peut pas être nul ou vide.");
        }

        if (montantStr.length() > 12) {
            throw new IllegalArgumentException("Le montant ne peut pas avoir plus de 12 chiffres.");
        }
	    // Convertir en BigDecimal
        return montantStr.replaceFirst("^0+(?!$)", "");
    }

    /**
     * Convertir une valeur XOF en format DCC ex : 25000 → 00000025000
     *
     * @param value
     * @return
     */
    public static String convertXofToDccFormat(String value) {
        try {
            if (value != null) {
                value = value.replaceAll(",", ".");
                int intValue = (int) Double.parseDouble(value);
                System.out.println("**************************" + intValue + "**************************");
                value = String.valueOf(intValue);
                System.out.println("**************************" + value.length() + "**************************");
                System.out.println("************************** Virgule " + value.length() + "**************************");
                value = value.replaceAll("\\.", "");
                System.out.println("************************** Point " + value.length() + "**************************");

                if (value.length() < 12) {
                    StringBuilder valueBuilder = new StringBuilder(value);

                    for (int i = valueBuilder.length(); i < 12; i++) {
                        valueBuilder.insert(0, "0");
                    }
                    value = valueBuilder.toString();

                }
            }
            return value;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid value: " + value, e);
        }
    }
}
