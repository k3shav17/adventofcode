package com.twentyfour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Throughout the Chief's office, the historically significant locations are
 * listed not by name but
 * by a unique number called the location ID. To make sure they don't miss a hin
 * The sto ns split
 * into two groups, each searching the office and trying to create their own
 * complete list of
 * <p>
 * location IDs. There's just one problem: by holding the two lists up side y
 * side (your puzzle
 * <p>
 * input), it quickly becomes clear that the lists aren't very similar. Maybe
 * you can help The
 * Historians reconcile their lists?
 *
 * <p>
 * <p>
 * For example:
 *
 * <p>
 * 3 4 4 3 2 5 1 3 3 9 3 3
 * <p>
 * Maybe the lists are only off by a small amount! To find out, pair up the
 * <p>
 * numbers and measure how
 * <p>
 * far apart they are. Pair up the smallest number in the left list with the
 * <p>
 * smallest number in the
 * <p>
 * right list, then the second-smallest left number with the second-smallest
 * <p>
 * right number, and so
 * <p>
 * on.
 * <p>
 * Within each pair, figure out how far apart the two numbers are; you'll need
 * <p>
 * to add up all of
 * <p>
 * those distances. For example, if you pair up a 3 from the left list with a 7
 * from the right list,
 * the distance apart is 4; if you pair up a 9 with a 3, the distance apart is
 * <p>
 * 6.
 * <p>
 * In the example list above, the pairs and distances would be as follows:
 * <p>
 * <p>
 * right list is 3. The
 * <p>
 * and the second-smallest number in the right list is another 3. The distance
 * between them is 1.
 * The third-smallest number in both lists is 3, so the distance between them is
 * 0. The next numbers
 * to pair up are 3 and 4, a distance of 1. The fifth-smallest numbers in each
 * list are 3 and 5, a
 * distance of 2. Finally, the largest number in the left list is 4, while the
 * largest number in the
 * right list is 9; these are a distance 5 apart.
 * <p>
 * To find the total distance between the left list and the right list, add up
 * the distances between
 * all the pairs you found. In the example above, this is 2 + 1 + 0 + 1 + 2 + 5,
 * a total distance of
 * 11!
 * <p>
 * Your actual left and right lists contain many location IDs. What is the total
 * distance between
 * your lists?
 */

public class DayOne {

    public static void main(String[] args) throws IOException {

        List<Integer> locationsOnLeft = new ArrayList<>();
        List<Integer> locationsOnRight = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader("java/inputs/2024/day01_part1.txt"))) {
            String locationIds;
            while ((locationIds = br.readLine()) != null) {
                String[] slicedLocations = locationIds.trim().split("\\s+");
                locationsOnLeft.add(Integer.parseInt(slicedLocations[0]));
                locationsOnRight.add(Integer.parseInt(slicedLocations[1]));
            }
        }
        Collections.sort(locationsOnLeft);
        Collections.sort(locationsOnRight);

        partOne(locationsOnLeft, locationsOnRight);
        partTwo(locationsOnLeft, locationsOnRight);
    }

    private static void partOne(List<Integer> locationsOnLeft,
            List<Integer> locationsOnRight) {
        System.out.println(totalDistance(locationsOnLeft, locationsOnRight));
    }

    private static int totalDistance(List<Integer> locationIdsOnLeft,
            List<Integer> locationIdsOnRight) {
        return IntStream.range(0, locationIdsOnLeft.size())
                .map(i -> Math.abs(locationIdsOnRight.get(i) - locationIdsOnLeft.get(i)))
                .sum();

    }

    private static void partTwo(List<Integer> locationsOnLeft,
            List<Integer> locationsOnRight) {
        System.out.println(similarityScore(locationsOnLeft, locationsOnRight));
    }

    private static int similarityScore(List<Integer> locationIdsOnLeft,
            List<Integer> locationIdsOnRight) {

        Map<Integer, Integer> rightLocationsMap = new HashMap<>();
        int similarScore = 0;

        for (int id : locationIdsOnRight) {
            rightLocationsMap.put(id, rightLocationsMap.getOrDefault(id, 0) + 1);
        }

        for (int id : locationIdsOnLeft) {
            if (rightLocationsMap.containsKey(id)) {
                similarScore += id * rightLocationsMap.get(id);
            }
        }

        return similarScore;
    }

}
