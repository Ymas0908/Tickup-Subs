package org.tickup.adapters.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Kassi
 */
public class ReferenceGenerator {

    private static final Set<Character> EXCLUDED_CHARACTERS = new HashSet<>();

    static {
        EXCLUDED_CHARACTERS.add('-');
        EXCLUDED_CHARACTERS.add('_');
    }

    public static String generateReference(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        String[] words = input.split("\\s+");
        StringBuilder reference = new StringBuilder();
        if (words.length >= 2) {
            reference.append(words[0].substring(0, 1));
            reference.append(words[1].substring(0, 1));
        } else {
            reference.append(input.substring(0, Math.min(input.length(), 2)));
        }

        for (String word : words) {
            reference.append(getValidCharacter(word.charAt(0)));
        }

        return reference.toString().toUpperCase();
    }


    public static String normalize(String value) {
        String normalizedValue = value.replaceAll("[.'_\\-;,!:|]", "")
                .replaceAll("[ÀÁÂÃÄÅàáâãäå]", "A")
                .replaceAll("[ÈÉÊËèéêë]", "E")
                .replaceAll("[ÌÍÎÏìíîï]", "I")
                .replaceAll("[ÒÓÔÕÖØòóôõöø]", "O")
                .replaceAll("[ÙÚÛÜùúûü]", "U")
                .replaceAll("[Ýýÿ]", "Y")
                .replaceAll("[Çç]", "C")
                .replaceAll("[Ññ]", "N")
                .replaceAll("[^\\p{ASCII}]", ""); // Supprime les caractères non correspondants
        return normalizedValue;
    }

    private static char getValidCharacter(char c) {
        if (Character.isLetterOrDigit(c) && !EXCLUDED_CHARACTERS.contains(c)) {
            return c;
        }
        // Remplacez le caractère invalide par un caractère par défaut (par exemple,
        // 'X')
        return 'X';
    }

    /**
     * Generer la reférence d'une demande de crédit pour un client
     *
     * @param refClient
     * @param nbDemandeCredit
     * @return
     */
    public static String genererReferenceDemandeCredit(String refClient, int nbDemandeCredit) {
        return refClient + "D-" + String.format("%04d", nbDemandeCredit);
    }

    /**
     * Generer la reférence d'un client
     *
     * @param nomPart
     * @param rangClient
     * @return
     */
    public static String genererReferenceClient(String name, String year, int sequence) {
        return ReferenceGenerator.extract(ReferenceGenerator.normalize(name), 2) + pop(year, 2) + "-"
                + String.format("%06d", sequence);
    }

    /**
     * @param value
     * @param length
     * @return
     */
    private static String pop(String value, int length) {
        return value.substring(value.length() - length, value.length());
    }

    /**
     * @param input
     * @param length
     * @return
     */
    public static String extract(String input, int length) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        String[] words = input.split("\\s+");
        StringBuilder reference = new StringBuilder();
        if (words.length >= length) {
            for (String part : words) {
                reference.append(part.substring(0, 1));
            }
        } else {
            reference.append(input.substring(0, Math.min(input.length(), length)));
        }
        return reference.toString().toUpperCase();
    }
}