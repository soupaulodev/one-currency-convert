package br.com.alura.challenges.currency.converter.services;

import br.com.alura.challenges.currency.converter.models.app.enums.MenuState;

/**
 * Interface for handling action history in the currency converter application.
 */
public interface IActionHistory {

    /**
     * Initializes the action history based on the provided menu state.
     *
     * @param state the current state of the menu.
     */
    void init(MenuState state);
}