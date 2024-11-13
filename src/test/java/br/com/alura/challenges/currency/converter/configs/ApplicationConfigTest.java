package br.com.alura.challenges.currency.converter.configs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApplicationConfigTest {

    @Test
    void propertiesUtilReturnsNonNullInstance() {
        ApplicationConfig config = new ApplicationConfig();
        assertNotNull(config.propertiesUtil());
    }

    @Test
    void propertiesNameReturnsCorrectName() {
        ApplicationConfig config = new ApplicationConfig();
        assertEquals("application", config.propertiesName());
    }

    @Test
    void scannerReturnsNonNullInstance() {
        ApplicationConfig config = new ApplicationConfig();
        assertNotNull(config.scanner());
    }

    @Test
    void gsonReturnsNonNullInstance() {
        ApplicationConfig config = new ApplicationConfig();
        assertNotNull(config.gson());
    }

    @Test
    void bannerUtilReturnsNonNullInstance() {
        ApplicationConfig config = new ApplicationConfig();
        assertNotNull(config.bannerUtil());
    }
}