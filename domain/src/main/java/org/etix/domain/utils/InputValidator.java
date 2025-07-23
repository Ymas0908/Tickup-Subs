package org.etix.domain.utils;

import java.util.regex.Pattern;

public class InputValidator {

    // Mots-clés SQL dangereux
    private static final Pattern SQL_KEYWORDS_PATTERN = Pattern.compile(
            "(?i)\\b(delete|update|drop|alter|truncate|exec|union|insert|shutdown|grant|revoke)\\b");

    // Mots suspects liés aux scripts, XSS, etc.
    private static final Pattern SUSPICIOUS_WORDS_PATTERN = Pattern.compile(
            "(?i)\\b(script|alert|onerror|onload|hacked|javascript|confirm|prompt|string)\\b");

    // Caractères spéciaux dangereux
    private static final Pattern SPECIAL_CHARS_PATTERN = Pattern.compile(
            "[<>\"'%;()&+\\-\\[\\]{}]");

    // Vérifie si trop d'espaces sont présents ou si la chaîne est vide après suppression des espaces
    private static boolean hasTooManySpaces(String input) {
        return input.trim().isEmpty() || input.matches(".*\\s{3,}.*");
    }

    /**
     * Vérifie si une chaîne d'entrée est sécurisée.
     */
    public static boolean isSafeInput(String input) {

        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        boolean b = !SQL_KEYWORDS_PATTERN.matcher(input).find()
                && !SUSPICIOUS_WORDS_PATTERN.matcher(input).find()
                && !SPECIAL_CHARS_PATTERN.matcher(input).find()
                && !hasTooManySpaces(input);
        System.out.println("input:::::::::: : " + input + " : " + b);
        return b;
    }

}
