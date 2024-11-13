package br.com.alura.challenges.currency.converter.services;

import br.com.alura.challenges.currency.converter.models.app.enums.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class IActionHistoryTest {

    private IActionHistory actionHistory;

    @BeforeEach
    void setUp() {
        actionHistory = mock(IActionHistory.class);
    }

    @Test
    void initWithRunningMenuState() {
        MenuState menuState = MenuState.RUNNING;
        actionHistory.init(menuState);
        verify(actionHistory, times(1)).init(menuState);
    }

    @Test
    void initWithConvertCurrencyMenuState() {
        MenuState menuState = MenuState.CONVERT_CURRENCY;
        actionHistory.init(menuState);
        verify(actionHistory, times(1)).init(menuState);
    }

    @Test
    void initWithConversionsHistoryMenuState() {
        MenuState menuState = MenuState.CONVERSIONS_HISTORY;
        actionHistory.init(menuState);
        verify(actionHistory, times(1)).init(menuState);
    }

    @Test
    void initWithLeavingMenuState() {
        MenuState menuState = MenuState.LEAVING;
        actionHistory.init(menuState);
        verify(actionHistory, times(1)).init(menuState);
    }

    @Test
    void initWithNullMenuState() {
        MenuState menuState = null;
        actionHistory.init(menuState);
        verify(actionHistory, times(1)).init(menuState);
    }
}