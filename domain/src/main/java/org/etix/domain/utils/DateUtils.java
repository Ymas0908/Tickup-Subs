package org.etix.domain.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    /**
     * Convertit l'objet Date donné en un LocalDateTime en utilisant le fuseau horaire par défaut du système.
     *
     * @param dateToConvert l'objet Date à convertir ; ne doit pas être nul
     * @return la représentation correspondante en LocalDateTime de la Date donnée
     */
    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * Formate l'objet Date donné en une représentation sous forme de chaîne,
     * selon le format et la locale spécifiés.
     *
     * @param date   la Date à formater ; ne doit pas être nulle
     * @param format le modèle décrivant le format de date souhaité ; ne doit pas être nul
     * @param locale la locale à utiliser pour le formatage ; ne doit pas être nulle
     * @return une représentation sous forme de chaîne formatée de la Date donnée
     * @throws IllegalArgumentException si l’un des paramètres est nul
     */
    public static String formatDate(Date date, String format, Locale locale) {
        if (date == null || format == null || locale == null) {
            throw new IllegalArgumentException("La date, le format ou la locale ne peut pas être null");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        return sdf.format(date);
    }

    /**
     * Formate l'objet LocalDateTime donné en une représentation sous forme de chaîne,
     * selon le format et la locale spécifiés.
     *
     * @param dateTime le LocalDateTime à formater ; ne doit pas être nul
     * @param format   le modèle décrivant le format de date et d'heure souhaité ; ne doit pas être nul
     * @param locale   la locale à utiliser pour le formatage ; ne doit pas être nulle
     * @return une représentation sous forme de chaîne formatée du LocalDateTime donné
     * @throws IllegalArgumentException si l’un des paramètres est nul
     */
    public static String formatLocalDateTime(LocalDateTime dateTime, String format, Locale locale) {
        if (dateTime == null || format == null || locale == null) {
            throw new IllegalArgumentException("Le LocalDateTime, le format ou la locale ne peut pas être null");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, locale);
        return dateTime.format(formatter);
    }

    /**
     * Convertit la date donnée de type LocalDate en un LocalDateTime,
     * avec l'heure réglée au début de la journée.
     *
     * @param dateDebut la date à convertir en LocalDateTime
     * @return la représentation de la date donnée en LocalDateTime, au début de la journée 00:00:00
     */
    public static LocalDateTime convertDateDebutToDateTime(LocalDate dateDebut) {
        return dateDebut.atStartOfDay();
    }

    /**
     * Convertit la date donnée de type LocalDate en un LocalDateTime,
     * avec l'heure réglée à la fin de la journée.
     *
     * @param dateFin la date à convertir en LocalDateTime
     * @return la représentation de la date donnée en LocalDateTime, avec l'heure réglée à 00:00:00
     */
    public static LocalDateTime convertDateFinToDateTime(LocalDate dateFin) {
        return dateFin.atTime(0, 0, 0);
    }

    /**
     * Convertit une chaîne de caractères en un LocalDateTime selon un format donné.
     *
     * @param date
     * @param format
     * @return
     */
    public static LocalDate convertDateStringToLocalDateTime(String date, String format) {
        if (date == null || format == null) {
            throw new IllegalArgumentException("Date and format can't be null");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

            // Ajouter une heure par défaut (par exemple minuit)
            return LocalDate.parse(date.trim(), formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format or value: " + date, e);
        }
    }

    /**
     * Convertit une chaîne d'heure en LocalTime selon un format donné.
     *
     * @param timeString La chaîne d'heure (ex: "103005").
     * @param format     Le format d'heure (ex: "HHmmss").
     * @return L'heure en LocalTime.
     */
    public static LocalTime convertTimeStringToLocalTime(String timeString, String format) {
        if (timeString == null || format == null) {
            throw new IllegalArgumentException("Time and format can't be null");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format.trim());

        try {
            return LocalTime.parse(timeString.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format or value: " + timeString, e);
        }
    }

    /**
     * Convertit une chaîne de caractères en un LocalDateTime selon un format donné.
     *
     * @param dateDebut
     * @param dateFin
     * @param pattern
     * @param locale
     * @return
     */
    public static String getFormattedDateRange(LocalDate dateDebut, LocalDate dateFin, String pattern, Locale locale) {
        if (dateDebut == null || dateFin == null || pattern == null || locale == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, locale);
        return dateDebut.format(formatter) + " - " + dateFin.format(formatter);
    }
    public static String convertLocalDateTimeToString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    public static String convertLocalDateToString(LocalDate localDate, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(formatter);
    }


}
