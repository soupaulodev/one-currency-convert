package br.com.alura.challenges.currency.converter.models.app;

/**
 * Record representing banner properties.
 *
 * @param title the title of the banner.
 * @param since the date since the banner is active.
 * @param version the version of the banner.
 */
public record BannerProps(String title, String since, String version) {
}