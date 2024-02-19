package com.twentytwenty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent
 * calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star.
 * Good luck! Before you leave, the Elves in accounting just need you to fix your expense report
 * (your puzzle input); apparently, something isn't quite adding up.
 * <p>
 * Specifically, they need you to find the two entries that sum to 2020 and then multiply those two
 * numbers together.
 * <p>
 * For example, suppose your expense report contained the following:
 * <p>
 * 1721 979 366 299 675 1456
 * <p>
 * In this list, the two entries that sum to 2020 are 1721 and 299. Multiplying them together
 * produces 1721 * 299 = 514579, so the correct answer is 514579. Of course, your expense report is
 * much larger. Find the two entries that sum to 2020; what do you get if you multiply them
 * together?
 */

public class Day01 {

  public static void main(String[] args) throws IOException {

    Map<Integer, Integer> twoSum = new HashMap<>();
    int target = 2020;
    int first = 0, second = 0;

    try (BufferedReader br = new BufferedReader(new FileReader("inputs/day12020.txt"))) {

      String line;

      while ((line = br.readLine()) != null) {
        int star = Integer.parseInt(line);
        if (twoSum.containsKey(Math.abs(target - star))) {
          first = star;
          second = twoSum.get(Math.abs(target - star));
          break;
        } else {
          twoSum.put(star, star);
        }
      }
      System.out.println(first * second);
    }
  }


}
