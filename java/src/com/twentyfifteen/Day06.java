package com.twentyfifteen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 6: Probably a Fire Hazard ---
 * <p>
 * Because your neighbors keep defeating you in the holiday house decorating contest year after
 * year, you've decided to deploy one million lights in a 1000x1000 grid.
 * <p>
 * Furthermore, because you've been especially nice this year, Santa has mailed you instructions on
 * how to display the ideal lighting configuration.
 * <p>
 * Lights in your grid are numbered from 0 to 999 in each direction; the lights at each corner are
 * at 0,0, 0,999, 999,999, and 999,0. The instructions include whether to turn on, turn off, or
 * toggle various inclusive ranges given as coordinate pairs. Each coordinate pair represents
 * opposite corners of a rectangle, inclusive; a coordinate pair like 0,0 through 2,2 therefore
 * refers to 9 lights in a 3x3 square. The lights all start turned off.
 * <p>
 * To defeat your neighbors this year, all you have to do is set up your lights by doing the
 * instructions Santa sent you in order.
 * <p>
 * For example:
 * <p>
 * turn on 0,0 through 999,999 would turn on (or leave on) every light. toggle 0,0 through 999,0
 * would toggle the first line of 1000 lights, turning off the ones that were on, and turning on the
 * ones that were off. turn off 499,499 through 500,500 would turn off (or leave off) the middle
 * four lights.
 * <p>
 * After following the instructions, how many lights are lit? turn off 660,55 through 986,197 toggle
 * 322,558 through 977,958 turn on 240,129 through 703,297
 */
public class Day06 {

  private static final String TURN_OFF = "turn off";
  private static final String TURN_ON = "turn on";
  private static final String TOGGLE = "toggle";

  public static void main(String[] args) throws IOException {
    int statusOn = 0;
    int[][] grid = new int[1000][1000];

    try (BufferedReader br = new BufferedReader(new FileReader("inputs/2015/day6.txt"))) {
      String instructions;
      while ((instructions = br.readLine()) != null) {
        String operation = deriveInstruction(instructions);
        List<Integer> coordinates = extractNumbers(instructions);
        int x = coordinates.get(0);
        int y = coordinates.get(1);
        int xBar = coordinates.get(2);
        int yBar = coordinates.get(3);
        performInstructions(operation, x, y, xBar, yBar, grid);
      }
    }

    for (int[] row : grid) {
      for (int cell : row) {
        statusOn += cell;
      }
    }
    System.out.println(statusOn);
  }

  public static void performInstructions(String operation, int x, int y, int xBar, int yBar,
      int[][] grid) {
    for (int i = x; i < xBar; i++) {
      for (int j = y; j < yBar; j++) {
        switch (operation) {
          case TURN_OFF -> grid[i][j] = 0;
          case TURN_ON -> grid[i][j] = 1;
          case TOGGLE -> grid[i][j] = (grid[i][j] == 0 ? 1 : 0);
          default -> {
            // no op
          }
        }
      }
    }
  }

  public static List<Integer> extractNumbers(String input) {
    List<Integer> numbers = new ArrayList<>();
    Pattern pattern = Pattern.compile("\\b\\d+\\b");
    Matcher matcher = pattern.matcher(input);

    while (matcher.find()) {
      numbers.add(Integer.parseInt(matcher.group()));
    }
    return numbers;
  }

  public static String deriveInstruction(String instructions) {
    if (instructions.contains(TURN_OFF)) {
      return TURN_OFF;
    } else if (instructions.contains(TURN_ON)) {
      return TURN_ON;
    } else {
      return TOGGLE;
    }
  }
}
