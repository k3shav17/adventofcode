package com.twentytwenty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Your flight to the major airline hub reaches cruising altitude without incident. While you
 * consider checking the in-flight menu for one of those drinks that come with a little umbrella,
 * you are interrupted by the kid sitting next to you.
 * <p>
 * Their handheld game console won't turn on! They ask if you can take a look.
 * <p>
 * You narrow the problem down to a strange infinite loop in the boot code (your puzzle input) of
 * the device. You should be able to fix it, but first you need to be able to run the code in
 * isolation.
 * <p>
 * The boot code is represented as a text file with one instruction per line of text. Each
 * instruction consists of an operation (acc, jmp, or nop) and an argument (a signed number like +4
 * or -20).
 * <p>
 * acc increases or decreases a single global value called the accumulator by the value given in the
 * argument. For example, acc +7 would increase the accumulator by 7. The accumulator starts at 0.
 * After an acc instruction, the instruction immediately below it is executed next. jmp jumps to a
 * new instruction relative to itself. The next instruction to execute is found using the argument
 * as an offset from the jmp instruction; for example, jmp +2 would skip the next instruction, jmp
 * +1 would continue to the instruction immediately below it, and jmp -20 would cause the
 * instruction 20 lines above to be executed next. nop stands for No OPeration - it does nothing.
 * The instruction immediately below it is executed next.
 * <p>
 * For example, consider the following program:
 * <p>
 * nop +0 acc +1 jmp +4 acc +3 jmp -3 acc -99 acc +1 jmp -4 acc +6
 * <p>
 * These instructions are visited in this order:
 * <p>
 * nop +0  | 1 acc +1  | 2, 8(!) jmp +4  | 3 acc +3  | 6 jmp -3  | 7 acc -99 | acc +1  | 4 jmp -4  |
 * 5 acc +6  |
 * <p>
 * First, the nop +0 does nothing. Then, the accumulator is increased from 0 to 1 (acc +1) and jmp
 * +4 sets the next instruction to the other acc +1 near the bottom. After it increases the
 * accumulator from 1 to 2, jmp -4 executes, setting the next instruction to the only acc +3. It
 * sets the accumulator to 5, and jmp -3 causes the program to continue back at the first acc +1.
 * <p>
 * This is an infinite loop: with this sequence of jumps, the program will run forever. The moment
 * the program tries to run any instruction a second time, you know it will never terminate.
 * <p>
 * Immediately before the program would run an instruction a second time, the value in the
 * accumulator is 5.
 * <p>
 * Run your copy of the boot code. Immediately before any instruction is executed a second time,
 * what value is in the accumulator?
 */
public class Day08 {

  private static int ACCUMULATOR = 0;
  private static final String ACC = "acc";
  private static final String NO_OP = "nop";
  private static final String JUMP = "jmp";
  private static final Set<String> alreadyRanInstruction = new HashSet<>();
  private static final List<String> instructionList = new ArrayList<>();


  public static void main(String[] args) throws IOException {

    try (BufferedReader br = new BufferedReader(new FileReader("inputs/day82020.txt"))) {

      String instruction;

      while ((instruction = br.readLine()) != null) {
        String command = extractInstruction(instruction);
        int value = extractInstructionValue(instruction);

        if (command.equals(JUMP) && value > 0) {
          instructionList.add(instruction);
          while (value > 0) {
            instruction = br.readLine();
            instructionList.add(instruction);
            value--;
          }
        }
        command = extractInstruction(instruction);

        if (performCommands(command, instruction, value)) {
          break;
        }
      }
    }
    System.out.println(ACCUMULATOR);
  }

  private static boolean performCommands(String command, String instruction, int value) {

    if (alreadyRanInstruction.contains(instruction)) {
      return true;
    }

    if (command.equals(ACC)) {
      ACCUMULATOR += value;
    }

    if (command.equals(JUMP) && value < 0) {
      String instructionAfterJump = instructionList.get(instructionList.size() - 1 + value);

      while (value < 0) {
        performCommands(extractInstruction(instructionAfterJump), instructionAfterJump,
            extractInstructionValue(instructionAfterJump));
        if (alreadyRanInstruction.contains(instructionAfterJump)) {
          return true;
        }
        value++;
      }
    }

    alreadyRanInstruction.add(instruction);
    instructionList.add(instruction);
    return false;
  }

  private static int extractInstructionValue(String instruction) {
    return Integer.parseInt(instruction.split(" ")[1]);
  }

  private static String extractInstruction(String instruction) {
    return instruction.split(" ")[0];
  }
}
