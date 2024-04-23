package display;

import java.util.List;

public class CommentsDisplayer {
    private CommentsDisplayer() {}

    public static void displayComments(List<String> comments) {
        if (!comments.isEmpty()) {
            comments.forEach(System.out :: println);
        } else {
            System.out.println("Пока что нет комментариев, связанных с этой категорией");
        }
    }
}
