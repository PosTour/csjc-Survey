package database_utils.results_load;

import options.Option;
import options.OptionLoader;
import database_utils.properties_holders.ConfigHolder;
import database_utils.properties_holders.SQLQueriesHolder;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultsWithCountLoader {
    private ResultsWithCountLoader() {}

    public static Map<Option, Integer> loadCategories() {
        List<Option> categories = OptionLoader.returnCategories();

        return loadResultsWithCount(
                SQLQueriesHolder.returnRequest("categories.load.count"),
                categories);
    }

    public static Map<Option, Integer> loadProblems(int categoryId) {
        List<Option> problems = OptionLoader.returnProblems(categoryId);

        return loadResultsWithCount(
                SQLQueriesHolder.returnRequest("problems.load.count"),
                problems);
    }

    public static Map<Option, Integer> loadSolutions(int problemId) {
        List<Option> solutions = OptionLoader.returnSolutions(problemId);

        return loadResultsWithCount(
                SQLQueriesHolder.returnRequest("solutions.load.count"),
                solutions);
    }

    private static Map<Option, Integer> loadResultsWithCount (String query, List<Option> results) {
        Map<Option, Integer> resultsCount = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(
                ConfigHolder.returnConfigData("host"),
                ConfigHolder.returnConfigData("user"),
                ConfigHolder.returnConfigData("password")
        );
             Statement statement = connection.createStatement()) {

            for (Option option : results) {
                ResultSet count = statement.executeQuery(String.format(query, option.name()));

                if (count.next()) {
                    resultsCount.put(option, count.getInt(1));
                } else {
                    resultsCount.put(option, 0);
                }
            }

            return resultsCount;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных при загрузке ответов пользователей");
        }
    }
}
