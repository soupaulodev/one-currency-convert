package br.com.alura.challenges.currency.converter.models;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class CurrencyTargetTest {

    @Test
    void constructorSetsAllFields() {
        CurrencyTarget target = new CurrencyTarget("EUR", BigDecimal.valueOf(0.85));
        assertEquals("EUR", target.currency());
        assertEquals(BigDecimal.valueOf(0.85), target.value());
    }

    @Test
    void constructorWithNullCurrency() {
        CurrencyTarget target = new CurrencyTarget(null, BigDecimal.valueOf(0.85));
        assertNull(target.currency());
        assertEquals(BigDecimal.valueOf(0.85), target.value());
    }

    @Test
    void constructorWithNullValue() {
        CurrencyTarget target = new CurrencyTarget("EUR", null);
        assertEquals("EUR", target.currency());
        assertNull(target.value());
    }

    @Test
    void constructorWithNullCurrencyAndValue() {
        CurrencyTarget target = new CurrencyTarget(null, null);
        assertNull(target.currency());
        assertNull(target.value());
    }
}