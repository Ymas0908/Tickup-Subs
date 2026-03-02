package org.tickup.adapters.utils;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * @author Kassi
 */
public class CommonValidator {

    private static String EMAIIL_REGEX = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static String PHONE_REGEX = "^\\+?[1-9][0-9]{7,14}$";

    private static Pattern EMAIIL_PATTERN;
    private static Pattern PHONE_PATTERN;

    static {
        EMAIIL_PATTERN = Pattern.compile(EMAIIL_REGEX);
        PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    }

    /**
     * @param value
     * @return
     */
    public static boolean isValidTelephone(String value) {
        return PHONE_PATTERN.matcher(value).matches();
    }

    /**
     * @param value
     * @return
     */
    public static boolean isValidEmail(String value) {
        return EMAIIL_PATTERN.matcher(value).matches();
    }

    /**
     * @param value
     * @return
     */
    public static boolean isValidAmount(BigDecimal value) {
        return null != value && value.compareTo(BigDecimal.ZERO) >= 0;
    }


    /**
     * @param tauxInteret
     * @return
     */
    public static boolean isValidTauxInteret(Float tauxInteret) {
        return FloatRangeOperators.isInOpenRange(tauxInteret, 0.1f, 20f);
    }

    /**
     * @param anneeExercice
     * @return
     */
    public static boolean isValidAnneeExercice(Integer anneeExercice) {
        int year = LocalDate.now().getYear();
        return IntRangeOperators.isInOpenClosedRange(anneeExercice, year - 2, year + 2);
    }

    public static boolean isValidDateExercice(LocalDate dateExercice) {
        return dateExercice != null && !dateExercice.isAfter(LocalDate.now());
    }

}
