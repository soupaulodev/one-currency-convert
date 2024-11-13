package br.com.alura.challenges.currency.converter.services.impls;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.challenges.currency.converter.models.Currency;
import br.com.alura.challenges.currency.converter.models.app.enums.MenuState;
import br.com.alura.challenges.currency.converter.services.ICurrencyService;

class ActionConvertServiceTest {

    private ICurrencyService currencyService;
    private Scanner scanner;
    private ActionConvertService actionConvertService;
    private final int breakLineLength = 50;

    @BeforeEach
    void setUp() {
        currencyService = mock(ICurrencyService.class);
        scanner = mock(Scanner.class);
        actionConvertService = new ActionConvertService(currencyService, scanner, breakLineLength);
    }

    @Test
    void testInitWithConvertCurrency() {
        when(scanner.nextLine()).thenReturn("USD", "EUR");
        when(scanner.nextDouble()).thenReturn(100.0);

        Currency currency = mock(Currency.class);
        when(currencyService.getCurrency("USD")).thenReturn(currency);
        when(currency.conversionRates()).thenReturn(Map.of("EUR", BigDecimal.valueOf(0.85)));
        when(currency.timeLastUpdate()).thenReturn(LocalDateTime.parse("2023-10-01T10:00:00"));

        when(currencyService.convertValueBy(BigDecimal.valueOf(100.0), "EUR", currency)).thenReturn(BigDecimal.valueOf(85.0));

        actionConvertService.init(MenuState.CONVERT_CURRENCY);

        verify(currencyService).getCurrency("USD");
        verify(currencyService).convertValueBy(BigDecimal.valueOf(100.0), "EUR", currency);
    }

    @Test
    void testInCurrencyValid() {
        when(scanner.nextLine()).thenReturn("USD");
        String currency;
        try {
            var method = ActionConvertService.class.getDeclaredMethod("inCurrency");
            method.setAccessible(true);
            currency = (String) method.invoke(actionConvertService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals("USD", currency);
    }

    @Test
    void testInCurrencyValueValid() {
        when(scanner.nextDouble()).thenReturn(100.0);
        BigDecimal value;
        try {
            var method = ActionConvertService.class.getDeclaredMethod("inCurrencyValue");
            method.setAccessible(true);
            value = (BigDecimal) method.invoke(actionConvertService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(BigDecimal.valueOf(100.0), value);
    }
}