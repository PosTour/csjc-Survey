package display;

import options.Option;
import statistics_calculation.StatisticsCalculator;

import java.util.Map;

public class StatisticsDisplayer {
    private StatisticsDisplayer() {}

    public static void displayStats(Map<Option, Integer> resultsCount) {
        Map<String, Double> stats = StatisticsCalculator.calculateStatistics(resultsCount);

        stats.forEach((key, value) -> {
            System.out.printf("%s%n%.2f%%%n", key, value * 100);
        });
    }
}
