package br.com.alura.challenges.currency.converter.services;

import br.com.alura.challenges.currency.converter.models.app.enums.MenuState;

/**
 * Interface for handling currency conversion actions.
 */
public interface IActionConvertService {

    /**
     * Initializes the action based on the provided menu state.
     *
     * @param menuState the current state of the menu.
     */
    void init(MenuState menuState);
}