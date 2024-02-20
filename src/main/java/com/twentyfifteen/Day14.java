package com.twentyfifteen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * --- Day 14: Reindeer Olympics ---
 * <p>
 * This year is the Reindeer Olympics! Reindeer can fly at high speeds, but must rest occasionally
 * to recover their energy. Santa would like to know which of his reindeer is fastest, and so he has
 * them race.
 * <p>
 * Reindeer can only either be flying (always at their top speed) or resting (not moving at all),
 * and always spend whole seconds in either state.
 * <p>
 * For example, suppose you have the following Reindeer:
 * <p>
 * Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds. Dancer can fly 16 km/s
 * for 11 seconds, but then must rest for 162 seconds.
 * <p>
 * After one second, Comet has gone 14 km, while Dancer has gone 16 km. After ten seconds, Comet has
 * gone 140 km, while Dancer has gone 160 km. On the eleventh second, Comet begins resting (staying
 * at 140 km), and Dancer continues on for a total distance of 176 km. On the 12th second, both
 * reindeer are resting. They continue to rest until the 138th second, when Comet flies for another
 * ten seconds. On the 174th second, Dancer flies for another 11 seconds.
 * <p>
 * In this example, after the 1000th second, both reindeer are resting, and Comet is in the lead at
 * 1120 km (poor Dancer has only gotten 1056 km by that point). So, in this situation, Comet would
 * win (if the race ended at 1000 seconds).
 * <p>
 * Given the descriptions of each reindeer (in your puzzle input), after exactly 2503 seconds, what
 * distance has the winning reindeer traveled?
 * <p>
 * Vixen can fly 19 km/s for 7 seconds, but then must rest for 124 seconds. Rudolph can fly 3 km/s
 * for 15 seconds, but then must rest for 28 seconds. Donner can fly 19 km/s for 9 seconds, but then
 * must rest for 164 seconds. Blitzen can fly 19 km/s for 9 seconds, but then must rest for 158
 * seconds. Comet can fly 13 km/s for 7 seconds, but then must rest for 82 seconds. Cupid can fly 25
 * km/s for 6 seconds, but then must rest for 145 seconds. Dasher can fly 14 km/s for 3 seconds, but
 * then must rest for 38 seconds. Dancer can fly 3 km/s for 16 seconds, but then must rest for 37
 * seconds. Prancer can fly 25 km/s for 6 seconds, but then must rest for 143 seconds.
 */
public class Day14 {

  static Map<String, Integer> participants = new HashMap<>();
  static final int MAX_SECONDS = 2503;

  public static void main(String[] args) throws IOException {

    try (BufferedReader br = new BufferedReader(new FileReader("inputs/Day142015.txt"))) {
      String participant;
      while ((participant = br.readLine()) != null) {
        String[] details = participant.split(" ");
        totalDistanceTravelled(details[0], Integer.parseInt(details[3]),
            Integer.parseInt(details[6]), Integer.parseInt(details[13]));
      }
    }
    System.out.println(participants.entrySet().stream().max(Entry.comparingByValue()));
  }

  public static void totalDistanceTravelled(String deerName, int flyingDistance, int flyingTime,
      int restingTime) {

    int totalDistance = 0;
    int seconds = 0;

    while (seconds < MAX_SECONDS) {
      for (int i = 0; i < flyingTime; i++) {
        totalDistance += flyingDistance;
      }
      seconds += restingTime + flyingTime;
    }
    participants.put(deerName, totalDistance);
  }

}
