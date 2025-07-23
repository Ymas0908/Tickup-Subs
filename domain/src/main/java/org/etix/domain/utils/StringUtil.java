package org.etix.domain.utils;

public class StringUtil {

    public static boolean hasLength(String value) {
        return null != value && !value.isBlank();
    }

    public static String maskAccountNumber(String numeroCompte) {
        final int TOTAL_LENGTH = 11; // Longueur fixe du numéro de compte
        final int NUM_VISIBLE_START = 3; // Nombre de chiffres visibles au début
        final int NUM_VISIBLE_END = 2;   // Nombre de chiffres visibles à la fin

        // Vérification des entrées
        if (numeroCompte == null || numeroCompte.length() != TOTAL_LENGTH) {
            throw new IllegalArgumentException("Le numéro de compte doit contenir exactement " + TOTAL_LENGTH + " chiffres.");
        }

        // Calcul du masque
        int maskLength = TOTAL_LENGTH - (NUM_VISIBLE_START + NUM_VISIBLE_END);
        String maskedPart = "*".repeat(maskLength);

        // Construire le numéro masqué
        String maskedAccount = numeroCompte.substring(0, NUM_VISIBLE_START)
                + maskedPart
                + numeroCompte.substring(TOTAL_LENGTH - NUM_VISIBLE_END);

        return maskedAccount;
    }
}
