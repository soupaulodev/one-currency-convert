package br.com.alura.challenges.currency.converter.services;

import br.com.alura.challenges.currency.converter.models.app.enums.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class IActionConvertServiceTest {

    private IActionConvertService actionConvertService;

    @BeforeEach
    void setUp() {
        actionConvertService = mock(IActionConvertService.class);
    }

    @Test
    void initWithRunningMenuState() {
        MenuState menuState = MenuState.RUNNING;
        actionConvertService.init(menuState);
        verify(actionConvertService, times(1)).init(menuState);
    }

    @Test
    void initWithConvertCurrencyMenuState() {
        MenuState menuState = MenuState.CONVERT_CURRENCY;
        actionConvertService.init(menuState);
        verify(actionConvertService, times(1)).init(menuState);
    }

    @Test
    void initWithConversionsHistoryMenuState() {
        MenuState menuState = MenuState.CONVERSIONS_HISTORY;
        actionConvertService.init(menuState);
        verify(actionConvertService, times(1)).init(menuState);
    }

    @Test
    void initWithLeavingMenuState() {
        MenuState menuState = MenuState.LEAVING;
        actionConvertService.init(menuState);
        verify(actionConvertService, times(1)).init(menuState);
    }
}