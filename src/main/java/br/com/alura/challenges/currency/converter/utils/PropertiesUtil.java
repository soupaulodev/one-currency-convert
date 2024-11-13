package br.com.alura.challenges.currency.converter.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * Utility class for loading properties from JSON configuration files.
 */
public class PropertiesUtil {

    /**
     * Loads a JSON configuration file and converts it to an instance of the specified class.
     *
     * @param <T> the type of the class modeled by the class object.
     * @param fileName the name of the JSON file (without the .json extension).
     * @param clazz the class of T.
     * @return an instance of T populated with the data from the JSON file, or null if an error occurs.
     */
    public <T> T load(final String fileName, final Class<T> clazz) {
        try {
            var gson = new Gson();
            var path = Objects.requireNonNull(getClass()
                    .getClassLoader()
                    .getResource(fileName + ".json"))
                    .getPath();
            try (var reader = new JsonReader(new FileReader(path))) {
                return gson.fromJson(reader, clazz);
            } catch (IOException e) {
                System.out.println("ERROR: Unable to read the configuration file.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: Unable to perform the configuration operation.");
        }

        return null;
    }
}