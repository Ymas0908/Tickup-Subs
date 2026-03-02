package org.tickup.adapters.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class Formater {

    /**
     * Locale par defaut
     */
    private static final Locale defaultLocale = new Locale("fr", "ci");

    /**
     * Retourne un numéro avec des zéros avant
     *
     * @param number
     * @param length
     * @return
     */
    public static String beginNumberWithZero(int number, int length) {
        int numberLength = Integer.toString(number).length(); // On calcule la longueur du nombre
        int difference = length - numberLength;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < difference; i++) {
            stringBuilder.append("0");
        }
        return stringBuilder.toString() + number;
    }

    /**
     * Permet de trimer une chaine de caracteres
     *
     * @param word
     * @param search
     * @return
     */
    public static String trim(String word, String search) {
        while (Character.toString(word.charAt(word.length() - 1)).equals(search)) {
            word = word.substring(0, word.length() - 1);
        }
        return word;
    }

    /**
     * Retourne la derniere occurrence d'une chaine comprenant des "/"
     *
     * @param path
     * @return
     */
    public static String truncat(String path) {
        String[] parts = path.split("/");
        return parts[parts.length - 1];

    }

    /**
     * Détermine le nom d'un fichier /etc/toto.txt => toto.txt
     *
     * @param path
     * @return
     */
    public static String fileName(String path) {
        String[] pathParts = path.split(";");
        return pathParts[pathParts.length - 1];
    }

    /**
     * Renommer en enlevant
     *
     * @param name
     * @return
     */
    public static String fix(String name) {

        String[] parts = name.split("@");
        String folder = parts[parts.length - 1];
        return folder;
    }

    /**
     * Renommer en enlevant certains caractères comme (é, è, ê, ë, , à, â, ä ...)
     *
     * @param name
     * @return
     */
    public static String rename(String name) {
        name = name.replaceAll("é", "e");
        name = name.replaceAll("è", "e");
        name = name.replaceAll("ê", "e");
        name = name.replaceAll("ë", "e");
        name = name.replaceAll("à", "a");
        name = name.replaceAll("â", "a");
        name = name.replaceAll("ä", "a");
        name = name.replaceAll("ô", "o");
        name = name.replaceAll("ö", "o");
        name = name.replaceAll("'", "");
        name = name.replaceAll(" ", "-");
        name = name.replaceAll("/", "-");
        name = name.replaceAll("_", "-");
        return name;

    }

    public static String renameFileName(String name) {
        name = rename(name);
        name = name.replaceAll(" ", "_");
        name = name.replaceAll("/", "_");
        name = name.replaceAll("-", "_");
        return name;

    }

    public static String getLastName(String name) {
        String[] prenoms = name.split(" ");

        return prenoms[prenoms.length - 1];
    }

    public static String extractFileName(String filePath) {
        String[] params = filePath.split("/");
        int index = params.length - 1 >= 0 ? params.length - 1 : 0;
        return params[index];
    }

    /**
     * Tronquer une chaine de caractères à une longueur indiquée
     *
     * @param libelle
     * @param longueurMax
     * @return
     */
    public static String getExcerpt(String libelle, int longueurMax) {
        if (libelle != null && libelle.length() > longueurMax) {
            return libelle.substring(0, longueurMax) + "...";
        }
        return libelle;
    }

    /**
     * @param amount
     * @return
     */
    public static String formatAmount(double amount) {
        return handleFormatting(defaultLocale, amount);
    }

    /**
     * @param locale
     * @param amount
     * @return
     */
    private static String handleFormatting(Locale locale, double amount) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        return numberFormat.format(amount).replace("XOF", "FCFA");
    }

    public static String getBaseReference(int n) {
        // choisissez un caractére au hasard à partir de cette chaîne
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder s = new StringBuilder(n);
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int index = (str.length() * random.nextInt());
            s.append(str.charAt(index));
        }
        return s.toString();
    }

    public static String generateClientReference(long nbr) {
        String ref = "00000001";
        ref = ref.substring(0, ref.length() - (String.valueOf(nbr).length())) + nbr;
        return ref;
    }

    /**
     * @param value
     * @return
     */
    public static double getTwoDecimals(double value) {
        BigDecimal bd = new BigDecimal(value);
        BigDecimal rounded = bd.setScale(2, RoundingMode.FLOOR);
        return rounded.doubleValue();
    }

    /**
     * @param anneeExercice
     * @return
     */
    public static String anneeExerciceFormater(LocalDate anneeExercice) {
        return dateFormater(anneeExercice, "MM/yyyy");
    }

    /**
     * @param localDate
     * @param pattern
     * @return
     */
    public static String dateFormater(LocalDate localDate, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(localDate);
    }

    /**
     * @param localDate
     * @param pattern
     * @return
     */
    public static String getYear(LocalDate localDate) {
        return String.valueOf(localDate.getYear());
    }

    /**
     * @param localDate
     * @param pattern
     * @return
     */
    public static String getMonth(LocalDate localDate) {
        int monthValue = localDate.getMonthValue();
        return monthValue >= 10 ? String.valueOf(monthValue) : "0" + monthValue;
    }

    /**
     * @param expression
     * @param valeur
     * @return
     */
    public static String remplacerVariableParValeur(String expression, String valeur) {
        return expression;
    }

    /**
     * @param strNum
     * @return
     * @throws NumberFormatException
     */
    public static Double parseToDouble(String strNum) throws NumberFormatException {
        if (strNum == null || strNum.isBlank()) {
            return null;
        }
        try {
            return Double.parseDouble(strNum);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            throw new NumberFormatException(String.format("La valeur %s n'est pas un nombre valide.", strNum));
        }
    }

    /**
     * @param strNum
     * @return
     * @throws NumberFormatException
     */
    public static Double StringValueToDouble(String strNum) throws NumberFormatException {
        if (strNum == null || strNum.isBlank()) {
            return null;
        }
        try {
            strNum = strNum.replace(",", ".");
            return Double.valueOf(strNum);
        } catch (NumberFormatException ex) {

            ex.printStackTrace();
            throw new NumberFormatException(String.format("La valeur %s n'est pas un nombre valide.", strNum));
        }
    }


}
