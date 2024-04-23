package navigation;

import options.Option;
import options.OptionParamsReceiver;
import user_result.ResultSender;

import java.util.Scanner;

public class Navigator {
    private Navigator() {}

    public static void displayMenu() {
        System.out.println("Спасибо, что согласились принять участие в опросе.");

        Option category = OptionParamsReceiver.receiveCategory();
        Option problem = OptionParamsReceiver.receiveProblem(category.id());
        Option solution = OptionParamsReceiver.receiveSolution(problem.id());

        System.out.println("""
                Если предложенные варианты не отразили в полной мере вашу позицию, вы можете оставить открытый комментарий.
                Желаете ли вы сделать это?
                1. Да
                2. Нет""");

        Scanner scanner = new Scanner(System.in);

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

        System.out.println("Спасибо за принятие участия в опросе! Мы обязательно учтем ваше мнение.");
    }
}
