package com.twentytwenty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * You board your plane only to discover a new problem: you dropped your boarding pass! You aren't
 * sure which seat is yours, and all of the flight attendants are busy with the flood of people that
 * suddenly made it through passport control.
 * <p>
 * You write a quick program to use your phone's camera to scan all of the nearby boarding passes
 * (your puzzle input); perhaps you can find your seat through process of elimination.
 * <p>
 * Instead of zones or groups, this airline uses binary space partitioning to seat people. A seat
 * might be specified like FBFBBFFRLR, where F means "front", B means "back", L means "left", and R
 * means "right".
 * <p>
 * The first 7 characters will either be F or B; these specify exactly one of the 128 rows on the
 * plane (numbered 0 through 127). Each letter tells you which half of a region the given seat is
 * in. Start with the whole list of rows; the first letter indicates whether the seat is in the
 * front (0 through 63) or the back (64 through 127). The next letter indicates which half of that
 * region the seat is in, and so on until you're left with exactly one row.
 * <p>
 * For example, consider just the first seven characters of FBFBBFFRLR:
 * <p>
 * Start by considering the whole range, rows 0 through 127. F means to take the lower half, keeping
 * rows 0 through 63. B means to take the upper half, keeping rows 32 through 63. F means to take
 * the lower half, keeping rows 32 through 47. B means to take the upper half, keeping rows 40
 * through 47. B keeps rows 44 through 47. F keeps rows 44 through 45. The final F keeps the lower
 * of the two, row 44.
 * <p>
 * The last three characters will be either L or R; these specify exactly one of the 8 columns of
 * seats on the plane (numbered 0 through 7). The same process as above proceeds again, this time
 * with only three steps. L means to keep the lower half, while R means to keep the upper half.
 * <p>
 * For example, consider just the last 3 characters of FBFBBFFRLR:
 * <p>
 * Start by considering the whole range, columns 0 through 7. R means to take the upper half,
 * keeping columns 4 through 7. L means to take the lower half, keeping columns 4 through 5. The
 * final R keeps the upper of the two, column 5.
 * <p>
 * So, decoding FBFBBFFRLR reveals that it is the seat at row 44, column 5.
 * <p>
 * Every seat also has a unique seat ID: multiply the row by 8, then add the column. In this
 * example, the seat has ID 44 * 8 + 5 = 357.
 * <p>
 * Here are some other boarding passes:
 * <p>
 * BFFFBBFRRR: row 70, column 7, seat ID 567. FFFBBBFRRR: row 14, column 7, seat ID 119. BBFFBBFRLL:
 * row 102, column 4, seat ID 820.
 * <p>
 * As a sanity check, look through your list of boarding passes. What is the highest seat ID on a
 * boarding pass?
 */
public class Day05 {

  public static void main(String[] args) throws IOException {
    int heighestSeatId = 0;

    try (BufferedReader br = new BufferedReader(new FileReader("inputs/day052020.txt"))) {
      String seat;
      while ((seat = br.readLine()) != null) {
        int row = getRow(seat.substring(0, 8));
        int col = getColumn(seat.substring(7));
        heighestSeatId = Math.max(getSeatId(row, col), heighestSeatId);
      }
    }
    System.out.println(heighestSeatId);

  }

  public static int getRow(String codeForRow) {
    int lower = 0, upper = 127;
    int charCounter = 0;
    int midpoint = (upper + lower) / 2;

    while (charCounter <= 7) {
      midpoint = (upper + lower) / 2;
      if (codeForRow.charAt(charCounter) == 'F') {
        upper = midpoint;
      }
      if (codeForRow.charAt(charCounter) == 'B') {
        lower = midpoint + 1;
      }
      charCounter++;
    }

    return midpoint;
  }

  public static int getColumn(String codeForColumn) {

    if (codeForColumn.equals("RRR")) {
      return 7;
    }
    int lower = 0, upper = 7;
    int charCounter = 0;
    int midpoint = (int) Math.ceil((double) (upper + lower) / 2);

    while (charCounter <= 2) {
      midpoint = (int) Math.ceil((double) (upper + lower) / 2);

      if (codeForColumn.charAt(charCounter) == 'L') {
        upper = midpoint;
      }
      if (codeForColumn.charAt(charCounter) == 'R') {
        lower = midpoint - 1;
      }
      charCounter++;
    }
    return midpoint;
  }

  public static int getSeatId(int row, int column) {
    return row * 8 + column;
  }
}
