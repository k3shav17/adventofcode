package com.twentytwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// A - Rock - X  - Score - 1
// B - Paper - Y - Score - 2
// C - Scissor - Z - Score - 3
// win 6, lose 0, draw 3
public class Day02 {

  static final int ROCK = 1;
  static final int PAPER = 2;
  static final int SCISSORS = 3;
  static final int LOSS = 0;
  static final int WIN = 6;
  static final int DRAW = 3;

  public static void main(String[] args) throws IOException {

    int score = 0;
    Map<String, Integer> shapes = new HashMap<>();
    shapes.put("A", ROCK);
    shapes.put("B", PAPER);
    shapes.put("C", SCISSORS);
    shapes.put("X", ROCK);
    shapes.put("Y", PAPER);
    shapes.put("Z", SCISSORS);

    try (BufferedReader br = new BufferedReader(new FileReader("inputs/2022/day2.txt"))) {

      String line;
      while ((line = br.readLine()) != null) {
        String[] match = line.trim().split(" ");

        if (match.length != 2) {
          continue;
        }

        if (Objects.equals(shapes.get(match[0]), shapes.get(match[1]))) {
          score += DRAW;
        } else if (shapes.get(match[0]) == 1 && shapes.get(match[1]) == 2) {
          score += WIN;
        } else if (shapes.get(match[0]) == 2 && shapes.get(match[1]) == 3) {
          score += WIN;
        } else if (shapes.get(match[0]) == 3 && shapes.get(match[1]) == 1) {
          score += WIN;
        } else {
          score += LOSS;
        }
        score += shapes.get(match[1]);
      }
    }
    System.out.println(score);
  }
}
