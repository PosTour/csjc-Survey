package navigation;

import database_utils.results_load.CommentsLoader;
import database_utils.results_load.ResultsWithCountLoader;
import display.CommentsDisplayer;
import display.StatisticsDisplayer;
import options.Option;
import options.OptionParamsReceiver;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Navigator {
    private static final Scanner scanner = new Scanner(System.in);

    private Navigator() {}

    public static void displayMainMenu() {
        System.out.print("""
                Что вас интересует?
                
                1. Получить статистику итогов опроса.
                2. Получить комментарии пользователей.
                """);

        try {
            switch (scanner.nextInt()) {
                case 1 -> displayStatsMenu();
                case 2 -> displayCommentsMenu();
                default -> {
                    System.out.println("Введите корректное значение");
                    displayMainMenu();
                }
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Введите корректное значение:\n");
            scanner.next();
            displayMainMenu();
        }
    }

    private static void displayStatsMenu() {
        System.out.print("""
                Какую статистику вы бы желали получить?
                
                1. По категориям проблем.
                2. По проблемам выбранной категории.
                3. По способам решения проблем, одобренных пользователями.
                """);

        try {
            switch (scanner.nextInt()) {
                case 1 -> StatisticsDisplayer.displayStats(ResultsWithCountLoader.loadCategories());
                case 2 -> {
                    Option category = OptionParamsReceiver.receiveCategory();
                    StatisticsDisplayer.displayStats(ResultsWithCountLoader.loadProblems(category.id()));
                }
                case 3 -> {
                    Option problem = OptionParamsReceiver.receiveProblem(OptionParamsReceiver.receiveCategory().id());
                    Map<Option, Integer> resultsCount = ResultsWithCountLoader.loadSolutions(problem.id());
                    StatisticsDisplayer.displayStats(resultsCount);
                }
                default -> displayStatsMenu();
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Введите корректное значение:\n");
            scanner.next();
            displayStatsMenu();
        }
    }

    private static void displayCommentsMenu() {
        System.out.print("""
                1. Получить все комментарии пользователей.
                2. Получить комментарии пользователей по категории проблем.
                3. Получить комментарии пользователей по проблеме выбранной категории.
                """);

        try {
            switch (scanner.nextInt()) {
                case 1 -> CommentsDisplayer.displayComments(CommentsLoader.loadAll());
                case 2 -> {
                    Option category = OptionParamsReceiver.receiveCategory();
                    CommentsDisplayer.displayComments(CommentsLoader.loadByCategory(category.name()));
                }
                case 3 -> {
                    Option problem = OptionParamsReceiver.receiveProblem(OptionParamsReceiver.receiveCategory().id());
                    CommentsDisplayer.displayComments(CommentsLoader.loadByProblem(problem.name()));
                }
                default -> displayCommentsMenu();
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Введите корректное значение:\n");
            scanner.next();
            displayCommentsMenu();
        }
    }
}
