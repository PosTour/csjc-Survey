package database_utils.properties_holders;

import java.io.*;
import java.util.Properties;

public class SQLQueriesHolder {
    private static final String FILE_PATH = "Common Utils/src/main/resources/queries.properties";
    private static final Properties queries = loadRequests();

    private SQLQueriesHolder() {}

    private static Properties loadRequests() {
        Properties queries = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(FILE_PATH)) {
            queries.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Потерян путь к списку SQL-запросов");
        }

        return queries;
    }

    public static String returnRequest(String key) {
        return queries.getProperty(key);
    }
}
