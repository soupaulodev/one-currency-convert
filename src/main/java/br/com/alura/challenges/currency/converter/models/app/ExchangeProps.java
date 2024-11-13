package br.com.alura.challenges.currency.converter.models.app;

/**
 * Record representing exchange properties.
 *
 * @param exchangeUri the URI for the exchange service.
 * @param exchangeKey the key for accessing the exchange service.
 * @param exchangeVersion the version of the exchange service.
 */
public record ExchangeProps(String exchangeUri, String exchangeKey, String exchangeVersion) {
}