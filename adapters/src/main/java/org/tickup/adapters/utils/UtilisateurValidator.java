package org.tickup.adapters.utils;


import java.util.List;

public class UtilisateurValidator {

    /**
     * @param value
     * @return
     */
    public static boolean isValidNom(String value) {
        return null != value && !value.isBlank() && IntRangeOperators.isInOpenRange(value.length(), 2, 50);
    }

    /**
     * @param value
     * @return
     */
    public static boolean isValidPrenoms(String value) {
        return null != value && !value.isBlank() && IntRangeOperators.isInOpenRange(value.length(), 2, 80);
    }

    /**
     * @param value
     * @return
     */
    public static boolean isValidCodeExploitant(String value) {
        return null != value && !value.isBlank() && IntRangeOperators.isInOpenRange(value.length(), 2, 10);
    }

    /**
     * @param value
     * @return
     */
    public static boolean isValidLogin(String value) {
        return null != value && !value.isBlank() && IntRangeOperators.isInOpenRange(value.length(), 2, 30);
    }

//    /**
//     * @param value
//     * @return
//     */
//    public static boolean isValidGenre(Genre value) {
//        return null != value && List.of(Genre.values()).contains(value);
//    }

    /**
     * @param value
     * @return
     */
    public static boolean isValidStatutActif(Boolean value) {
        return null != value && List.of(Boolean.FALSE, Boolean.TRUE).contains(value);
    }

    /**
     * @param value
     * @return
     */
    public static boolean isValidTelephone(String value) {
        return CommonValidator.isValidTelephone(value);
    }

    /**
     * @param value
     * @return
     */
    public static boolean isValidEmail(String value) {
        return CommonValidator.isValidEmail(value);
    }

    /**
     * @param strategieCompte
     * @param password
     * @return
     */
//    public static boolean isValidPassword(StrategieCompte strategieCompte, String password) {
//        return Pattern.compile(strategieCompte.getGeneratedRegex()).matcher(password).matches();
//    }
}
