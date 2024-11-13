package br.com.alura.challenges.currency.converter.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Record representing a currency with its base code, conversion rates, and the last update time.
 *
 * @param baseCode the code of the base currency.
 * @param conversionRates a map containing conversion rates to other currencies.
 * @param timeLastUpdate the time when the conversion rates were last updated.
 */
public record Currency(String baseCode, Map<String, BigDecimal> conversionRates, LocalDateTime timeLastUpdate) {
}