package br.com.alura.challenges.currency.converter.controllers;

import br.com.alura.challenges.currency.converter.models.CurrencyHistory;
import br.com.alura.challenges.currency.converter.models.CurrencyTarget;
import br.com.alura.challenges.currency.converter.models.app.BannerProps;
import br.com.alura.challenges.currency.converter.models.app.enums.MenuState;
import br.com.alura.challenges.currency.converter.services.ICurrencyService;
import br.com.alura.challenges.currency.converter.utils.BannerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurrencyControllerTest {

    private Scanner scanner;
    private CurrencyController controller;

    @BeforeEach
    void setUp() {
        scanner = mock(Scanner.class);
        BannerUtil bannerUtil = mock(BannerUtil.class);
        BannerProps bannerProps = mock(BannerProps.class);
        ICurrencyService currencyService = mock(ICurrencyService.class);
        controller = new CurrencyController(scanner, bannerUtil, bannerProps, currencyService);
    }

    @Test
    void addCurrencyOfHistoryAddsRecord() {
        CurrencyHistory history = new CurrencyHistory("USD", new BigDecimal("1.0"), new CurrencyTarget("BRL", new BigDecimal("5.0")), LocalDateTime.now());
        CurrencyController.addCurrencyOfHistory(history);
        assertTrue(CurrencyController.getCurrencyHistory().contains(history));
    }

    @Test
    void getCurrencyHistoryReturnsNonEmptySet() {
        CurrencyHistory history = new CurrencyHistory("USD", new BigDecimal("1.0"), new CurrencyTarget("BRL", new BigDecimal("5.0")), LocalDateTime.now());
        CurrencyController.addCurrencyOfHistory(history);
        assertFalse(CurrencyController.getCurrencyHistory().isEmpty());
    }

    @Test
    void chooseReturnsConvertCurrencyState() throws Exception {
        when(scanner.nextInt()).thenReturn(1);
        Method chooseMethod = CurrencyController.class.getDeclaredMethod("choose");
        chooseMethod.setAccessible(true);
        assertEquals(MenuState.CONVERT_CURRENCY, chooseMethod.invoke(controller));
    }

    @Test
    void chooseReturnsConversionsHistoryState() throws Exception {
        when(scanner.nextInt()).thenReturn(2);
        Method chooseMethod = CurrencyController.class.getDeclaredMethod("choose");
        chooseMethod.setAccessible(true);
        assertEquals(MenuState.CONVERSIONS_HISTORY, chooseMethod.invoke(controller));
    }

    @Test
    void chooseReturnsLeavingState() throws Exception {
        when(scanner.nextInt()).thenReturn(3);
        Method chooseMethod = CurrencyController.class.getDeclaredMethod("choose");
        chooseMethod.setAccessible(true);
        assertEquals(MenuState.LEAVING, chooseMethod.invoke(controller));
    }

    @Test
    void chooseHandlesInvalidOption() throws Exception {
        when(scanner.nextInt()).thenReturn(4);
        Method chooseMethod = CurrencyController.class.getDeclaredMethod("choose");
        chooseMethod.setAccessible(true);
        assertEquals(MenuState.RUNNING, chooseMethod.invoke(controller));
    }

    @Test
    void chooseHandlesInputMismatchException() throws Exception {
        when(scanner.nextInt()).thenThrow(InputMismatchException.class);
        Method chooseMethod = CurrencyController.class.getDeclaredMethod("choose");
        chooseMethod.setAccessible(true);
        assertEquals(MenuState.RUNNING, chooseMethod.invoke(controller));
    }
}