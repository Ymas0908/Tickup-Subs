package org.tickup.domain.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomPasswordGenerator {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "@#$&";

    private static final SecureRandom random = new SecureRandom();


    /**
     * Génère un mot de passe aléatoire.
     *
     * @param length              longueur souhaitée (doit être >= 1)
     * @param useLower            inclure des lettres minuscules
     * @param useUpper            inclure des lettres majuscules
     * @param useDigits           inclure des chiffres
     * @param useSymbols          inclure des symboles
     * @param ensureEachCategory  si true, garantit qu'au moins un caractère
     *                            de chaque catégorie sélectionnée sera présent
     * @return mot de passe généré
     * @throws IllegalArgumentException si aucune catégorie n'est sélectionnée
     *                                  ou si length est trop petit pour ensureEachCategory
     */
    public static String generate(int length,
                                  boolean useLower,
                                  boolean useUpper,
                                  boolean useDigits,
                                  boolean useSymbols,
                                  boolean ensureEachCategory) {

        if (length < 1) {
            throw new IllegalArgumentException("length must be >= 1");
        }

        // Construire l'ensemble des caractères autorisés
        StringBuilder allowed = new StringBuilder();
        List<Character> guaranteed = new ArrayList<>();

        if (useLower) {
            allowed.append(LOWER);
            if (ensureEachCategory) guaranteed.add(pickRandomChar(LOWER));
        }
        if (useUpper) {
            allowed.append(UPPER);
            if (ensureEachCategory) guaranteed.add(pickRandomChar(UPPER));
        }
        if (useDigits) {
            allowed.append(DIGITS);
            if (ensureEachCategory) guaranteed.add(pickRandomChar(DIGITS));
        }
        if (useSymbols) {
            allowed.append(SYMBOLS);
            if (ensureEachCategory) guaranteed.add(pickRandomChar(SYMBOLS));
        }

        if (allowed.isEmpty()) {
            throw new IllegalArgumentException("At least one character category must be selected");
        }

        if (ensureEachCategory && guaranteed.size() > length) {
            throw new IllegalArgumentException("length is too small to include at least one of each selected category");
        }

        // Remplir le mot de passe avec des caractères aléatoires parmi les autorisés

        // ajouter d'abord les caractères garantis (si demandés)
        List<Character> passwordChars = new ArrayList<>(guaranteed);

        for (int i = guaranteed.size(); i < length; i++) {
            char c = pickRandomChar(allowed.toString());
            passwordChars.add(c);
        }

        // Mélanger pour éviter que les caractères garantis soient toujours en début
        Collections.shuffle(passwordChars, random);

        // Construire la chaîne finale
        StringBuilder password = new StringBuilder(length);
        for (char c : passwordChars) {
            password.append(c);
        }

        return password.toString();
    }

    // Méthode utilitaire pour choisir un caractère aléatoire dans une chaîne
    private static char pickRandomChar(String chars) {
        int idx = random.nextInt(chars.length());
        return chars.charAt(idx);
    }

    // Méthode simple par défaut (exemple d'utilisation)
    public static String generateDefault(int length) {
        // lettres minuscules + majuscules + chiffres + symboles, garantir chaque catégorie
        return generate(length, true, true, true, true, true);
    }
}
