package br.com.alura.challenges.currency.converter.utils;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Utility class for handling user interactions.
 */
public class InteractionUtil {

    /**
     * Prints a section break line composed of a repeated character.
     *
     * @param target the character to repeat.
     * @param count the number of times to repeat the character.
     */
    public void breakSection(final char target, final int count) {
        char[] newCharArr = new char[count];
        Arrays.fill(newCharArr, target);
        System.out.println(new String(newCharArr));
    }

    /**
     * Clears the scanner buffer by reading the next line if available.
     *
     * @param scanner the Scanner object to clear.
     */
    public void clearScanner(final Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}