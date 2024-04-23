package database_utils.user_result;

public class UserSurveyResult {
    private final String category;
    private final String problem;
    private final String solution;
    private final String comment;

    public UserSurveyResult(String category, String problem, String solution) {
        this.category = category;
        this.problem = problem;
        this.solution = solution;
        this.comment = "none";
    }

    public UserSurveyResult(String category, String problem, String solution, String comment) {
        this.category = category;
        this.problem = problem;
        this.solution = solution;
        this.comment = comment;
    }

    public String getCategory() {
        return category;
    }

    public String getProblem() {
        return problem;
    }

    public String getSolution() {
        return solution;
    }

    public String getComment() {return comment;}
}
