package br.com.alura.challenges.currency.converter.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {

    @Test
    void constructorSetsMessage() {
        String message = "Resource not found";
        NotFoundException exception = new NotFoundException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void constructorWithNullMessage() {
        NotFoundException exception = new NotFoundException(null);
        assertNull(exception.getMessage());
    }

    @Test
    void constructorWithEmptyMessage() {
        NotFoundException exception = new NotFoundException("");
        assertEquals("", exception.getMessage());
    }
}