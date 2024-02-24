package com.twentytwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ResumeFiller {

  private static final StringBuilder resumeBuilder = new StringBuilder();

  public static void main(String[] args) throws IOException {

    try (BufferedReader br = new BufferedReader(new FileReader("inputs/resume.txt"))) {

      String detail;

      while ((detail = br.readLine()) != null) {
        if (detail.trim().isBlank()) {
          continue;
        }
        if (detail.contains("firstname")) {
          buildResume("first name", detail.trim().split(":")[1]);
        }

        if (detail.contains("lastname")) {
          buildResume("last name", detail.trim().split(":")[1]);
        }

        if (detail.contains("work experience")) {
          buildResume("experience", detail.trim().split(":")[1]);
        }

        if (detail.contains("education")) {
          buildResume("Education", detail.trim().split(":")[1]);
        }
      }
    }

    System.out.println(resumeBuilder);
  }

  public static void buildResume(String section, String description) {
    resumeBuilder.append(section).append(" - ").append(description).append("\n");
  }
}
