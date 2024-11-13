package br.com.alura.challenges.currency.converter.models;

import java.math.BigDecimal;

/**
 * Record representing a target currency and its value.
 *
 * @param currency the code of the target currency.
 * @param value the value associated with the target currency.
 */
public record CurrencyTarget(String currency, BigDecimal value) {
}