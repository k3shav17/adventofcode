package com.twentytwenty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * You arrive at the airport only to realize that you grabbed your North Pole Credentials instead of
 * your passport. While these documents are extremely similar, North Pole Credentials aren't issued
 * by a country and therefore aren't actually valid documentation for travel in most of the world.
 * <p>
 * It seems like you're not the only one having problems, though; a very long line has formed for
 * the automatic passport scanners, and the delay could upset your travel itinerary.
 * <p>
 * Due to some questionable network security, you realize you might be able to solve both of these
 * problems at the same time.
 * <p>
 * The automatic passport scanners are slow because they're having trouble detecting which passports
 * have all required fields. The expected fields are as follows:
 * <p>
 * byr (Birth Year) iyr (Issue Year) eyr (Expiration Year) hgt (Height) hcl (Hair Color) ecl (Eye
 * Color) pid (Passport ID) cid (Country ID)
 * <p>
 * Passport data is validated in batch files (your puzzle input). Each passport is represented as a
 * sequence of key:value pairs separated by spaces or newlines. Passports are separated by blank
 * lines.
 * <p>
 * Here is an example batch file containing four passports:
 * <p>
 * ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm
 * <p>
 * iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929
 * <p>
 * hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm
 * <p>
 * hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in
 * <p>
 * The first passport is valid - all eight fields are present. The second passport is invalid - it
 * is missing hgt (the Height field).
 * <p>
 * The third passport is interesting; the only missing field is cid, so it looks like data from
 * North Pole Credentials, not a passport at all! Surely, nobody would mind if you made the system
 * temporarily ignore missing cid fields. Treat this "passport" as valid.
 * <p>
 * The fourth passport is missing two fields, cid and byr. Missing cid is fine, but missing any
 * other field is not, so this passport is invalid.
 * <p>
 * According to the above rules, your improved system would report 2 valid passports.
 * <p>
 * Count the number of valid passports - those that have all required fields. Treat cid as optional.
 * In your batch file, how many passports are valid?
 */

public class Day04 {

  private static final Set<String> passportDetailsSet = new HashSet<>();

  static {
    passportDetailsSet.add("ecl");
    passportDetailsSet.add("pid");
    passportDetailsSet.add("eyr");
    passportDetailsSet.add("hcl");
    passportDetailsSet.add("byr");
    passportDetailsSet.add("iyr");
    passportDetailsSet.add("hgt");
  }

  public static void main(String[] args) throws IOException {

    int validCounter = 0;

    try (BufferedReader br = new BufferedReader(new FileReader("inputs/day042020.txt"))) {
      String passport;
      while ((passport = br.readLine()) != null) {
        if (passport.trim().isBlank()) {
          continue;
        }
        if (isValid(passport)) {
          validCounter++;
        }
      }
    }
    System.out.println(validCounter);
  }

  public static boolean isValid(String passport) {

    String[] details = passport.split("\\s+");
    if (details.length < 7) {
      return false;
    }
    return new HashSet<>(Arrays.stream(details).map(d -> d.substring(0, 3)).toList()).containsAll(
        passportDetailsSet);
  }
}

