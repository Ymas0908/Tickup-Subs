package org.etix.adapters.utils;


import org.etix.adapters.entities.Security.StrategieCompteEntity;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PasswordStringGenerator {

    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String SPECIAL_CHARACTERS = "#?!@%&*";
    private static final String NUMBERS = "0123456789";
    private static final Random random = new SecureRandom();


    /**
     * Generer un mot de passe compatible avec une stratégie de compte.
     *
     * @param streategie
     * @return
     */
    public static String generatePassword(StrategieCompteEntity streategie) {
        return generateStringMatchingRegex(streategie.generateRegex(), streategie.getNbreCaractereMajuscule(), streategie.getNbrCaractereSpeciaux(), streategie.getNbrChiffre(), streategie.getLongueurMinimalePassword());
    }

    /**
     * @param regex
     * @param numUppercase
     * @param numSpecialChars
     * @param numDigits
     * @param minLength
     * @return
     */
    private static String generateStringMatchingRegex(String regex, int numUppercase, int numSpecialChars, int numDigits, int minLength) {

        List<Character> characters = new ArrayList<>();

        // Add uppercase letters
        for (int i = 0; i < numUppercase; i++) {
            characters.add(getRandomCharacter(UPPERCASE_LETTERS));
        }
        // Add special characters
        for (int i = 0; i < numSpecialChars; i++) {
            characters.add(getRandomCharacter(SPECIAL_CHARACTERS));
        }
        // Add digits
        for (int i = 0; i < numDigits; i++) {
            characters.add(getRandomCharacter(NUMBERS));
        }

        // Add remaining characters (minimum length: 8)
        int remainingLength = minLength - (numUppercase + numSpecialChars + numDigits);
        for (int i = 0; i < remainingLength; i++) {
            characters.add(getRandomCharacter(UPPERCASE_LETTERS + SPECIAL_CHARACTERS + NUMBERS));
        }

        // Shuffle the characters randomly
        Collections.shuffle(characters);

        // Convert the list of characters to a string
        StringBuilder sb = new StringBuilder(characters.size());
        for (Character c : characters) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Extraire un caractère de manière aléatoire dans une chaine de caractères.
     *
     * @param characters
     * @return
     */
    private static char getRandomCharacter(String characters) {
        int index = random.nextInt(characters.length());
        return characters.charAt(index);
    }
}