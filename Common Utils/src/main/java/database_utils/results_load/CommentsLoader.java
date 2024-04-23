package database_utils.results_load;

import database_utils.properties_holders.ConfigHolder;
import database_utils.properties_holders.SQLQueriesHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommentsLoader {
    private CommentsLoader() {}

    public static List<String> loadAll() {
        return loadComments(
                SQLQueriesHolder.returnRequest("comments.load.all"),
                "");
    }

    public static List<String> loadByCategory(String categoryName) {
        return loadComments(
                SQLQueriesHolder.returnRequest("comments.load.categories"),
                categoryName);
    }

    public static List<String> loadByProblem(String problemName) {
        return loadComments(
                SQLQueriesHolder.returnRequest("comments.load.problems"),
                problemName);
    }

    private static List<String> loadComments(String query, String name) {
        List<String> comments = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                ConfigHolder.returnConfigData("host"),
                ConfigHolder.returnConfigData("user"),
                ConfigHolder.returnConfigData("password")
        );
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(String.format(query, name));

            while (resultSet.next()) {
                if (!Objects.equals(resultSet.getString(1), "none"))
                    comments.add(resultSet.getString(1));
            }

            return comments;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных при загрузке комментариев пользователей");
        }
    }
}
