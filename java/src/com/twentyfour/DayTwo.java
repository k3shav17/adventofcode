package com.twentyfour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Fortunately, the first location The Historians want to search isn't a long walk from the Chief
 * Historian's office.
 * <p>
 * While the Red-Nosed Reindeer nuclear fusion/fission plant appears to contain no sign of the Chief
 * Historian, the engineers there run up to you as soon as they see you. Apparently, they still talk
 * about the time Rudolph was saved through molecular synthesis from a single electron.
 * <p>
 * They're quick to add that - since you're already here - they'd really appreciate your help
 * analyzing some unusual data from the Red-Nosed reactor. You turn to check if The Historians are
 * waiting for you, but they seem to have already divided into groups that are currently searching
 * every corner of the facility. You offer to help with the unusual data.
 * <p>
 * The unusual data (your puzzle input) consists of many reports, one report per line. Each report
 * is a list of numbers called levels that are separated by spaces. For example:
 * <p>
 * 7 6 4 2 1 1 2 7 8 9 9 7 6 2 1 1 3 2 4 5 8 6 4 4 1 1 3 6 7 9
 * <p>
 * This example data contains six reports each containing five levels.
 * <p>
 * The engineers are trying to figure out which reports are safe. The Red-Nosed reactor safety
 * systems can only tolerate levels that are either gradually increasing or gradually decreasing.
 * So, a report only counts as safe if both of the following are true:
 * <p>
 * The levels are either all increasing or all decreasing. Any two adjacent levels differ by at
 * least one and at most three.
 * <p>
 * In the example above, the reports can be found safe or unsafe by checking those rules:
 * <p>
 * 7 6 4 2 1: Safe because the levels are all decreasing by 1 or 2. 1 2 7 8 9: Unsafe because 2 7 is
 * an increase of 5. 9 7 6 2 1: Unsafe because 6 2 is a decrease of 4. 1 3 2 4 5: Unsafe because 1 3
 * is increasing but 3 2 is decreasing. 8 6 4 4 1: Unsafe because 4 4 is neither an increase or a
 * decrease. 1 3 6 7 9: Safe because the levels are all increasing by 1, 2, or 3.
 * <p>
 * So, in this example, 2 reports are safe.
 * <p>
 * Analyze the unusual data from the engineers. How many reports are safe?
 */

public class DayTwo {

    public static void main(String[] args) throws IOException {

        List<List<Integer>> reactorLevels = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
            new FileReader("java/inputs/2024/day_02.txt"))) {

            String reactorReport;
            while ((reactorReport = br.readLine()) != null) {
                String[] report = reactorReport.split("\\s+");
                reactorLevels.add(strArrToInt(report));
            }
        }
        partOne(reactorLevels);
    }

    private static List<Integer> strArrToInt(String[] report) {
        return Arrays.stream(report)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    private static void partOne(List<List<Integer>> reactorLevels) {
        List<List<Integer>> safeReports = new CopyOnWriteArrayList<>();
        List<List<Integer>> offByOneSafeReports = new CopyOnWriteArrayList<>();

        for (List<Integer> report : reactorLevels) {
            if (isIncreasing(report) || isDecreasing(report)) {
                safeReports.add(report);
            } else {
                if (isSafeReportBarringOneElement(report)) {
                    offByOneSafeReports.add(report);
                }
            }
        }

        System.out.println("safe reports = " + safeReports.size());
        // part two
        System.out.println("safe reports by removing one element = " + offByOneSafeReports.size());
        System.out.println(
            "total safe reports = " + (safeReports.size() + offByOneSafeReports.size()));
    }

    private static boolean isSafeReportBarringOneElement(List<Integer> report) {
        for (int i = report.size() - 1; i >= 0; i--) {
            List<Integer> tempList = new CopyOnWriteArrayList<>(report);
            tempList.remove(i);
            if (isIncreasing(tempList) || isDecreasing(tempList)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isIncreasing(List<Integer> reportOfLevel) {
        for (int i = 1; i < reportOfLevel.size(); i++) {
            int previous = reportOfLevel.get(i - 1);
            int current = reportOfLevel.get(i);
            if (current < previous || isNotInThresholdLimit(previous, current)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDecreasing(List<Integer> reportOfLevel) {
        for (int i = 1; i < reportOfLevel.size(); i++) {
            int previous = reportOfLevel.get(i - 1);
            int current = reportOfLevel.get(i);
            if (current > previous || isNotInThresholdLimit(previous, current)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNotInThresholdLimit(int previous, int current) {
        return Math.abs(previous - current) < 1 || Math.abs(previous - current) > 3;
    }
}
