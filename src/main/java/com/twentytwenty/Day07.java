package com.twentytwenty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * You land at the regional airport in time for your next flight. In fact, it looks like you'll even
 * have time to grab some food: all flights are currently delayed due to issues in luggage
 * processing.
 * <p>
 * Due to recent aviation regulations, many rules (your puzzle input) are being enforced about bags
 * and their contents; bags must be color-coded and must contain specific quantities of other
 * color-coded bags. Apparently, nobody responsible for these regulations considered how long they
 * would take to enforce!
 * <p>
 * For example, consider the following rules:
 * <p>
 * light red bags contain 1 bright white bag, 2 muted yellow bags. dark orange bags contain 3 bright
 * white bags, 4 muted yellow bags. bright white bags contain 1 shiny gold bag. muted yellow bags
 * contain 2 shiny gold bags, 9 faded blue bags. shiny gold bags contain 1 dark olive bag, 2 vibrant
 * plum bags. dark olive bags contain 3 faded blue bags, 4 dotted black bags. vibrant plum bags
 * contain 5 faded blue bags, 6 dotted black bags. faded blue bags contain no other bags. dotted
 * black bags contain no other bags.
 * <p>
 * These rules specify the required contents for 9 bag types. In this example, every faded blue bag
 * is empty, every vibrant plum bag contains 11 bags (5 faded blue and 6 dotted black), and so on.
 * <p>
 * You have a shiny gold bag. If you wanted to carry it in at least one other bag, how many
 * different bag colors would be valid for the outermost bag? (In other words: how many colors can,
 * eventually, contain at least one shiny gold bag?)
 * <p>
 * In the above rules, the following options would be available to you:
 * <p>
 * A bright white bag, which can hold your shiny gold bag directly. A muted yellow bag, which can
 * hold your shiny gold bag directly, plus some other bags. A dark orange bag, which can hold bright
 * white and muted yellow bags, either of which could then hold your shiny gold bag. A light red
 * bag, which can hold bright white and muted yellow bags, either of which could then hold your
 * shiny gold bag.
 * <p>
 * So, in this example, the number of bag colors that can eventually contain at least one shiny gold
 * bag is 4.
 * <p>
 * How many bag colors can eventually contain at least one shiny gold bag? (The list of rules is
 * quite long; make sure you get all of it.)
 */
public class Day07 {

  private static final String LEAST_SHINY_GOLD = "shiny gold bag";
  private static final String SHINY_GOLD = "shiny gold bags";

  public static void main(String[] args) throws IOException {
    int noOfBags = 0;
    try (BufferedReader br = new BufferedReader(new FileReader("inputs/day72020.txt"))) {
      String bags;
      while ((bags = br.readLine()) != null) {
        if (containsShinyGoldBag(bags)) {
          noOfBags += getBagCount(bags);
        }
      }
    }
    System.out.println(noOfBags);
  }

  public static char getBagCount(String bags) {
    return bags.charAt(bags.indexOf(SHINY_GOLD) - 2);
  }

  public static boolean containsShinyGoldBag(String bags) {
    return bags.contains(LEAST_SHINY_GOLD) || bags.contains(SHINY_GOLD);
  }
}

