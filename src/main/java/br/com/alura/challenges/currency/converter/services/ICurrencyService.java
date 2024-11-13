package br.com.alura.challenges.currency.converter.services;

import br.com.alura.challenges.currency.converter.models.Currency;

import java.math.BigDecimal;

/**
 * Interface for currency-related services.
 */
public interface ICurrencyService {

    /**
     * Retrieves a Currency object based on the provided currency code.
     *
     * @param currency the code of the currency to retrieve.
     * @return the Currency object corresponding to the provided code.
     */
    Currency getCurrency(final String currency);

    /**
     * Converts a value to a target exchange rate using the provided Currency object.
     *
     * @param value the value to be converted.
     * @param targetExchange the target exchange rate code.
     * @param currency the Currency object containing exchange rate information.
     * @return the converted value as a BigDecimal.
     */
    BigDecimal convertValueBy(final BigDecimal value, final String targetExchange, final Currency currency);
}