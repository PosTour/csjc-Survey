package options;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OptionParamsReceiver {
    private static final Scanner scanner = new Scanner(System.in);

    private OptionParamsReceiver() {}

    public static Option receiveCategory() {
        List<Option> categories = OptionLoader.returnCategories();
        System.out.println("Выберите интересующую вас категорию:\n");

        return returnOption(categories);
    }

    public static Option receiveProblem(int categoryId) {
        List<Option> problems = OptionLoader.returnProblems(categoryId);
        System.out.println("Выберите интересующую вас проблему:\n");

        return returnOption(problems);
    }

    public static Option receiveSolution(int problemId) {
        List<Option> solutions = OptionLoader.returnSolutions(problemId);
        System.out.println("Выберите пути решения данной проблемы, которые считаете верными:\n");

        return returnOption(solutions);
    }

    private static Option returnOption(List<Option> options) {
        printOptions(options);

        try {
            int id = scanner.nextInt();

            if (id < 0 || id > options.size()) {
                throw new InputMismatchException();
            }

            int realId = options.get(id - 1).id();

            return findOption(realId, options);
        } catch (java.util.InputMismatchException e) {
            System.out.println("Введите корректное значение:\n");
            scanner.next();
            return returnOption(options);
        }
    }

    private static Option findOption(int id, List<Option> options) {
        for (Option option : options) {
            if (id == option.id()) {
                return option;
            }
        }

        return null;
    }

    private static void printOptions(List<Option> options) {
        int i = 1;
        for (Option option : options) {
            System.out.printf("%d. %s%n", i, option.name());
            i++;
        }
    }
}
