package br.com.alura.challenges.currency.converter.configs;

import br.com.alura.challenges.currency.converter.utils.BannerUtil;
import br.com.alura.challenges.currency.converter.utils.PropertiesUtil;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Scanner;

/**
 * Configuration class for the application.
 * Provides utility instances and configurations.
 */
public class ApplicationConfig {

    /**
     * Provides an instance of PropertiesUtil.
     *
     * @return a new PropertiesUtil instance.
     */
    public PropertiesUtil propertiesUtil() {
        return new PropertiesUtil();
    }

    /**
     * Provides the name of the properties file.
     *
     * @return the name of the properties file as a String.
     */
    public String propertiesName() {
        return "application";
    }

    /**
     * Provides a Scanner instance for reading input from the console.
     *
     * @return a new Scanner instance.
     */
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    /**
     * Provides a Gson instance with a specific field naming policy.
     *
     * @return a configured Gson instance.
     */
    public Gson gson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    /**
     * Provides an instance of BannerUtil.
     *
     * @return a new BannerUtil instance.
     */
    public BannerUtil bannerUtil() {
        return new BannerUtil();
    }
}