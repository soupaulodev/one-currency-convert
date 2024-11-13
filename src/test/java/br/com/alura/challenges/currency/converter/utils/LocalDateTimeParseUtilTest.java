package br.com.alura.challenges.currency.converter.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateTimeParseUtilTest {

    @Test
    void testConvertRFC1123_ValidDate() {
        String rfc1123Date = "Tue, 3 Jun 2008 11:05:30 GMT";
        LocalDateTime expectedDateTime = ZonedDateTime.parse(rfc1123Date, DateTimeFormatter.RFC_1123_DATE_TIME)
                .withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();

        LocalDateTime result = LocalDateTimeParseUtil.convertRFC1123(rfc1123Date);

        assertNotNull(result);
        assertEquals(expectedDateTime, result);
    }

    @Test
    void testConvertRFC1123_BlankDate() {
        String blankDate = " ";

        Executable executable = () -> LocalDateTimeParseUtil.convertRFC1123(blankDate);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Invalid date.", exception.getMessage());
    }

    @Test
    void testConvertRFC1123_InvalidDate() {
        String invalidDate = "Invalid Date String";

        LocalDateTime result = LocalDateTimeParseUtil.convertRFC1123(invalidDate);

        assertNull(result);
    }

    @Test
    void testToComplete_ValidDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 10, 5, 14, 30, 0);
        String expectedFormattedDate = "Thu 5/10/2023 02:30:00";

        String result = LocalDateTimeParseUtil.toComplete(dateTime);

        assertEquals(expectedFormattedDate, result);
    }

    @Test
    void testToComplete_NullDateTime() {
        LocalDateTime dateTime = null;

        Executable executable = () -> LocalDateTimeParseUtil.toComplete(dateTime);

        assertThrows(NullPointerException.class, executable);
    }
}