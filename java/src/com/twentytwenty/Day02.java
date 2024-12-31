package com.twentytwenty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;


/**
 * Your flight departs in a few days from the coastal airport; the easiest way down to the coast
 * from here is via toboggan.
 * <p>
 * The shopkeeper at the North Pole Toboggan Rental Shop is having a bad day. "Something's wrong
 * with our computers; we can't log in!" You ask if you can take a look.
 * <p>
 * Their password database seems to be a little corrupted: some of the passwords wouldn't have been
 * allowed by the Official Toboggan Corporate Policy that was in effect when they were chosen.
 * <p>
 * To try to debug the problem, they have created a list (your puzzle input) of passwords (according
 * to the corrupted database) and the corporate policy when that password was set.
 * <p>
 * For example, suppose you have the following list:
 * <p>
 * 1-3 a: abcde 1-3 b: cdefg 2-9 c: ccccccccc
 * <p>
 * Each line gives the password policy and then the password. The password policy indicates the
 * lowest and highest number of times a given letter must appear for the password to be valid. For
 * example, 1-3 a means that the password must contain a at least 1 time and at most 3 times.
 * <p>
 * In the above example, 2 passwords are valid. The middle password, cdefg, is not; it contains no
 * instances of b, but needs at least 1. The first and third passwords are valid: they contain one a
 * or nine c, both within the limits of their respective policies.
 * <p>
 * How many passwords are valid according to their policies?
 */
public class Day02 {

  public static void main(String[] args) throws IOException {
    Day02 dayTwo = new Day02();
    dayTwo.printValidCount();
  }

  public static boolean isValid(String password) {
    char charToCheck = targetChar(password)[0];
    List<Integer> range = splitAndGetRange(password);
    int lower = range.get(0);
    int upper = range.get(1);

    ValueRange charRange = ValueRange.of(lower, upper);
    int charCount = 0;

    for (int i = 7; i < password.length(); i++) {
      if (password.charAt(i) == charToCheck) {
        charCount++;
      }
    }
    return charRange.isValidIntValue(charCount);
  }

  public static char[] targetChar(String password) {
    String[] instructions = password.split(":");
    return instructions[0].split("-")[1].split(" ")[1].toCharArray();
  }

  public static List<Integer> splitAndGetRange(String password) {
    String[] instructions = password.split(":");
    List<Integer> range = new ArrayList<>();
    range.add(Integer.parseInt(instructions[0].split("-")[0]));
    range.add(Integer.parseInt(instructions[0].split("-")[1].split(" ")[0]));
    return range;
  }

  private void printValidCount() throws IOException {
    int validCounter = 0;
    try (BufferedReader br = new BufferedReader(new FileReader("inputs/day022020.txt"))) {
      String password;
      while ((password = br.readLine()) != null) {
        if (isValid(password)) {
          validCounter++;
        }
      }
    }
    System.out.println(validCounter);
  }
}
