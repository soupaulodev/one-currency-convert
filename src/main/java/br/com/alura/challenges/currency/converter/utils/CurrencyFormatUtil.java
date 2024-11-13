package br.com.alura.challenges.currency.converter.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Utility class for formatting currency values.
 */
public class CurrencyFormatUtil {

    private static final String DEFAULT_PATTERN = "#,##0.00";

    /**
     * Formats the given BigDecimal value with a specified prefix.
     *
     * @param value the BigDecimal value to format.
     * @param prefix the prefix to add before the formatted value.
     * @return the formatted currency string with the prefix.
     */
    public String toFormat(final BigDecimal value, final String prefix) {
        return formatCurrency(value, prefix + " ");
    }

    /**
     * Formats the given BigDecimal value with a specified prefix.
     *
     * @param value the BigDecimal value to format.
     * @param prefix the prefix to add before the formatted value.
     * @return the formatted currency string with the prefix.
     */
    private String formatCurrency(final BigDecimal value, final String prefix) {
        var currencyFmt = new DecimalFormat(DEFAULT_PATTERN);
        currencyFmt.setPositivePrefix(prefix);
        return currencyFmt.format(value);
    }
}