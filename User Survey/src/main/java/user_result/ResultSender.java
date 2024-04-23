package user_result;

import database_utils.user_result.UserSurveyResult;
import database_utils.user_result.UserSurveyResultDAO;

public class ResultSender {
    ResultSender() {}

    public static void sendResultWithComment(String category, String problem, String solution, String comment) {
        UserSurveyResult userSurveyResult = new UserSurveyResult(category, problem, solution, comment);
        new UserSurveyResultDAO().create(userSurveyResult);
    }

    public static void sendResultWithNoComment(String category, String problem, String solution) {
        UserSurveyResult userSurveyResult = new UserSurveyResult(category, problem, solution);
        new UserSurveyResultDAO().create(userSurveyResult);
    }
}
