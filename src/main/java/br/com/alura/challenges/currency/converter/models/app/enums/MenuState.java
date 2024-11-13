package br.com.alura.challenges.currency.converter.models.app.enums;

/**
 * Enum representing the different states of the menu.
 */
public enum MenuState {
    /**
     * State when the menu is running.
     */
    RUNNING,

    /**
     * State for converting currency.
     */
    CONVERT_CURRENCY,

    /**
     * State for viewing the conversion history.
     */
    CONVERSIONS_HISTORY,

    /**
     * State for leaving the menu.
     */
    LEAVING,
}