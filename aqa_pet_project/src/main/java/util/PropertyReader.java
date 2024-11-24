package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static Properties properties = new Properties();

    public static void init() {
        String filename = "config.properties";

        InputStream fis = PropertyReader.class.getClassLoader().getResourceAsStream("config.properties");
        if (fis == null) {
            throw new RuntimeException("Property file '" + filename + "' not found in the classpath.");
        }
        try {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
