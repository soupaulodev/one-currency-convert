package br.com.alura.challenges.currency.converter;

import br.com.alura.challenges.currency.converter.configs.ApplicationConfig;
import br.com.alura.challenges.currency.converter.controllers.CurrencyController;
import br.com.alura.challenges.currency.converter.models.app.BannerProps;
import br.com.alura.challenges.currency.converter.services.impls.CurrencyService;
import br.com.alura.challenges.currency.converter.services.impls.ExchangeService;

/**
 * Main class to start the currency converter application.
 */
public class Main {

    /**
     * The main method to start the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        var config = new ApplicationConfig();
        var scanner = config.scanner();
        var props = config.propertiesUtil();
        var gson = config.gson();

        var bannerUtil = config.bannerUtil();
        var bannerProps = props.load(config.propertiesName(), BannerProps.class);

        var exchangeService = new ExchangeService();
        var currencyService = new CurrencyService(props, config.propertiesName(), exchangeService, gson);

        new CurrencyController(scanner, bannerUtil, bannerProps, currencyService).start();
    }
}