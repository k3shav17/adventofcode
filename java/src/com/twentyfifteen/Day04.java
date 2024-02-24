package com.twentyfifteen;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * --- Day 4: The Ideal Stocking Stuffer ---
 * <p>
 * Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts for all the
 * economically forward-thinking little girls and boys.
 * <p>
 * To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five zeroes.
 * The input to the MD5 hash is some secret key (your puzzle input, given below) followed by a
 * number in decimal. To mine AdventCoins, you must find Santa the lowest positive number (no
 * leading zeroes: 1, 2, 3, ...) that produces such a hash.
 * <p>
 * For example:
 * <p>
 * If your secret key is abcdef, the answer is 609043, because the MD5 hash of abcdef609043 starts
 * with five zeroes (000001dbbfa...), and it is the lowest such number to do so. If your secret key
 * is pqrstuv, the lowest number it combines with to make an MD5 hash starting with five zeroes is
 * 1048970; that is, the MD5 hash of pqrstuv1048970 looks like 000006136ef....
 * <p>
 * Your puzzle input is bgvyzdsv.
 */
public class Day04 {

  public static void main(String[] args) {

    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      String secretKey = "bgvyzdsv";

      int i = 0;
      do {
        md5.update((secretKey + i).getBytes());
        byte[] messageBytes = md5.digest();

        // part 1
//        if (messageBytes[0] == 0 && messageBytes[1] == 0 && (messageBytes[2] & 0xFF) < 0x10) {

        // part 2
        if (messageBytes[0] == 0 && messageBytes[1] == 0 && messageBytes[2] == 0) {
          System.out.println(i);
          break;
        }
        ++i;
      } while (true);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }
}
