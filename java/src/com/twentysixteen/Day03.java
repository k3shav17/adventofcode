package com.twentysixteen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Now that you can think clearly, you move deeper into the labyrinth of hallways and office
 * furniture that makes up this part of Easter Bunny HQ. This must be a graphic design department;
 * the walls are covered in specifications for triangles.
 * <p>
 * Or are they?
 * <p>
 * The design document gives the side lengths of each triangle it describes, but... 5 10 25? Some of
 * these aren't triangles. You can't help but mark the impossible ones.
 * <p>
 * In a valid triangle, the sum of any two sides must be larger than the remaining side. For
 * example, the "triangle" given above is impossible, because 5 + 10 is not larger than 25.
 * <p>
 * In your puzzle input, how many of the listed triangles are possible?
 * <p>
 * PART - 2
 * Now that you've helpfully marked up their design documents, it occurs to you that
 * triangles are specified in groups of three vertically. Each set of three numbers in a column
 * specifies a triangle. Rows are unrelated.
 * <p>
 * For example, given the following specification, numbers with the same hundreds digit would be
 * part of the same triangle:
 * <p>
 * 101 301 501 102 302 502 103 303 503 201 401 601 202 402 602 203 403 603
 * <p>
 * In your puzzle input, and instead reading by columns, how many of the listed triangles are
 * possible?
 */
public class Day03 {

  public static void main(String[] args) throws IOException {
    partOne();
    partTwo();
  }

  public static void partTwo() throws IOException {
    int possibleTriangles = 0;

    List<Integer> columnOne = new ArrayList<>();
    List<Integer> columnTwo = new ArrayList<>();
    List<Integer> columnThree = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("inputs/2016/day3.txt"))) {
      String triangle;
      while ((triangle = br.readLine()) != null) {
        List<Integer> sides = getSides(triangle);
        columnOne.add(sides.get(0));
        columnTwo.add(sides.get(1));
        columnThree.add(sides.get(2));
      }
    }

    for (int i = 0; i < columnOne.size(); i += 3) {
      if (isTriangle(columnOne.get(i), columnOne.get(i + 1), columnOne.get(i + 2))) {
        possibleTriangles++;
      }
      if (isTriangle(columnTwo.get(i), columnTwo.get(i + 1), columnTwo.get(i + 2))) {
        possibleTriangles++;
      }
      if (isTriangle(columnThree.get(i), columnThree.get(i + 1), columnThree.get(i + 2))) {
        possibleTriangles++;
      }
    }
    System.out.println(possibleTriangles);
  }

  public static void partOne() throws IOException {
    int possibleTriangles = 0;
    try (BufferedReader br = new BufferedReader(new FileReader("inputs/day3.txt"))) {
      String triangle;
      while ((triangle = br.readLine()) != null) {
        List<Integer> sides = getSides(triangle);
        if (isTriangle(sides.get(0), sides.get(1), sides.get(2))) {
          possibleTriangles++;
        }
      }
    }
    System.out.println(possibleTriangles);
  }

  public static List<Integer> getSides(String triangle) {
    return Arrays.stream(triangle.trim().split("\\s+"))
        .map(Integer::parseInt)
        .toList();
  }

  public static boolean isTriangle(int sideA, int sideB, int sideC) {
    return sideA + sideB > sideC && sideB + sideC > sideA && sideC + sideA > sideB;
  }
}
