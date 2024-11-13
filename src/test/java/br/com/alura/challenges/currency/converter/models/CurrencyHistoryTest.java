package br.com.alura.challenges.currency.converter.models;

import br.com.alura.challenges.currency.converter.utils.CurrencyFormatUtil;
import br.com.alura.challenges.currency.converter.utils.LocalDateTimeParseUtil;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class CurrencyHistoryTest {

    @Test
    void constructorSetsAllFields() {
        LocalDateTime now = LocalDateTime.now();
        CurrencyTarget target = new CurrencyTarget("EUR", BigDecimal.valueOf(0.85));
        CurrencyHistory history = new CurrencyHistory("USD", BigDecimal.valueOf(100), target, now);
        assertEquals(target, history.getTarget());
        assertEquals(now, history.getTimeLastUpdate());
    }

    @Test
    void constructorWithNullCurrency() {
        LocalDateTime now = LocalDateTime.now();
        CurrencyTarget target = new CurrencyTarget("EUR", BigDecimal.valueOf(0.85));
        CurrencyHistory history = new CurrencyHistory(null, BigDecimal.valueOf(100), target, now);
        assertNull(history.getCurrency());
        assertEquals(BigDecimal.valueOf(100), history.getValue());
        assertEquals(target, history.getTarget());
        assertEquals(now, history.getTimeLastUpdate());
    }

    @Test
    void constructorWithNullValue() {
        LocalDateTime now = LocalDateTime.now();
        CurrencyTarget target = new CurrencyTarget("EUR", BigDecimal.valueOf(0.85));
        CurrencyHistory history = new CurrencyHistory("USD", null, target, now);
        assertEquals("USD", history.getCurrency());
        assertNull(history.getValue());
        assertEquals(target, history.getTarget());
        assertEquals(now, history.getTimeLastUpdate());
    }

    @Test
    void constructorWithNullTarget() {
        LocalDateTime now = LocalDateTime.now();
        CurrencyHistory history = new CurrencyHistory("USD", BigDecimal.valueOf(100), null, now);
        assertEquals("USD", history.getCurrency());
        assertEquals(BigDecimal.valueOf(100), history.getValue());
        assertNull(history.getTarget());
        assertEquals(now, history.getTimeLastUpdate());
    }

    @Test
    void constructorWithNullTimeLastUpdate() {
        CurrencyTarget target = new CurrencyTarget("EUR", BigDecimal.valueOf(0.85));
        CurrencyHistory history = new CurrencyHistory("USD", BigDecimal.valueOf(100), target, null);
        assertEquals("USD", history.getCurrency());
        assertEquals(BigDecimal.valueOf(100), history.getValue());
        assertEquals(target, history.getTarget());
        assertNull(history.getTimeLastUpdate());
    }

    @Test
    void setRegisteredInSetsField() {
        LocalDateTime now = LocalDateTime.now();
        CurrencyHistory history = new CurrencyHistory("USD", BigDecimal.valueOf(100), new CurrencyTarget("EUR", BigDecimal.valueOf(0.85)), now);
        history.setRegisteredIn(now);
        assertEquals(now, history.getRegisteredIn());
    }

    @Test
    void toStringReturnsFormattedString() {
        LocalDateTime now = LocalDateTime.now();
        CurrencyHistory history = new CurrencyHistory("USD", BigDecimal.valueOf(100), new CurrencyTarget("EUR", BigDecimal.valueOf(0.85)), now);
        history.setRegisteredIn(now);
        String expected = """
                Registrado em %s
                [Moeda %s | Conversão %s]
                Ultima atualização da cotação [%s]"""
                .formatted(
                        LocalDateTimeParseUtil.toComplete(now),
                        new CurrencyFormatUtil().toFormat(BigDecimal.valueOf(100), "USD"),
                        new CurrencyFormatUtil().toFormat(BigDecimal.valueOf(0.85), "EUR"),
                        LocalDateTimeParseUtil.toComplete(now)
                );
        assertEquals(expected, history.toString());
    }
}