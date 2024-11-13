package br.com.alura.challenges.currency.converter.models;

import br.com.alura.challenges.currency.converter.utils.CurrencyFormatUtil;
import br.com.alura.challenges.currency.converter.utils.LocalDateTimeParseUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents the history of a currency conversion.
 */
public class CurrencyHistory {

    private final String currency;
    private final BigDecimal value;
    private final CurrencyTarget target;
    private final LocalDateTime timeLastUpdate;
    private LocalDateTime registeredIn;

    /**
     * Constructs a new CurrencyHistory instance.
     *
     * @param currency the code of the currency.
     * @param value the value of the currency.
     * @param target the target currency and its value.
     * @param timeLastUpdate the time of the last update.
     */
    public CurrencyHistory(final String currency,
                           final BigDecimal value,
                           final CurrencyTarget target,
                           final LocalDateTime timeLastUpdate) {
        this.currency = currency;
        this.value = value;
        this.target = target;
        this.timeLastUpdate = timeLastUpdate;
    }

    /**
     * Gets the currency code.
     *
     * @return the currency code.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Gets the currency value.
     *
     * @return the currency value.
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Gets the target currency and its value.
     *
     * @return the target currency and its value.
     */
    public CurrencyTarget getTarget() {
        return target;
    }

    /**
     * Gets the registration time.
     *
     * @return the time when the currency history was registered.
     */
    public LocalDateTime getRegisteredIn() {
        return registeredIn;
    }

    /**
     * Sets the registration time.
     *
     * @param registeredIn the time when the currency history was registered.
     */
    public void setRegisteredIn(LocalDateTime registeredIn) {
        this.registeredIn = registeredIn;
    }

    /**
     * Gets the time of the last update.
     *
     * @return the time of the last update.
     */
    public LocalDateTime getTimeLastUpdate() {
        return timeLastUpdate;
    }

    /**
     * Returns a string representation of the currency history.
     *
     * @return a formatted string representing the currency history.
     */
	@Override
	public String toString() {
		var currencyFmt = new CurrencyFormatUtil();
		return """
                Registrado em %s
                [Moeda %s | Conversão %s]
                Ultima atualização da cotação [%s]"""
				.formatted(
						LocalDateTimeParseUtil.toComplete(registeredIn),
						currencyFmt.toFormat(value, currency),
						currencyFmt.toFormat(target.value(), target.currency()),
						LocalDateTimeParseUtil.toComplete(timeLastUpdate)
				);
	}
}