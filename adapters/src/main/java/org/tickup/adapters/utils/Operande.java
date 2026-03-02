package org.tickup.adapters.utils;


public class Operande {

    private static final String OPERANDE_PARENTHESE_OUVRANTE = "(";
    private static final String OPERANDE_PARENTHESE_FERMENTE = ")";
    private static final String OPERANDE_ADDITION = "+";
    private static final String OPERANDE_DIFFERENCE = "-";
    private static final String OPERANDE_MULTIPLE = "*";
    private static final String OPERANDE_DIVISION = "/";
    private static final String OPERANDE_POURCENT = "100";

    public static String[] values() {
        return new String[]{OPERANDE_PARENTHESE_OUVRANTE,
                OPERANDE_PARENTHESE_FERMENTE,
                OPERANDE_ADDITION,
                OPERANDE_DIFFERENCE,
                OPERANDE_MULTIPLE,
                OPERANDE_DIVISION,
                OPERANDE_POURCENT
        };
    }

}
