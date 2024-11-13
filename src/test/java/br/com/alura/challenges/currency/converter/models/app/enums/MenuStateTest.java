package br.com.alura.challenges.currency.converter.models.app.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MenuStateTest {

    @Test
    void valueOfRunningReturnsRunning() {
        assertEquals(MenuState.RUNNING, MenuState.valueOf("RUNNING"));
    }

    @Test
    void valueOfConvertCurrencyReturnsConvertCurrency() {
        assertEquals(MenuState.CONVERT_CURRENCY, MenuState.valueOf("CONVERT_CURRENCY"));
    }

    @Test
    void valueOfConversionsHistoryReturnsConversionsHistory() {
        assertEquals(MenuState.CONVERSIONS_HISTORY, MenuState.valueOf("CONVERSIONS_HISTORY"));
    }

    @Test
    void valueOfLeavingReturnsLeaving() {
        assertEquals(MenuState.LEAVING, MenuState.valueOf("LEAVING"));
    }

    @Test
    void valueOfInvalidStateThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> MenuState.valueOf("INVALID_STATE"));
    }

    @Test
    void valuesReturnsAllMenuStates() {
        MenuState[] expectedStates = {MenuState.RUNNING, MenuState.CONVERT_CURRENCY, MenuState.CONVERSIONS_HISTORY, MenuState.LEAVING};
        assertArrayEquals(expectedStates, MenuState.values());
    }
}