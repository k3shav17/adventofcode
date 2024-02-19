package com.twentytwenty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Your ferry made decent progress toward the island, but the storm came in faster than anyone
 * expected. The ferry needs to take evasive actions!
 * <p>
 * Unfortunately, the ship's navigation computer seems to be malfunctioning; rather than giving a
 * route directly to safety, it produced extremely circuitous instructions. When the captain uses
 * the PA system to ask if anyone can help, you quickly volunteer.
 * <p>
 * The navigation instructions (your puzzle input) consists of a sequence of single-character
 * actions paired with integer input values. After staring at them for a few minutes, you work out
 * what they probably mean:
 * <p>
 * Action N means to move north by the given value. Action S means to move south by the given value.
 * Action E means to move east by the given value. Action W means to move west by the given value.
 * Action L means to turn left the given number of degrees. Action R means to turn right the given
 * number of degrees. Action F means to move forward by the given value in the direction the ship is
 * currently facing.
 * <p>
 * The ship starts by facing east. Only the L and R actions change the direction the ship is facing.
 * (That is, if the ship is facing east and the next instruction is N10, the ship would move north
 * 10 units, but would still move east if the following action were F.)
 * <p>
 * For example:
 * <p>
 * F10 N3 F7 R90 F11
 * <p>
 * These instructions would be handled as follows:
 * <p>
 * F10 would move the ship 10 units east (because the ship starts by facing east) to east 10, north
 * 0. N3 would move the ship 3 units north to east 10, north 3. F7 would move the ship another 7
 * units east (because the ship is still facing east) to east 17, north 3. R90 would cause the ship
 * to turn right by 90 degrees and face south; it remains at east 17, north 3. F11 would move the
 * ship 11 units south to east 17, south 8.
 * <p>
 * At the end of these instructions, the ship's Manhattan distance (sum of the absolute values of
 * its east/west position and its north/south position) from its starting position is 17 + 8 = 25.
 * <p>
 * Figure out where the navigation instructions lead. What is the Manhattan distance between that
 * location and the ship's starting position?
 */

public class Day12 {

  private static final int NORTH = 0;
  private static final int EAST = 90;
  private static final int SOUTH = 180;
  private static final int WEST = 270;
  private static int direction = 90;
  private static int x = 0;
  private static int y = 0;


  public static void main(String[] args) throws IOException {

    try (BufferedReader br = new BufferedReader(new FileReader("inputs/day122020.txt"))) {
      String navigation;
      while ((navigation = br.readLine()) != null) {
        manhattanDistance(navigation.charAt(0), Integer.parseInt(navigation.substring(1)));
      }
    }

    System.out.println(Math.abs(x) + Math.abs(y));
  }

  public static void manhattanDistance(Character inst, int value) {
    switch (inst) {
      case 'N' -> y -= value;
      case 'S' -> y += value;
      case 'E' -> x += value;
      case 'W' -> x -= value;
      case 'F' -> moveForward(value);
      case 'L' -> changeDirection(-value);
      case 'R' -> changeDirection(value);
      default -> {
        //
      }
    }
  }

  private static void changeDirection(int value) {
    direction += value;

    if (direction < 0) {
      direction += 360;
    }
    if (direction > 359) {
      direction -= 360;
    }
  }

  private static void moveForward(int value) {
    switch (direction) {
      case NORTH -> y -= value;
      case SOUTH -> y += value;
      case EAST -> x += value;
      case WEST -> x -= value;
      default -> {
        //
      }
    }
  }

}
