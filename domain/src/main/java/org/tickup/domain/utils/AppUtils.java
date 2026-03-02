package org.tickup.domain.utils;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppUtils {

    public static String generateReference(String prefix) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd-HHmmss-SSS");
        String date = now.format(formatter);
        SecureRandom random = new SecureRandom();
        int randomNum = random.nextInt(999);
        return prefix + date + randomNum;
    }

}
