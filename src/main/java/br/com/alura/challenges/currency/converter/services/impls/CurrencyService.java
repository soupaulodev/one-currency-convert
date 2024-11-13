package br.com.alura.challenges.currency.converter.services.impls;

import br.com.alura.challenges.currency.converter.exceptions.NotFoundException;
import br.com.alura.challenges.currency.converter.models.Currency;
import br.com.alura.challenges.currency.converter.models.app.ExchangeProps;
import br.com.alura.challenges.currency.converter.models.transfers.ExchangeDTO;
import br.com.alura.challenges.currency.converter.services.ICurrencyService;
import br.com.alura.challenges.currency.converter.services.IExchangeService;
import br.com.alura.challenges.currency.converter.utils.LocalDateTimeParseUtil;
import br.com.alura.challenges.currency.converter.utils.PropertiesUtil;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Service implementation for handling currency-related operations.
 */
public class CurrencyService implements ICurrencyService {

    private final IExchangeService exchangeService;
    private final String exchangeURI;
    private final Gson gson;

    /**
     * Constructor for CurrencyService.
     *
     * @param props the PropertiesUtil instance for loading properties.
     * @param propsPath the path to the properties file.
     * @param exchangeService the exchange service for making requests.
     * @param gson the Gson instance for JSON parsing.
     */
    public CurrencyService(PropertiesUtil props, String propsPath, IExchangeService exchangeService, Gson gson) {
        ExchangeProps exchangeProps = props.load(propsPath, ExchangeProps.class);
        this.exchangeService = exchangeService;
        this.exchangeURI = String.format("%s/%s/%s/", exchangeProps.exchangeUri(), exchangeProps.exchangeKey(), exchangeProps.exchangeVersion());
        this.gson = gson;
    }

    /**
     * Retrieves currency information based on the provided currency code.
     *
     * @param currency the currency code to retrieve information for.
     * @return a Currency object containing the currency information.
     * @throws NotFoundException if the currency information is not found.
     */
    @Override
    public Currency getCurrency(String currency) {
        String json = exchangeService.request(exchangeURI + currency.toUpperCase()).body();
        ExchangeDTO exchangeDTO = gson.fromJson(json, ExchangeDTO.class);
        if (!exchangeDTO.isNonNull()) {
            throw new NotFoundException("The indicated currency could not be found.");
        }

        String baseCode = exchangeDTO.baseCode();
        var timeLastUpdate = LocalDateTimeParseUtil.convertRFC1123(exchangeDTO.timeLastUpdateUtc());
        Map<String, BigDecimal> conversionRates = new HashMap<>();
        exchangeDTO.conversionRates().forEach((key, value) -> {
            if (value != null) {
                conversionRates.put(key, BigDecimal.valueOf(value));
            }
        });

        return new Currency(baseCode, conversionRates, timeLastUpdate);
    }

    /**
     * Converts a value to a target currency based on the provided Currency object.
     *
     * @param value the value to be converted.
     * @param targetExchange the target currency code.
     * @param currency the Currency object containing conversion rates.
     * @return the converted value as a BigDecimal.
     * @throws IllegalArgumentException if the target currency code is blank.
     * @throws NotFoundException if the target currency is not found in the conversion rates.
     */
    @Override
    public BigDecimal convertValueBy(BigDecimal value, String targetExchange, Currency currency) {
        if (targetExchange.isBlank()) {
            throw new IllegalArgumentException("Invalid operation, it is not possible to proceed without knowing the currency to be converted.");
        }

        BigDecimal targetCurrency = currency.conversionRates().get(targetExchange);
        if (targetCurrency == null) {
            throw new NotFoundException("It was not possible to find the exchange currency provided.");
        }

        return value.multiply(targetCurrency);
    }
}