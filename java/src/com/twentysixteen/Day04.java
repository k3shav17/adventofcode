package com.twentysixteen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
 * <p>
 * <p>
 * --- Part Two ---
 * <p>
 * With all the decoy data out of the way, it's time to decrypt this list and get moving.
 * <p>
 * The room names are encrypted by a state-of-the-art shift cipher, which is nearly unbreakable
 * without the right software. However, the information kiosk designers at Easter Bunny HQ were not
 * expecting to deal with a master cryptographer like yourself.
 * <p>
 * To decrypt a room name, rotate each letter forward through the alphabet a number of times equal
 * to the room's sector ID. A becomes B, B becomes C, Z becomes A, and so on. Dashes become spaces.
 * <p>
 * For example, the real name for qzmt-zixmtkozy-ivhz-343 is very encrypted name.
 * <p>
 * What is the sector ID of the room where North Pole objects are stored?
 */
public class Day04 {

  private static int summationOfSectors = 0;

  public static void main(String[] args) throws IOException {
    partOne(getInstructions());
    partTwo(getInstructions());
  }

  public static List<String> getInstructions() throws IOException {
    List<String> input = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("inputs/day42016.txt"))) {
      String instructions;
      while ((instructions = br.readLine()) != null) {
        input.add(instructions);
      }
    }
    return input;
  }

  public static void partOne(List<String> instructions) {
    for (String instruction : instructions) {
      if (isRealRoom(instruction)) {
        int sectorId = extractSectorId(instruction);
        summationOfSectors += sectorId;
      }
    }
    System.out.println(summationOfSectors);
  }

  public static void partTwo(List<String> instructions) {
    for (String instruction : instructions) {
      if (isRealRoom(instruction) && getRealRoomName(instruction).startsWith("north")) {
        System.out.println(extractSectorId(instruction));
        break;
      }
    }
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

    for (Entry<Character, Integer> entries : charList) {
      sb.append(entries.getKey());
    }
    return sb.toString();
  }

  public static String getRealRoomName(String encrypted) {
    char[] alphabet = new char[26];

    int index = 0;
    for (int i = 97; i < 123; i++) {
      alphabet[index++] = (char) i;
    }

    String instructions = encrypted.substring(0, encrypted.length() - 10);
    StringBuilder encryptedCode = new StringBuilder();
    for (Character c : instructions.toCharArray()) {
      if (c == '-') {
        encryptedCode.append(" ");
      } else {
        encryptedCode.append(c);
      }
    }

    StringBuilder decryptedName = new StringBuilder();
    int sector = extractSectorId(encrypted);
    for (Character c : encryptedCode.toString().toCharArray()) {
      if (c == ' ') {
        decryptedName.append(" ");
      } else {
        decryptedName.append(alphabet[Math.abs(c - 'a' + sector) % 26]);
      }
    }
    return decryptedName.toString();
  }
}
