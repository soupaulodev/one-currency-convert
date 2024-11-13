package br.com.alura.challenges.currency.converter.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InteractionUtilTest {

    private InteractionUtil interactionUtil;

    @BeforeEach
    void setUp() {
        interactionUtil = new InteractionUtil();
    }

    @Test
    void testBreakSection() {
        // Capture the output of System.out
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Test the breakSection method
        interactionUtil.breakSection('*', 5);
        assertEquals("*****\n", outContent.toString());

        // Reset System.out
        System.setOut(System.out);
    }

    @Test
    void testClearScanner() {
        // Create a mock Scanner
        Scanner mockScanner = Mockito.mock(Scanner.class);

        // Define the behavior of the mock
        when(mockScanner.hasNextLine()).thenReturn(true);

        // Test the clearScanner method
        interactionUtil.clearScanner(mockScanner);

        // Verify that nextLine() was called
        verify(mockScanner, times(1)).nextLine();
    }
}