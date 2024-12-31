package com.twentyfifteen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 9: All in a Single Night ---
 * <p>
 * Every year, Santa manages to deliver all of his presents in a single night.
 * <p>
 * This year, however, he has some new locations to visit; his elves have provided him the distances
 * between every pair of locations. He can start and end at any two (different) locations he wants,
 * but he must visit each location exactly once. What is the shortest distance he can travel to
 * achieve this?
 * <p>
 * For example, given the following distances:
 * <p>
 * London to Dublin = 464 London to Belfast = 518 Dublin to Belfast = 141
 * <p>
 * The possible routes are therefore:
 * <p>
 * Dublin -> London -> Belfast = 982 London -> Dublin -> Belfast = 605 London -> Belfast -> Dublin =
 * 659 Dublin -> Belfast -> London = 659 Belfast -> Dublin -> London = 605 Belfast -> London ->
 * Dublin = 982
 * <p>
 * The shortest of these is London -> Dublin -> Belfast = 605, and so the answer is 605 in this
 * example.
 * <p>
 * What is the distance of the shortest route?
 */
public class Day09 {

  public static void main(String[] args) throws IOException {

    List<String> cities = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("inputs/2015/day9.txt"))) {
      String locationDetails;

      while ((locationDetails = br.readLine()) != null) {
        String[] locations = locationDetails.split(" ");
        cities.add(locations[0]);
        cities.add(locations[2]);
      }
    }
    System.out.println(cities);
  }
}
