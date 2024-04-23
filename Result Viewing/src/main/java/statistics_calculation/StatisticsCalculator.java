package statistics_calculation;

import options.Option;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StatisticsCalculator {
    private StatisticsCalculator() {}

    public static Map<String, Double> calculateStatistics(Map<Option, Integer> resultsCount) {
        int totalCount = returnTotalCount(resultsCount);

        Map<String, Double> stats = new HashMap<>();

        if (totalCount == 0) {
            resultsCount.forEach((key, value) -> stats.put(key.name(), 0.0));
        } else {
            resultsCount.forEach((key, value) -> stats.put(key.name(), (double) value / totalCount));
        }

        return sortStatsByValue(stats);
    }

    private static Map<String, Double> sortStatsByValue(Map<String, Double> stats) {
        Map<String, Double> statsByValue = new LinkedHashMap<>();

        stats.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(stat -> statsByValue.put(stat.getKey(), stat.getValue()));

        return statsByValue;
    }

    private static int returnTotalCount(Map<Option, Integer> resultsCount) {
        return resultsCount.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
