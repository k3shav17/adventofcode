package com.twentyfour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * --- Day 7: Bridge Repair --- The Historians take you to a familiar rope bridge over a river in
 * the middle of a jungle. The Chief isn't on this side of the bridge, though; maybe he's on the
 * other side?
 * <p>
 * When you go to cross the bridge, you notice a group of engineers trying to repair it.
 * (Apparently, it breaks pretty frequently.) You won't be able to cross until it's fixed.
 * <p>
 * You ask how long it'll take; the engineers tell you that it only needs final calibrations, but
 * some young elephants were playing nearby and stole all the operators from their calibration
 * equations! They could finish the calibrations if only someone could determine which test values
 * could possibly be produced by placing any combination of operators into their calibration
 * equations (your puzzle input).
 * <p>
 * For example:
 * <p>
 * 190: 10 19 3267: 81 40 27 83: 17 5 156: 15 6 7290: 6 8 6 15 161011: 16 10 13 192: 17 8 14 21037:
 * 9 7 18 13 292: 11 6 16 20
 * <p>
 * Each line represents a single equation. The test value appears before the colon on each line; it
 * is your job to determine whether the remaining numbers can be combined with operators to produce
 * the test value.
 * <p>
 * Operators are always evaluated left-to-right, not according to precedence rules. Furthermore,
 * numbers in the equations cannot be rearranged. Glancing into the jungle, you can see elephants
 * holding two different types of operators: add (+) and multiply (*).
 * <p>
 * Only three of the above equations can be made true by inserting operators:
 * <p>
 * 190: 10 19 has only one position that accepts an operator: between 10 and 19. Choosing + would
 * give 29, but choosing * would give the test value (10 * 19 = 190). 3267: 81 40 27 has two
 * positions for operators. Of the four possible configurations of the operators, two cause the
 * right side to match the test value: 81 + 40 * 27 and 81 * 40 + 27 both equal 3267 (when evaluated
 * left-to-right)! 292: 11 6 16 20 can be solved in exactly one way: 11 + 6 * 16 + 20.
 * <p>
 * The engineers just need the total calibration result, which is the sum of the test values from
 * just the equations that could possibly be true. In the above example, the sum of the test values
 * for the three equations listed above is 3749.
 * <p>
 * Determine which equations could possibly be true. What is their total calibration result?
 * <p>
 * --- Part Two ---
 * <p>
 * The engineers seem concerned; the total calibration result you gave them is nowhere close to
 * being within safety tolerances. Just then, you spot your mistake: some well-hidden elephants are
 * holding a third type of operator.
 * <p>
 * The concatenation operator (||) combines the digits from its left and right inputs into a single
 * number. For example, 12 || 345 would become 12345. All operators are still evaluated
 * left-to-right.
 * <p>
 * Now, apart from the three equations that could be made true using only addition and
 * multiplication, the above example has three more equations that can be made true by inserting
 * operators:
 * <p>
 * 156: 15 6 can be made true through a single concatenation: 15 || 6 = 156. 7290: 6 8 6 15 can be
 * made true using 6 * 8 || 6 * 15. 192: 17 8 14 can be made true using 17 || 8 + 14.
 * <p>
 * Adding up all six test values (the three that could be made before using only + and * plus the
 * new three that can now be made by also using ||) produces the new total calibration result of
 * 11387.
 * <p>
 * Using your new knowledge of elephant hiding spots, determine which equations could possibly be
 * true. What is their total calibration result?
 */

public class DaySeven {

  public static void main(String[] args) throws IOException {

    BigInteger calibrationResult = BigInteger.ZERO;
    try (BufferedReader br = new BufferedReader(new FileReader("java/inputs/2024/day_07.txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
        calibrationResult = calibrationResult.add(resultAndOperands(line.strip()));
      }
    }
    System.out.println(calibrationResult);
  }


  private static BigInteger resultAndOperands(String line) {
    String[] arr = line.split(":");
    BigInteger result = new BigInteger(arr[0]);
    int[] operands = Arrays.stream(arr[1].strip().split("\\s+")).mapToInt(Integer::parseInt)
        .toArray();
    BigInteger accumulator = BigInteger.valueOf(operands[0]);

    if (canCompute(operands, 1, accumulator, result)) {
      return result;
    }
    return BigInteger.ZERO;
  }

  private static boolean canCompute(int[] operands, int index, BigInteger accumulator,
      BigInteger result) {
    if (index == operands.length) {
      return accumulator.equals(result);
    }

    BigInteger addition = accumulator.add(BigInteger.valueOf(operands[index]));
    BigInteger multiplication = accumulator.multiply(BigInteger.valueOf(operands[index]));
    BigInteger or = parsePipe(accumulator, operands[index]);
    index++;

    boolean isAddition = canCompute(operands, index, addition, result);
    boolean isMultiplication = canCompute(operands, index, multiplication, result);
    // part two
    boolean isOr = canCompute(operands, index, or, result);
    return isAddition || isMultiplication || isOr;

  }

  private static BigInteger parsePipe(BigInteger acc, int current) {
    return new BigInteger(acc + String.valueOf(current));
  }

}