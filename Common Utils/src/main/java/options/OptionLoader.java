package options;

import database_utils.properties_holders.ConfigHolder;
import database_utils.properties_holders.SQLQueriesHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionLoader {
    private OptionLoader() {}

    public static List<Option> returnCategories() {
        return returnResult(SQLQueriesHolder.returnRequest("categories.load"));
    }

    public static List<Option> returnProblems(int categoryId) {
        return returnResult(
                SQLQueriesHolder.returnRequest("problems.load"),
                categoryId);
    }

    public static List<Option> returnSolutions(int problemId) {
        return returnResult(
                SQLQueriesHolder.returnRequest("solutions.load"),
                problemId);
    }

    private static List<Option> returnResult(String query) {
        List<Option> results = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                ConfigHolder.returnConfigData("host"),
                ConfigHolder.returnConfigData("user"),
                ConfigHolder.returnConfigData("password")
        );
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                results.add(new Option(
                        resultSet.getInt(1),
                        resultSet.getString(2)));
            }

            return results;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных при загрузке данных для опроса");
        }
    }

    private static List<Option> returnResult(String query, int id) {
        List<Option> results = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                ConfigHolder.returnConfigData("host"),
                ConfigHolder.returnConfigData("user"),
                ConfigHolder.returnConfigData("password")
        );
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(String.format(query, id));

            while (resultSet.next()) {
                results.add(new Option(
                        resultSet.getInt(1),
                        resultSet.getString(2)));
            }

            return results;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных при загрузке данных для опроса");
        }
    }
}
