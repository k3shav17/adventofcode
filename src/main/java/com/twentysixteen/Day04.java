package com.twentysixteen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * --- Day 4: Security Through Obscurity ---
 * <p>
 * Finally, you come across an information kiosk with a list of rooms. Of course, the list is
 * encrypted and full of decoy data, but the instructions to decode the list are barely hidden
 * nearby. Better remove the decoy data first.
 * <p>
 * Each room consists of an encrypted name (lowercase letters separated by dashes) followed by a
 * dash, a sector ID, and a checksum in square brackets.
 * <p>
 * A room is real (not a decoy) if the checksum is the five most common letters in the encrypted
 * name, in order, with ties broken by alphabetization. For example:
 * <p>
 * aaaaa-bbb-z-y-x-123[abxyz] is a real room because the most common letters are a (5), b (3), and
 * then a tie between x, y, and z, which are listed alphabetically. a-b-c-d-e-f-g-h-987[abcde] is a
 * real room because although the letters are all tied (1 of each), the first five are listed
 * alphabetically. not-a-real-room-404[oarel] is a real room. totally-real-room-200[decoy] is not.
 * <p>
 * Of the real rooms from the list above, the sum of their sector IDs is 1514.
 * <p>
 * What is the sum of the sector IDs of the real rooms?
 */
public class Day04 {

  private static int summationOfSectors = 0;

  public static void main(String[] args) throws IOException {
    partOne();
  }

  public static void partOne() throws IOException {

    try (BufferedReader br = new BufferedReader(new FileReader("inputs/day42016.txt"))) {
      String instructions;

      while ((instructions = br.readLine()) != null) {
        if (isRealRoom(instructions)) {
          int sectorId = extractSectorId(instructions);
          summationOfSectors += sectorId;
        }
      }
    }
    System.out.println(summationOfSectors);
  }


  public static int extractSectorId(String sectorDetails) {
    int sectorLength = sectorDetails.length();
    return Integer.parseInt(sectorDetails.substring(sectorLength - 10, sectorLength - 7));
  }

  public static boolean isRealRoom(String instructions) {
    int instructionLength = instructions.length();
    String realRoomCode = instructions.substring(instructionLength - 6, instructionLength - 1);
    return getRoomCode(instructions, instructionLength).contains(realRoomCode);
  }

  public static String getRoomCode(String instructions, int instructionLength) {
    Map<Character, Integer> repeatedChars = new HashMap<>();
    String encryptedRoomCode = instructions.substring(0, instructionLength - 10);

    for (Character c : encryptedRoomCode.toCharArray()) {
      if (c != '-') {
        repeatedChars.put(c, repeatedChars.getOrDefault(c, 0) + 1);
      }
    }

    List<Entry<Character, Integer>> charList = repeatedChars.entrySet().stream()
        .sorted(Entry.<Character, Integer>comparingByValue().reversed()
            .thenComparing(Entry.comparingByKey())).toList();

    StringBuilder sb = new StringBuilder();

    for (Map.Entry<Character, Integer> entries : charList) {
      sb.append(entries.getKey());
    }
    return sb.toString();
  }
}
