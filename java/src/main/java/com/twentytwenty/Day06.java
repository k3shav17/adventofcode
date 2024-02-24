package com.twentytwenty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * As your flight approaches the regional airport where you'll switch to a much larger plane,
 * customs declaration forms are distributed to the passengers.
 * <p>
 * The form asks a series of 26 yes-or-no questions marked a through z. All you need to do is
 * identify the questions for which anyone in your group answers "yes". Since your group is just
 * you, this doesn't take very long.
 * <p>
 * However, the person sitting next to you seems to be experiencing a language barrier and asks if
 * you can help. For each of the people in their group, you write down the questions for which they
 * answer "yes", one per line. For example:
 * <p>
 * abcx abcy abcz
 * <p>
 * In this group, there are 6 questions to which anyone answered "yes": a, b, c, x, y, and z.
 * (Duplicate answers to the same question don't count extra; each question counts at most once.)
 * <p>
 * Another group asks for your help, then another, and eventually you've collected answers from
 * every group on the plane (your puzzle input). Each group's answers are separated by a blank line,
 * and within each group, each person's answers are on a single line. For example:
 * <p>
 * abc
 * <p>
 * a b c
 * <p>
 * ab ac
 * <p>
 * a a a a
 * <p>
 * b
 * <p>
 * This list represents answers from five groups:
 * <p>
 * The first group contains one person who answered "yes" to 3 questions: a, b, and c. The second
 * group contains three people; combined, they answered "yes" to 3 questions: a, b, and c. The third
 * group contains two people; combined, they answered "yes" to 3 questions: a, b, and c. The fourth
 * group contains four people; combined, they answered "yes" to only 1 question, a. The last group
 * contains one person who answered "yes" to only 1 question, b.
 * <p>
 * In this example, the sum of these counts is 3 + 3 + 3 + 1 + 1 = 11.
 * <p>
 * For each group, count the number of questions to which anyone answered "yes". What is the sum of
 * those counts?
 */
public class Day06 {

  public static void main(String[] args) throws IOException {

    int yesCounter = 0;
    StringBuilder group = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader("inputs/day062020.txt"))) {
      String selection;
      while ((selection = br.readLine()) != null) {
        if (selection.trim().isBlank()) {
          yesCounter += noOfYesByAGroup(group.toString());
          group.setLength(0);
        } else {
          group.append(selection);
        }
      }
      yesCounter += noOfYesByAGroup(group.toString());
      System.out.println(yesCounter);
    }

  }

  public static int noOfYesByAGroup(String group) {
    return (int) group.chars().distinct().count();
  }
}
