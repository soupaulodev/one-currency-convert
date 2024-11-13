package br.com.alura.challenges.currency.converter.utils;

import java.time.*;
import java.time.format.*;

/**
 * Utility class for parsing and formatting LocalDateTime objects.
 */
public class LocalDateTimeParseUtil {

    /**
     * Converts a date string in RFC 1123 format to a LocalDateTime object.
     *
     * @param target the date string in RFC 1123 format.
     * @return the corresponding LocalDateTime object, or null if the conversion fails.
     * @throws IllegalArgumentException if the target string is blank.
     */
    public static LocalDateTime convertRFC1123(final String target) {
        if (target.isBlank()) {
            throw new IllegalArgumentException("Invalid date.");
        }

        try {
            var zonedDateTime = ZonedDateTime.parse(target, DateTimeFormatter.RFC_1123_DATE_TIME);
            return zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
        } catch (DateTimeException e) {
            System.out.println("ERROR: Unable to convert date.");
        }

        return null;
    }

    /**
     * Formats a LocalDateTime object to a string with the pattern "EE d/MM/yyyy hh:mm:ss".
     *
     * @param dateTime the LocalDateTime object to format.
     * @return the formatted date string.
     */
    public static String toComplete(final LocalDateTime dateTime) {
        return DateTimeFormatter.ofPattern("EE d/MM/yyyy hh:mm:ss").format(dateTime);
    }
}