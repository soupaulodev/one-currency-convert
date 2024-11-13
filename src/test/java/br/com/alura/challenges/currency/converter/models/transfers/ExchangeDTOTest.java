package br.com.alura.challenges.currency.converter.models.transfers;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ExchangeDTOTest {

    @Test
    void isNonNullReturnsTrueWhenBaseCodeIsNonNull() {
        ExchangeDTO dto = new ExchangeDTO("USD", null, null);
        assertTrue(dto.isNonNull());
    }

    @Test
    void isNonNullReturnsTrueWhenConversionRatesIsNonNull() {
        ExchangeDTO dto = new ExchangeDTO(null, Map.of("EUR", 0.85), null);
        assertTrue(dto.isNonNull());
    }

    @Test
    void isNonNullReturnsTrueWhenTimeLastUpdateUtcIsNonNull() {
        ExchangeDTO dto = new ExchangeDTO(null, null, "2023-01-01T00:00:00Z");
        assertTrue(dto.isNonNull());
    }

    @Test
    void isNonNullReturnsFalseWhenAllFieldsAreNull() {
        ExchangeDTO dto = new ExchangeDTO(null, null, null);
        assertFalse(dto.isNonNull());
    }

    @Test
    void isNonNullReturnsTrueWhenAllFieldsAreNonNull() {
        ExchangeDTO dto = new ExchangeDTO("USD", Map.of("EUR", 0.85), "2023-01-01T00:00:00Z");
        assertTrue(dto.isNonNull());
    }
}