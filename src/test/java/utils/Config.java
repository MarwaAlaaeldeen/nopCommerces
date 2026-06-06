package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {

    static Properties properties;

    public Config() throws IOException {
        properties = new Properties();
        FileInputStream fis =
                new FileInputStream("src/test/resources/config.properties");

        properties.load(fis);
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}