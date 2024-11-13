package br.com.alura.challenges.currency.converter.services.impls;

import br.com.alura.challenges.currency.converter.controllers.CurrencyController;
import br.com.alura.challenges.currency.converter.models.app.enums.MenuState;
import br.com.alura.challenges.currency.converter.services.IActionHistory;
import br.com.alura.challenges.currency.converter.utils.InteractionUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implementation of the IActionHistory interface.
 * Handles the display of currency conversion history.
 */
public class ActionHistory implements IActionHistory {

    private final int breakLineLength;
    private final InteractionUtil itr = new InteractionUtil();

    /**
     * Constructor for ActionHistory.
     *
     * @param breakLineLength the length of the break line used in the display.
     */
    public ActionHistory(final int breakLineLength) {
        this.breakLineLength = breakLineLength;
    }

    /**
     * Initializes the action based on the provided menu state.
     *
     * @param state the current state of the menu.
     */
    @Override
    public void init(MenuState state) {
        if (state == MenuState.CONVERSIONS_HISTORY) {
            itr.breakSection(':', breakLineLength);
            System.out.println("Option selected: Currency conversion history\n");

            var history = CurrencyController.getCurrencyHistory();
            var index = new AtomicInteger(0);

            history.forEach(registry -> {
                System.out.println(registry);
                if (index.getAndIncrement() < history.size() - 1) {
                    itr.breakSection('.', breakLineLength);
                }
            });
        }
    }
}