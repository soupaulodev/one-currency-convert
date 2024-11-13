package br.com.alura.challenges.currency.converter.models;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {

    @Test
    void constructorSetsAllFields() {
        LocalDateTime now = LocalDateTime.now();
        Currency currency = new Currency("USD", Map.of("EUR", BigDecimal.valueOf(0.85)), now);
        assertEquals("USD", currency.baseCode());
        assertEquals(BigDecimal.valueOf(0.85), currency.conversionRates().get("EUR"));
        assertEquals(now, currency.timeLastUpdate());
    }

    @Test
    void constructorWithNullBaseCode() {
        LocalDateTime now = LocalDateTime.now();
        Currency currency = new Currency(null, Map.of("EUR", BigDecimal.valueOf(0.85)), now);
        assertNull(currency.baseCode());
        assertEquals(BigDecimal.valueOf(0.85), currency.conversionRates().get("EUR"));
        assertEquals(now, currency.timeLastUpdate());
    }

    @Test
    void constructorWithEmptyBaseCode() {
        LocalDateTime now = LocalDateTime.now();
        Currency currency = new Currency("", Map.of("EUR", BigDecimal.valueOf(0.85)), now);
        assertEquals("", currency.baseCode());
        assertEquals(BigDecimal.valueOf(0.85), currency.conversionRates().get("EUR"));
        assertEquals(now, currency.timeLastUpdate());
    }

    @Test
    void constructorWithNullConversionRates() {
        LocalDateTime now = LocalDateTime.now();
        Currency currency = new Currency("USD", null, now);
        assertEquals("USD", currency.baseCode());
        assertNull(currency.conversionRates());
        assertEquals(now, currency.timeLastUpdate());
    }

    @Test
    void constructorWithEmptyConversionRates() {
        LocalDateTime now = LocalDateTime.now();
        Currency currency = new Currency("USD", Map.of(), now);
        assertEquals("USD", currency.baseCode());
        assertTrue(currency.conversionRates().isEmpty());
        assertEquals(now, currency.timeLastUpdate());
    }

    @Test
    void constructorWithNullTimeLastUpdate() {
        Currency currency = new Currency("USD", Map.of("EUR", BigDecimal.valueOf(0.85)), null);
        assertEquals("USD", currency.baseCode());
        assertEquals(BigDecimal.valueOf(0.85), currency.conversionRates().get("EUR"));
        assertNull(currency.timeLastUpdate());
    }
}