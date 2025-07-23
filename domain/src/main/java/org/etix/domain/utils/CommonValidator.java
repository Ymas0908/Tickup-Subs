package org.etix.domain.utils;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * @author Kassi
 */
public class CommonValidator {

    private static final String EMAIIL_REGEX = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String PHONE_REGEX = "^\\+?[0-9][0-9]{7,14}$";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Pattern EMAIIL_PATTERN;
    private static final Pattern PHONE_PATTERN;

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

    public static boolean isValidDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) return false;

        try {
            LocalDate parsedDate = LocalDate.parse(dateStr, FORMATTER);
            LocalDate today = LocalDate.now();

            // La date doit être inférieure ou égale à aujourd’hui
            return !parsedDate.isAfter(today);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidRib(String iban) {
        if (iban == null) return false;

        // Retirer les espaces
        String cleaned = iban.replaceAll("\\s+", "");

        // Regex : 2 lettres (pays) + 2 chiffres (clé) + 3 chiffres (banque) + 5 chiffres (guichet) + 11 à 20 chiffres (compte) + 2 chiffres (clé)
        String regex = "^[A-Z]{2}\\d{3}\\d{5}\\d{11,12}\\d{2}$";
        return cleaned.matches(regex);
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
     * Cette méthode permet de valider la référence du souscripteur
     * @param referenceSouscripteur
     * @return
     */
    public static boolean isValidReferenceSouscripteur(String referenceSouscripteur) {
        return referenceSouscripteur.length() == 15 && referenceSouscripteur.matches("[0-9]+");
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

    public static boolean isValidReferenceSouscription(String reference) {
        return reference.length() >= 22 && reference.length() <= 24  && reference.matches("[0-9]+");
    }
}
