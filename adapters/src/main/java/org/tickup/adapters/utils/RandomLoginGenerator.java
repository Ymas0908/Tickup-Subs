package org.tickup.adapters.utils;
import java.security.SecureRandom;

public class RandomLoginGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String genererCode4Chiffres() {
        int nombre = secureRandom.nextInt(10000);
        return String.format("%04d", nombre);
    }
}
