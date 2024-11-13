package br.com.alura.challenges.currency.converter.utils;

import br.com.alura.challenges.currency.converter.models.app.BannerProps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Utility class for handling banner operations.
 */
public class BannerUtil {

    private static final String BANNER_ARCHIVE = "banner.txt";
    private static final String BANNER_STD = "Alura One Challenge - Currency Converter";

    /**
     * Loads the banner content from a file or uses a default banner if the file is not found.
     *
     * @param bannerProps the properties for the banner.
     * @return the banner content as a string.
     */
    public String load(BannerProps bannerProps) {
        StringBuilder builder = new StringBuilder();

        try (var inputStream = BannerUtil.class.getClassLoader().getResourceAsStream(BANNER_ARCHIVE);
             var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            if (inputStream != null) {
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append('\n');
                }
            } else {
                builder.append(BANNER_STD);
            }
        } catch (IOException | NullPointerException e) {
            builder.append(BANNER_STD);
        }

        if (bannerProps != null) {
            builder.append(bannerProps.title()).append(' ')
                   .append(bannerProps.version()).append('\n')
                   .append(bannerProps.since()).append('\n');
        }
        return builder.toString();
    }
}