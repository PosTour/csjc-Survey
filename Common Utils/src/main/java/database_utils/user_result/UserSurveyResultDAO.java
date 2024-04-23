package database_utils.user_result;

import database_utils.properties_holders.ConfigHolder;
import database_utils.properties_holders.SQLQueriesHolder;

import java.sql.*;

public class UserSurveyResultDAO {
    public void create(UserSurveyResult surveyResult) {
        try (Connection connection = DriverManager.getConnection(
                ConfigHolder.returnConfigData("host"),
                ConfigHolder.returnConfigData("user"),
                ConfigHolder.returnConfigData("password")
        );
            Statement statement = connection.createStatement()) {

            statement.executeUpdate(String.format(
                    SQLQueriesHolder.returnRequest("result.upload"),
                    surveyResult.getCategory(),
                    surveyResult.getProblem(),
                    surveyResult.getSolution(),
                    surveyResult.getComment()));

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных при отправке ответов пользователя");
        }
    }
}
