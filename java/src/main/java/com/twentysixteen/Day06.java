package com.twentysixteen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * --- Day 6: Signals and Noise ---
 * <p>
 * Something is jamming your communications with Santa. Fortunately, your signal is only partially
 * jammed, and protocol in situations like this is to switch to a simple repetition code to get the
 * message through.
 * <p>
 * In this model, the same message is sent repeatedly. You've recorded the repeating message signal
 * (your puzzle input), but the data seems quite corrupted - almost too badly to recover. Almost.
 * <p>
 * All you need to do is figure out which character is most frequent for each position. For example,
 * suppose you had recorded the following messages:
 * <p>
 * eedadn drvtee eandsr raavrd atevrs tsrnev sdttsa rasrtv nssdts ntnada svetve tesnvt vntsnd vrdear
 * dvrsen enarar
 * <p>
 * The most common character in the first column is e; in the second, a; in the third, s, and so on.
 * Combining these characters returns the error-corrected message, easter.
 * <p>
 * Given the recording in your puzzle input, what is the error-corrected version of the message
 * being sent?
 */
public class Day06 {

  public static void main(String[] args) throws IOException {
    partOne(getInput());
    partTwo(getInput());
  }

  public static List<String> getInput() throws IOException {
    List<String> lines = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("inputs/day62016.txt"))) {
      String message;
      while ((message = br.readLine()) != null) {
        lines.add(message);
      }
    }
    return lines;
  }

  public static Map<Integer, Map<Character, Integer>> getFrequencyMap(List<String> inputMessages) {
    Map<Integer, Map<Character, Integer>> errorMessage = new HashMap<>();
    for (String message : inputMessages) {
      for (int i = 0; i < message.length(); i++) {
        char c = message.charAt(i);
        errorMessage.computeIfAbsent(i + 1, k -> new HashMap<>()).merge(c, 1, Integer::sum);
      }
    }
    return errorMessage;
  }

  public static void partOne(List<String> inputMessages) {
    Map<Integer, Map<Character, Integer>> errorMessage = getFrequencyMap(inputMessages);

    System.out.println(errorMessage.values().stream()
        .map(characterIntegerMap -> characterIntegerMap.entrySet().stream()
            .max(Entry.comparingByValue())
            .map(Entry::getKey)
            .map(String::valueOf)
            .orElse(""))
        .collect(Collectors.joining()));
  }

  public static void partTwo(List<String> inputMessages) {
    Map<Integer, Map<Character, Integer>> errorMessage = getFrequencyMap(inputMessages);

    System.out.println(errorMessage.values().stream()
        .map(characterIntegerMap -> characterIntegerMap.entrySet().stream()
            .min(Entry.comparingByValue())
            .map(Entry::getKey)
            .map(String::valueOf)
            .orElse(""))
        .collect(Collectors.joining()));
  }
}
