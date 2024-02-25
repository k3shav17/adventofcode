package com.twentyseventeen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * --- Day 4: High-Entropy Passphrases ---
 * <p>
 * A new system policy has been put in place that requires all accounts to use a passphrase instead
 * of simply a password. A passphrase consists of a series of words (lowercase letters) separated by
 * spaces.
 * <p>
 * To ensure security, a valid passphrase must contain no duplicate words.
 * <p>
 * For example:
 * <p>
 * aa bb cc dd ee is valid. aa bb cc dd aa is not valid - the word aa appears more than once. aa bb
 * cc dd aaa is valid - aa and aaa count as different words.
 * <p>
 * The system's full passphrase list is available as your puzzle input. How many passphrases are
 * valid?
 */
public class Day04 {

  public static void main(String[] args) throws IOException {
    partOne(getInput());
  }

  public static List<String> getInput() throws IOException {
    List<String> input = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("inputs/2017/day4.txt"))) {
      String passpharase;
      while ((passpharase = br.readLine()) != null) {
        input.add(passpharase);
      }
    }
    return input;
  }

  public static void partOne(List<String> passphrases) {
    int validPhrase = 0;
    int validPhraseV2 = 0;
    for (String passphrase : passphrases) {
      if (isValidPhrase(passphrase)) {
        validPhrase++;
      }
      if (isValidPhraseV2(passphrase)) {
        validPhraseV2++;
      }
    }
    System.out.println(validPhraseV2);
    System.out.println(validPhrase);
  }


  public static boolean isValidPhraseV2(String passphrase) {
    Set<String> uniquePhrase = new HashSet<>();
    String[] words = passphrase.split("\\s+");

    for (String word : words) {
      String sortedWord = getSortedWord(word);
      if (uniquePhrase.contains(sortedWord)) {
        return false;
      }
      uniquePhrase.add(sortedWord);
    }
    return true;
  }

  public static String getSortedWord(String word) {
    return word.trim().chars()
        .sorted()
        .collect(
            StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }

  public static boolean isValidPhrase(String passphrase) {
    Set<String> uniquePhrase = new HashSet<>();
    String[] words = passphrase.split("\\s+");

    for (String word : words) {
      if (uniquePhrase.contains(word)) {
        return false;
      }
      uniquePhrase.add(word);
    }
    return true;
  }
}
