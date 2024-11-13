package br.com.alura.challenges.currency.converter.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;

class CurrencyFormatUtilTest {

    @Test
    void testToFormatWithPrefix() {
        CurrencyFormatUtil util = new CurrencyFormatUtil();
        BigDecimal value = new BigDecimal("1234.56");
        String prefix = "$";
        String expected = "$ 1,234.56";
        String actual = util.toFormat(value, prefix);
        assertEquals(expected, actual);
    }

    @Test
    void testToFormatWithDifferentPrefix() {
        CurrencyFormatUtil util = new CurrencyFormatUtil();
        BigDecimal value = new BigDecimal("9876543.21");
        String prefix = "€";
        String expected = "€ 9,876,543.21";
        String actual = util.toFormat(value, prefix);
        assertEquals(expected, actual);
    }

    @Test
    void testToFormatWithZeroValue() {
        CurrencyFormatUtil util = new CurrencyFormatUtil();
        BigDecimal value = BigDecimal.ZERO;
        String prefix = "£";
        String expected = "£ 0.00";
        String actual = util.toFormat(value, prefix);
        assertEquals(expected, actual);
    }
}