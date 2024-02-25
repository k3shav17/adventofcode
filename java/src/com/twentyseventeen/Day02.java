package com.twentyseventeen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * --- Day 2: Corruption Checksum ---
 * <p>
 * As you walk through the door, a glowing humanoid shape yells in your direction. "You there! Your
 * state appears to be idle. Come help us repair the corruption in this spreadsheet - if we take
 * another millisecond, we'll have to display an hourglass cursor!"
 * <p>
 * The spreadsheet consists of rows of apparently-random numbers. To make sure the recovery process
 * is on the right track, they need you to calculate the spreadsheet's checksum. For each row,
 * determine the difference between the largest value and the smallest value; the checksum is the
 * sum of all of these differences.
 * <p>
 * For example, given the following spreadsheet:
 * <p>
 * 5 1 9 5 7 5 3 2 4 6 8
 * <p>
 * The first row's largest and smallest values are 9 and 1, and their difference is 8. The second
 * row's largest and smallest values are 7 and 3, and their difference is 4. The third row's
 * difference is 6.
 * <p>
 * In this example, the spreadsheet's checksum would be 8 + 4 + 6 = 18.
 * <p>
 * What is the checksum for the spreadsheet in your puzzle input?
 * <p>
 * <p>
 * --- Part Two ---
 * <p>
 * "Great work; looks like we're on the right track after all. Here's a star for your effort."
 * However, the program seems a little worried. Can programs be worried?
 * <p>
 * "Based on what we're seeing, it looks like all the User wanted is some information about the
 * evenly divisible values in the spreadsheet. Unfortunately, none of us are equipped for that kind
 * of calculation - most of us specialize in bitwise operations."
 * <p>
 * It sounds like the goal is to find the only two numbers in each row where one evenly divides the
 * other - that is, where the result of the division operation is a whole number. They would like
 * you to find those numbers on each line, divide them, and add up each line's result.
 * <p>
 * For example, given the following spreadsheet:
 * <p>
 * 5 9 2 8 9 4 7 3 3 8 6 5
 * <p>
 * In the first row, the only two numbers that evenly divide are 8 and 2; the result of this
 * division is 4. In the second row, the two numbers are 9 and 3; the result is 3. In the third row,
 * the result is 2.
 * <p>
 * In this example, the sum of the results would be 4 + 3 + 2 = 9.
 * <p>
 * What is the sum of each row's result in your puzzle input?
 */
public class Day02 {

  public static void main(String[] args) throws IOException {
    partOne();
  }

  public static void partOne() throws IOException {
    int checksum = 0;
    int checksumTwo = 0;
    try (BufferedReader br = new BufferedReader(
        new FileReader("inputs/2017/day2.txt"))) {
      String row;
      while ((row = br.readLine()) != null) {
        List<Integer> spreadsheetCells = getCells(row);
        checksum += calculateChecksum(spreadsheetCells);
        // part 2
        checksumTwo += getWholeNumbers(spreadsheetCells);
      }
    }
    System.out.println(checksumTwo);
    System.out.println(checksum);
  }

  public static List<Integer> getCells(String spreadsheetCells) {
    return Arrays.stream(spreadsheetCells.trim().split("\\s+"))
        .map(Integer::parseInt)
        .toList();
  }

  public static int calculateChecksum(List<Integer> spreadsheetCells) {
    OptionalInt max = spreadsheetCells.stream().mapToInt(Integer::intValue).max();
    OptionalInt min = spreadsheetCells.stream().mapToInt(Integer::intValue).min();
    return max.orElse(0) - min.orElse(0);
  }

  public static int getWholeNumbers(List<Integer> spreadsheetCells) {
    return IntStream.range(0, spreadsheetCells.size())
        .boxed()
        .flatMapToInt(e1 -> IntStream.range(0, spreadsheetCells.size())
            .filter(e2 -> e1 != e2 && spreadsheetCells.get(e1) % spreadsheetCells.get(e2) == 0)
            .map(e2 -> spreadsheetCells.get(e1) / spreadsheetCells.get(e2)))
        .findFirst().orElse(0);
  }
}
