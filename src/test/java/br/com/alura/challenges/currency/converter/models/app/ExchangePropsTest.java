package br.com.alura.challenges.currency.converter.models.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExchangePropsTest {

    @Test
    void constructorSetsAllFields() {
        ExchangeProps exchangeProps = new ExchangeProps("http://example.com", "key123", "v1");
        assertEquals("http://example.com", exchangeProps.exchangeUri());
        assertEquals("key123", exchangeProps.exchangeKey());
        assertEquals("v1", exchangeProps.exchangeVersion());
    }

    @Test
    void constructorWithNullUri() {
        ExchangeProps exchangeProps = new ExchangeProps(null, "key123", "v1");
        assertNull(exchangeProps.exchangeUri());
        assertEquals("key123", exchangeProps.exchangeKey());
        assertEquals("v1", exchangeProps.exchangeVersion());
    }

    @Test
    void constructorWithEmptyUri() {
        ExchangeProps exchangeProps = new ExchangeProps("", "key123", "v1");
        assertEquals("", exchangeProps.exchangeUri());
        assertEquals("key123", exchangeProps.exchangeKey());
        assertEquals("v1", exchangeProps.exchangeVersion());
    }

    @Test
    void constructorWithNullKey() {
        ExchangeProps exchangeProps = new ExchangeProps("http://example.com", null, "v1");
        assertEquals("http://example.com", exchangeProps.exchangeUri());
        assertNull(exchangeProps.exchangeKey());
        assertEquals("v1", exchangeProps.exchangeVersion());
    }

    @Test
    void constructorWithEmptyKey() {
        ExchangeProps exchangeProps = new ExchangeProps("http://example.com", "", "v1");
        assertEquals("http://example.com", exchangeProps.exchangeUri());
        assertEquals("", exchangeProps.exchangeKey());
        assertEquals("v1", exchangeProps.exchangeVersion());
    }

    @Test
    void constructorWithNullVersion() {
        ExchangeProps exchangeProps = new ExchangeProps("http://example.com", "key123", null);
        assertEquals("http://example.com", exchangeProps.exchangeUri());
        assertEquals("key123", exchangeProps.exchangeKey());
        assertNull(exchangeProps.exchangeVersion());
    }

    @Test
    void constructorWithEmptyVersion() {
        ExchangeProps exchangeProps = new ExchangeProps("http://example.com", "key123", "");
        assertEquals("http://example.com", exchangeProps.exchangeUri());
        assertEquals("key123", exchangeProps.exchangeKey());
        assertEquals("", exchangeProps.exchangeVersion());
    }
}