package br.com.alura.challenges.currency.converter.models.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BannerPropsTest {

    @Test
    void constructorSetsAllFields() {
        BannerProps bannerProps = new BannerProps("Title", "2023-01-01", "1.0");
        assertEquals("Title", bannerProps.title());
        assertEquals("2023-01-01", bannerProps.since());
        assertEquals("1.0", bannerProps.version());
    }

    @Test
    void constructorWithNullTitle() {
        BannerProps bannerProps = new BannerProps(null, "2023-01-01", "1.0");
        assertNull(bannerProps.title());
        assertEquals("2023-01-01", bannerProps.since());
        assertEquals("1.0", bannerProps.version());
    }

    @Test
    void constructorWithEmptyTitle() {
        BannerProps bannerProps = new BannerProps("", "2023-01-01", "1.0");
        assertEquals("", bannerProps.title());
        assertEquals("2023-01-01", bannerProps.since());
        assertEquals("1.0", bannerProps.version());
    }

    @Test
    void constructorWithNullSince() {
        BannerProps bannerProps = new BannerProps("Title", null, "1.0");
        assertEquals("Title", bannerProps.title());
        assertNull(bannerProps.since());
        assertEquals("1.0", bannerProps.version());
    }

    @Test
    void constructorWithEmptySince() {
        BannerProps bannerProps = new BannerProps("Title", "", "1.0");
        assertEquals("Title", bannerProps.title());
        assertEquals("", bannerProps.since());
        assertEquals("1.0", bannerProps.version());
    }

    @Test
    void constructorWithNullVersion() {
        BannerProps bannerProps = new BannerProps("Title", "2023-01-01", null);
        assertEquals("Title", bannerProps.title());
        assertEquals("2023-01-01", bannerProps.since());
        assertNull(bannerProps.version());
    }

    @Test
    void constructorWithEmptyVersion() {
        BannerProps bannerProps = new BannerProps("Title", "2023-01-01", "");
        assertEquals("Title", bannerProps.title());
        assertEquals("2023-01-01", bannerProps.since());
        assertEquals("", bannerProps.version());
    }
}