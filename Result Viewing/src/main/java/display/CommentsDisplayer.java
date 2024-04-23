package display;

import java.util.List;

public class CommentsDisplayer {
    private CommentsDisplayer() {}

    public static void displayComments(List<String> comments) {
        comments.forEach(System.out :: println);
    }
}
