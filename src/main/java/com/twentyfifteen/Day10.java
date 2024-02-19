package com.twentyfifteen;

import java.util.Objects;

/**
 * --- Day 10: Elves Look, Elves Say ---
 * <p>
 * Today, the Elves are playing a game called look-and-say. They take turns making sequences by
 * reading aloud the previous sequence and using that reading as the next sequence. For example, 211
 * is read as "one two, two ones", which becomes 1221 (1 2, 2 1s).
 * <p>
 * Look-and-say sequences are generated iteratively, using the previous value as input for the next
 * step. For each step, take the previous value, and replace each run of digits (like 111) with the
 * number of digits (3) followed by the digit itself (1).
 * <p>
 * For example:
 * <p>
 * 1 becomes 11 (1 copy of digit 1). 11 becomes 21 (2 copies of digit 1). 21 becomes 1211 (one 2
 * followed by one 1). 1211 becomes 111221 (one 1, one 2, and two 1s). 111221 becomes 312211 (three
 * 1s, two 2s, and one 1).
 * <p>
 * Starting with the digits in your puzzle input, apply this process 40 times. What is the length of
 * the result?
 * <p>
 * Your puzzle input is 1113222113.
 */
public class Day10 {

  public static void main(String[] args) {
    StringBuilder input = new StringBuilder("11132221130");
    for (int i = 0; i < 50; i++) {
      input = lookAndSay(input.append(0));
    }
    System.out.println(input.length());
  }

  public static StringBuilder lookAndSay(StringBuilder digits) {
    StringBuilder sb = new StringBuilder();
    int start = 0;
    for (int i = 0; i < digits.length() - 1; i++) {
      if (!isCharAtNextSame(digits.charAt(i), digits.charAt(i + 1))) {
        sb.append(digits.substring(start, i + 1).length()).append(digits.charAt(i));
        start = i + 1;
      }
    }
    return sb;
  }

  public static boolean isCharAtNextSame(char a, char b) {
    return Objects.equals(a, b);
  }
}
