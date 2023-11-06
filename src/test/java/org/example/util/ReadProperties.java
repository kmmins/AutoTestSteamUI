package org.example.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    private static Properties PROPERTIES;

    public static String get(String key) throws IOException {
        try (FileReader fileReader = new FileReader("src/test/resources/test.properties")) {
            PROPERTIES = new Properties();
            PROPERTIES.load(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return PROPERTIES.getProperty(key);
    }
}
