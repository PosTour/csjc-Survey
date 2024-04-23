package navigation;

import options.Option;
import options.OptionParamsReceiver;
import user_result.ResultSender;

import java.util.Scanner;

public class Navigator {
    private Navigator() {}

    public static void displayMainMenu() {
        System.out.println("Спасибо, что согласились принять участие в опросе.");

        Option category = OptionParamsReceiver.receiveCategory();
        Option problem = OptionParamsReceiver.receiveProblem(category.id());
        Option solution = OptionParamsReceiver.receiveSolution(problem.id());

        displayCommentMenu(category, problem, solution);

        System.out.println("Спасибо за принятие участия в опросе! Мы обязательно учтем ваше мнение.");
    }

    private static void displayCommentMenu(Option category, Option problem, Option solution) {
        System.out.println("""
                Если предложенные варианты не отразили в полной мере вашу позицию, вы можете оставить открытый комментарий.
                Желаете ли вы сделать это?
                1. Да
                2. Нет""");

        Scanner scanner = new Scanner(System.in);

        try {
            switch (scanner.nextInt()) {
                case 1 -> {
                    System.out.println("Впишите его ниже:");
                    scanner.nextLine();
                    String comment = scanner.nextLine();
                    ResultSender.sendResultWithComment(
                            category.name(),
                            problem.name(),
                            solution.name(),
                            comment);
                }
                case 2 -> ResultSender.sendResultWithNoComment(
                        category.name(),
                        problem.name(),
                        solution.name());
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Введите корректное значение:\n");
            scanner.next();
            displayCommentMenu(category, problem, solution);
        }
    }
}
