package com.twentyeighteen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * --- Day 1: Chronal Calibration ---
 * <p>
 * "We've detected some temporal anomalies," one of Santa's Elves at the Temporal Anomaly Research
 * and Detection Instrument Station tells you. She sounded pretty worried when she called you down
 * here. "At 500-year intervals into the past, someone has been changing Santa's history!"
 * <p>
 * "The good news is that the changes won't propagate to our time stream for another 25 days, and we
 * have a device" - she attaches something to your wrist - "that will let you fix the changes with
 * no such propagation delay. It's configured to send you 500 years further into the past every few
 * days; that was the best we could do on such short notice."
 * <p>
 * "The bad news is that we are detecting roughly fifty anomalies throughout time; the device will
 * indicate fixed anomalies with stars. The other bad news is that we only have one device and
 * you're the best person for the job! Good lu--" She taps a button on the device and you suddenly
 * feel like you're falling. To save Christmas, you need to get all fifty stars by December 25th.
 * <p>
 * Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent
 * calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star.
 * Good luck!
 * <p>
 * After feeling like you've been falling for a few minutes, you look at the device's tiny screen.
 * "Error: Device must be calibrated before first use. Frequency drift detected. Cannot maintain
 * destination lock." Below the message, the device shows a sequence of changes in frequency (your
 * puzzle input). A value like +6 means the current frequency increases by 6; a value like -3 means
 * the current frequency decreases by 3.
 * <p>
 * For example, if the device displays frequency changes of +1, -2, +3, +1, then starting from a
 * frequency of zero, the following changes would occur:
 * <p>
 * Current frequency  0, change of +1; resulting frequency  1. Current frequency  1, change of -2;
 * resulting frequency -1. Current frequency -1, change of +3; resulting frequency  2. Current
 * frequency  2, change of +1; resulting frequency  3.
 * <p>
 * In this example, the resulting frequency is 3.
 * <p>
 * Here are other example situations:
 * <p>
 * +1, +1, +1 results in  3 +1, +1, -2 results in  0 -1, -2, -3 results in -6
 * <p>
 * Starting with a frequency of zero, what is the resulting frequency after all of the changes in
 * frequency have been applied?
 */

public class Day01 {

  public static void main(String[] args) throws IOException {
    partOne(getInput());
    partTwo(getInput());
  }

  public static List<Integer> getInput() throws IOException {
    List<Integer> frequencies = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("inputs/2018/day1.txt"))) {
      String frequency;
      while ((frequency = br.readLine()) != null) {
        if (frequency.charAt(0) == '-') {
          int negative = -Integer.parseInt(frequency.substring(1));
          frequencies.add(negative);
        } else {
          int positive = Integer.parseInt(frequency.substring(1));
          frequencies.add(positive);
        }
      }
    }
    return frequencies;
  }

  public static void partOne(List<Integer> frequencies) {
    int resultingFrequency = 0;
    for (int frequency : frequencies) {
      resultingFrequency += frequency;
    }
    System.out.println(resultingFrequency);
  }

  public static void partTwo(List<Integer> frequencies) {
    int resultingFrequency = 0;
    Set<Integer> frequencySet = new HashSet<>();

    while (true) {
      for (int frequency : frequencies) {
        resultingFrequency += frequency;
        if (frequencySet.contains(resultingFrequency)) {
          System.out.println(resultingFrequency);
          return;
        }
        frequencySet.add(resultingFrequency);
      }
    }
  }
}
