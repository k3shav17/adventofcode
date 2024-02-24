package com.twentysixteen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 7: Internet Protocol Version 7 ---
 * <p>
 * While snooping around the local network of EBHQ, you compile a list of IP addresses (they're
 * IPv7, of course; IPv6 is much too limited). You'd like to figure out which IPs support TLS
 * (transport-layer snooping).
 * <p>
 * An IP supports TLS if it has an Autonomous Bridge Bypass Annotation, or ABBA. An ABBA is any
 * four-character sequence which consists of a pair of two different characters followed by the
 * reverse of that pair, such as xyyx or abba. However, the IP also must not have an ABBA within any
 * hypernet sequences, which are contained by square brackets.
 * <p>
 * For example:
 * <p>
 * abba[mnop]qrst supports TLS (abba outside square brackets). abcd[bddb]xyyx does not support TLS
 * (bddb is within square brackets, even though xyyx is outside square brackets). aaaa[qwer]tyui
 * does not support TLS (aaaa is invalid; the interior characters must be different).
 * ioxxoj[asdfgh]zxcvbn supports TLS (oxxo is outside square brackets, even though it's within a
 * larger string).
 * <p>
 * How many IPs in your puzzle input support TLS?
 */
public class Day07 {

  public static void main(String[] args) throws IOException {
    partOne(getInput());
  }

  public static List<String> getInput() throws IOException {

    List<String> input = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("inputs/day72016.txt"))) {
      String protocol;
      while ((protocol = br.readLine()) != null) {
        input.add(protocol);
      }
    }
    return input;
  }

  public static void partOne(List<String> inputInstructions) {

    int supportsTLS = 0;
    for (String protocol : inputInstructions) {
      if (hasTLSSupport(protocol)) {
        supportsTLS++;
      }
    }
    System.out.println(supportsTLS);
  }

  public static boolean hasTLSSupport(String protocol) {

    for (int i = 0; i < protocol.length() - 4; i++) {
      String abba = protocol.substring(i, i + 4);
      if (isPairOfTwo(abba) && isPalindrome(abba)) {
        if (!inHyperNet(abba, protocol)) {
          return true;
        }
        if (inHyperNet(abba, protocol)) {
          return false;
        }
      }
    }
    return false;
  }

  public static boolean inHyperNet(String abba, String protocol) {
    int hypernetStart = -1;
    int hypernetEnd = -1;

    for (int i = 0; i < protocol.length(); i++) {
      if (protocol.charAt(i) == '[') {
        hypernetStart = i;
      }
      if (hypernetStart != -1 && protocol.charAt(i) == ']') {
        hypernetEnd = i;
        break;
      }
    }

    if (hypernetStart != -1 && hypernetEnd != -1) {
      String hypernetSequence = protocol.substring(hypernetStart + 1, hypernetEnd);
      return hypernetSequence.contains(abba);
    }

    return false;
  }

  public static boolean isPairOfTwo(String abba) {
    return abba.chars().distinct().count() == 2;
  }

  public static boolean isPalindrome(String abba) {
    for (int i = 0; i < abba.length() / 2; i++) {
      if (abba.charAt(i) != abba.charAt(abba.length() - 1 - i)) {
        return false;
      }
    }
    return true;
  }
}
