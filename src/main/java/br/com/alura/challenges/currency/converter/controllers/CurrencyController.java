package br.com.alura.challenges.currency.converter.controllers;

import br.com.alura.challenges.currency.converter.models.CurrencyHistory;
import br.com.alura.challenges.currency.converter.models.app.BannerProps;
import br.com.alura.challenges.currency.converter.models.app.enums.MenuState;
import br.com.alura.challenges.currency.converter.services.IActionConvertService;
import br.com.alura.challenges.currency.converter.services.IActionHistory;
import br.com.alura.challenges.currency.converter.services.ICurrencyService;
import br.com.alura.challenges.currency.converter.services.impls.ActionConvertService;
import br.com.alura.challenges.currency.converter.services.impls.ActionHistory;
import br.com.alura.challenges.currency.converter.utils.BannerUtil;
import br.com.alura.challenges.currency.converter.utils.InteractionUtil;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/**
 * Controller class for handling currency conversion operations.
 */
public class CurrencyController {

    private final int BREAK_LINE_LENGHT = 64;

    private final Scanner scanner;
    private final BannerUtil bannerUtil;
    private final BannerProps bannerProps;

    private final IActionConvertService convertService;
    private final IActionHistory historyService;

    private static final Set<CurrencyHistory> history = new HashSet<>();

    /**
     * Constructs a new CurrencyController with the specified dependencies.
     *
     * @param scanner the scanner for user input.
     * @param bannerUtil the utility for handling banners.
     * @param bannerProps the properties for the banner.
     * @param service the currency service.
     */
    public CurrencyController(final Scanner scanner,
                              final BannerUtil bannerUtil,
                              final BannerProps bannerProps,
                              final ICurrencyService service) {
        this.scanner = scanner;
        this.bannerUtil = bannerUtil;
        this.bannerProps = bannerProps;

        this.convertService = new ActionConvertService(service, scanner, BREAK_LINE_LENGHT);
        this.historyService = new ActionHistory(BREAK_LINE_LENGHT);
    }

    /**
     * Adds a currency history record to the history set.
     *
     * @param currency the currency history record to add.
     */
    public static synchronized void addCurrencyOfHistory(CurrencyHistory currency) {
        var now = LocalDateTime.now(Clock.systemDefaultZone());
        currency.setRegisteredIn(now);
        history.add(currency);
    }

    /**
     * Retrieves the set of currency history records.
     *
     * @return the set of currency history records.
     */
    public static synchronized Set<CurrencyHistory> getCurrencyHistory() {
        return history;
    }

    /**
     * Starts the currency conversion process.
     */
    public void start() {
        final var itr = new InteractionUtil();
        var state = MenuState.RUNNING;
        System.out.println(bannerUtil.load(bannerProps));

        do {
            state = choose();
            convertService.init(state);
            historyService.init(state);

            itr.breakSection(':', BREAK_LINE_LENGHT);
            itr.breakSection('*', BREAK_LINE_LENGHT);
        } while (state != MenuState.LEAVING);

        System.out.println("Obrigado por utilizar o conversor de moedas.\n");
    }

    /**
     * Displays the main menu.
     *
     * @return the main menu as a string.
     */
    private String mainMenu() {
        return """
                Menu Principal
                  1- Converter moeda
                  2- Historico de conversões
                  3- Sair""";
    }

    /**
     * Handles the user's menu choice and returns the corresponding menu state.
     *
     * @return the selected menu state.
     */
    private MenuState choose() {
        final var itr = new InteractionUtil();

        System.out.println(mainMenu());
        System.out.print("Opção: ");
        try {
            var option = scanner.nextInt();
            switch (option) {
                case 1 -> { return MenuState.CONVERT_CURRENCY; }
                case 2 -> { return MenuState.CONVERSIONS_HISTORY; }
                case 3 -> { return MenuState.LEAVING; }
                default -> throw new IllegalArgumentException("Essa opção não existente.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Não é possivel prosseguir, a opção selecionada não é válida.");
            itr.clearScanner(scanner);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return MenuState.RUNNING;
    }

}