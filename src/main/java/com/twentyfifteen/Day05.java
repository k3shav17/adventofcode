package com.twentyfifteen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * --- Day 5: Doesn't He Have Intern-Elves For This? ---
 * <p>
 * Santa needs help figuring out which strings in his text file are naughty or nice.
 * <p>
 * A nice string is one with all the following properties:
 * <p>
 * It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou. It
 * contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa,
 * bb, cc, or dd). It does not contain the strings ab, cd, pq, or xy, even if they are part of one
 * of the other requirements.
 * <p>
 * For example:
 * <p>
 * ugknbfddgicrmopn is nice because it has at least three vowels (u...i...o...), a double letter
 * (...dd...), and none of the disallowed substrings. aaa is nice because it has at least three
 * vowels and a double letter, even though the letters used by different rules overlap.
 * jchzalrnumimnmhp is naughty because it has no double letter. haegwjzuvuyypxyu is naughty because
 * it contains the string xy. dvszwmarrgswjxmb is naughty because it contains only one vowel.
 * <p>
 * How many strings are nice?
 */
public class Day05 {


  private static final Set<Character> vowelSet = new HashSet<>();
  private static final Set<String> invalidChars = new HashSet<>();

  static {
    vowelSet.add('a');
    vowelSet.add('e');
    vowelSet.add('i');
    vowelSet.add('o');
    vowelSet.add('u');

    invalidChars.add("ab");
    invalidChars.add("cd");
    invalidChars.add("pq");
    invalidChars.add("xy");

  }

  public static void main(String[] args) throws IOException {
    int nicekid = 0;

    try (BufferedReader br = new BufferedReader(new FileReader("inputs/day52015.txt"))) {
      String kid;
      while ((kid = br.readLine()) != null) {
        if (!isNaughty(kid)) {
          nicekid++;
        }
      }
    }
    System.out.println(nicekid);
  }

  public static boolean isNaughty(String kid) {
    return containsAtLeastThreeVowels(kid) || containsInvalidChars(kid)
        || !containsDoubleLetters(kid);
  }

  private static boolean containsAtLeastThreeVowels(String kid) {
    int vowelCount = 0;
    for (int i = 0; i < kid.length(); i++) {
      if (vowelSet.contains(kid.charAt(i))) {
        vowelCount++;
      }
    }
    return vowelCount < 3;
  }

  private static boolean containsDoubleLetters(String kid) {
    for (int i = 0; i < kid.length() - 1; i++) {
      String doubleLetters = kid.substring(i, i + 2);
      if (doubleLetters.chars().distinct().count() == 1) {
        return true;
      }
    }
    return false;
  }

  private static boolean containsInvalidChars(String kid) {
    for (int i = 0; i < kid.length() - 1; i++) {
      if (invalidChars.contains(kid.substring(i, i + 2))) {
        return true;
      }
    }
    return false;
  }
}
