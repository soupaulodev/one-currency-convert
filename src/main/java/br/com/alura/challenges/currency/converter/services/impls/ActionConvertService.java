package br.com.alura.challenges.currency.converter.services.impls;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.alura.challenges.currency.converter.controllers.CurrencyController;
import br.com.alura.challenges.currency.converter.exceptions.NotFoundException;
import br.com.alura.challenges.currency.converter.models.CurrencyHistory;
import br.com.alura.challenges.currency.converter.models.CurrencyTarget;
import br.com.alura.challenges.currency.converter.models.app.enums.MenuState;
import br.com.alura.challenges.currency.converter.services.IActionConvertService;
import br.com.alura.challenges.currency.converter.services.ICurrencyService;
import br.com.alura.challenges.currency.converter.utils.CurrencyFormatUtil;
import br.com.alura.challenges.currency.converter.utils.InteractionUtil;
import br.com.alura.challenges.currency.converter.utils.LocalDateTimeParseUtil;

/**
 * Service implementation for handling currency conversion actions.
 */
public class ActionConvertService implements IActionConvertService {

    private final ICurrencyService service;
    private final Scanner scanner;
    private final int breakLineLength;
    private final InteractionUtil itr = new InteractionUtil();

    /**
     * Constructor for ActionConvertService.
     *
     * @param service the currency service for handling currency operations.
     * @param scanner the scanner for user input.
     * @param breakLineLength the length of the break line used in the display.
     */
    public ActionConvertService(final ICurrencyService service, final Scanner scanner, final int breakLineLength) {
        this.service = service;
        this.scanner = scanner;
        this.breakLineLength = breakLineLength;
    }

    /**
     * Initializes the action based on the provided menu state.
     *
     * @param menuState the current state of the menu.
     */
    @Override
    public void init(final MenuState menuState) {
        if (menuState == MenuState.CONVERT_CURRENCY) {
            itr.breakSection(':', breakLineLength);
            System.out.println("Option selected: Convert currency");
            System.out.println("\t- Currency identification follows ISO 4217.");
            System.out.println("\t- (e.g., USD = United States Dollar).");
            System.out.println();

            try {
                System.out.println("> Enter the currency to be converted and its value.");
                var currency = inCurrency();
                var currencyValue = inCurrencyValue();

                System.out.println("> Now enter the target currency.");
                var currencyTarget = inCurrency();

                var animationThread = createAnimationThread();
                var taskThread = createTaskThread(currency, currencyValue, currencyTarget);

                animationThread.start();
                taskThread.start();

                taskThread.join();
                animationThread.interrupt();
                animationThread.join();

            } catch (InputMismatchException e) {
                System.out.println("Invalid value entered.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads and validates the currency input from the user.
     *
     * @return the validated currency code in uppercase.
     * @throws IllegalArgumentException if the currency code is invalid.
     */
    private String inCurrency() {
        System.out.print("Currency: ");
        itr.clearScanner(scanner);
        var currency = scanner.nextLine();
        if (currency.length() != 3 || !currency.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Invalid currency, must be 3 letters.");
        }
        return currency.toUpperCase();
    }

    /**
     * Reads and validates the currency value input from the user.
     *
     * @return the validated currency value as a BigDecimal.
     * @throws IllegalArgumentException if the currency value is zero or negative.
     */
    private BigDecimal inCurrencyValue() {
        System.out.print("Value: ");
        var currencyValue = BigDecimal.valueOf(scanner.nextDouble());
        if (currencyValue.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Cannot convert zero or negative values.");
        }
        return currencyValue;
    }

    /**
     * Creates a thread to display a conversion animation.
     *
     * @return the created animation thread.
     */
    private Thread createAnimationThread() {
        return new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.print("\rConverting |");
                    Thread.sleep(125);
                    System.out.print("\rConverting /");
                    Thread.sleep(125);
                    System.out.print("\rConverting _");
                    Thread.sleep(125);
                    System.out.print("\rConverting \\");
                    Thread.sleep(125);
                }
            } catch (InterruptedException e) {
                System.out.print("\r");
            }
        });
    }

    /**
     * Creates a thread to perform the currency conversion task.
     *
     * @param currency the currency code to be converted.
     * @param currencyValue the value of the currency to be converted.
     * @param currencyTarget the target currency code.
     * @return the created task thread.
     */
    private Thread createTaskThread(final String currency, final BigDecimal currencyValue, final String currencyTarget) {
        return new Thread(() -> {
            try {
                var currencyFmt = new CurrencyFormatUtil();
                var founded = service.getCurrency(currency);
                var resultConverted = service.convertValueBy(currencyValue, currencyTarget, founded);

                var doneStr = "Done";
                System.out.printf("\r\r%s ", doneStr);
                itr.breakSection('.', breakLineLength - doneStr.length() - 1);

                System.out.printf("Currency used in conversion %s is [%s]\n", currencyTarget, currencyFmt.toFormat(founded.conversionRates().get(currencyTarget), currency));
                System.out.printf("Last currency update on %s\n", LocalDateTimeParseUtil.toComplete(founded.timeLastUpdate()));
                System.out.printf("\nConversion result from [%s] to [%s]\n", currencyFmt.toFormat(currencyValue, currency), currencyFmt.toFormat(resultConverted, currencyTarget));

                CurrencyController.addCurrencyOfHistory(new CurrencyHistory(currency, currencyValue, new CurrencyTarget(currencyTarget, resultConverted), founded.timeLastUpdate()));

            } catch (IllegalArgumentException | NotFoundException e) {
                System.out.println("\r" + e.getMessage());
            }
        });
    }
}