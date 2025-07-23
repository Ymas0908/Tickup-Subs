package org.etix.domain.utils;

/**
 * Generer une expression regulier pour le mot de passe par rapport au paramètres fournis
 *
 * @author Kassi
 */
public class PasswordRegExGenerator {

    /**
     * Générer la partie de l'expression régulière
     *
     * @param minLength
     * @param numUpperCase
     * @param numSpecialChars
     * @param numDigits
     * @param specialChars
     * @return
     */
    public static String generateRegex(int minLength, int numUpperCase, int numSpecialChars, int numDigits,
                                       String specialChars) {
        StringBuilder regexBuilder = new StringBuilder("^");
        regexBuilder.append("(?=(.*[A-Z]){" + numUpperCase + "})");
        regexBuilder.append("(?=(.*[" + escapeSpecialChars(specialChars) + "]){" + numSpecialChars + "})");
        regexBuilder.append("(?=(.*\\d){" + numDigits + "})");
        regexBuilder.append(".{" + minLength + ",}");
        regexBuilder.append("$");
        return regexBuilder.toString();
    }

    /**
     * Échapper les caractères spéciaux dans la liste pour
     * éviter les problèmes avec l'expression régulière
     *
     * @param specialChars
     * @return
     */
    private static String escapeSpecialChars(String specialChars) {
        return specialChars
                .replace("\\", "\\\\")
                .replace("[", "\\[")
                .replace("]", "\\]")
                .replace("(", "\\(")
                .replace(")", "\\)")
                .replace("{", "\\{")
                .replace("}", "\\}")
                .replace("*", "\\*")
                .replace("+", "\\+")
                .replace("-", "\\-")
                .replace(".", "\\.")
                .replace("?", "\\?")
                .replace("|", "\\|")
                .replace("^", "\\^")
                .replace("$", "\\$")
                .replace("&", "\\&");
    }

}
