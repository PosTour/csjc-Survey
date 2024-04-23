package database_utils.properties_holders;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigHolder {
    private static final String FILE_PATH = "Common Utils/src/main/resources/config.properties";
    private static final Properties config = loadConfig();

    private ConfigHolder() {}

    private static Properties loadConfig() {
        Properties config = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(FILE_PATH)) {
            config.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Потерян путь к данным для подключения к базе данных");
        }

        return config;
    }

    public static String returnConfigData(String key) {
        return config.getProperty(key);
    }
}
