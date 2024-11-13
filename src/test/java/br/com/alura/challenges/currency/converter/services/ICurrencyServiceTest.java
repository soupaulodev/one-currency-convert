package br.com.alura.challenges.currency.converter.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import br.com.alura.challenges.currency.converter.models.Currency;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ICurrencyServiceTest {

    private ICurrencyService currencyService;
    private Currency mockCurrency;

    @BeforeEach
    public void setUp() {
        currencyService = Mockito.mock(ICurrencyService.class);
        mockCurrency = Mockito.mock(Currency.class);
    }

    @Test
    void testGetCurrency() {
        String currencyCode = "USD";
        LocalDateTime now = LocalDateTime.now();
        Currency expectedCurrency = new Currency("USD", Map.of("EUR", BigDecimal.valueOf(0.85)), now);
        when(currencyService.getCurrency(currencyCode)).thenReturn(expectedCurrency);

        Currency actualCurrency = currencyService.getCurrency(currencyCode);

        assertEquals(expectedCurrency, actualCurrency);
        verify(currencyService, times(1)).getCurrency(currencyCode);
    }

    @Test
    void testConvertValueBy() {
        BigDecimal value = new BigDecimal("100.00");
        String targetExchange = "EUR";
        BigDecimal expectedConvertedValue = new BigDecimal("85.00");
        when(currencyService.convertValueBy(value, targetExchange, mockCurrency)).thenReturn(expectedConvertedValue);

        BigDecimal actualConvertedValue = currencyService.convertValueBy(value, targetExchange, mockCurrency);

        assertEquals(expectedConvertedValue, actualConvertedValue);
        verify(currencyService, times(1)).convertValueBy(value, targetExchange, mockCurrency);
    }
}