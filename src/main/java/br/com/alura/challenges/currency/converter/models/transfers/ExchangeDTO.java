package br.com.alura.challenges.currency.converter.models.transfers;

import java.util.Map;

/**
 * Record representing an exchange data transfer object (DTO).
 *
 * @param baseCode the code of the base currency.
 * @param conversionRates a map containing conversion rates to other currencies.
 * @param timeLastUpdateUtc the time of the last update in UTC.
 */
public record ExchangeDTO(String baseCode, Map<String, Double> conversionRates, String timeLastUpdateUtc) {

    /**
     * Checks if any of the fields are non-null.
     *
     * @return true if any of the fields are non-null, false otherwise.
     */
    public boolean isNonNull() {
        return baseCode != null ||
                conversionRates != null ||
                timeLastUpdateUtc != null;
    }

}