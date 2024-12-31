package com.twentyfour;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.stream.events.StartDocument;

/**
 * "Our computers are having issues, so I have no idea if we have any Chief Historians in stock!
 * You're welcome to check the warehouse, though," says the mildly flustered shopkeeper at the North
 * Pole Toboggan Rental Shop. The Historians head out to take a look.
 * <p>
 * The shopkeeper turns to you. "Any chance you can see why our computers are having issues again?"
 * <p>
 * The computer appears to be trying to run a program, but its memory (your puzzle input) is
 * corrupted. All of the instructions have been jumbled up!
 * <p>
 * It seems like the goal of the program is just to multiply some numbers. It does that with
 * instructions like mul(X,Y), where X and Y are each 1-3 digit numbers. For instance, mul(44,46)
 * multiplies 44 by 46 to get a result of 2024. Similarly, mul(123,4) would multiply 123 by 4.
 * <p>
 * However, because the program's memory has been corrupted, there are also many invalid characters
 * that should be ignored, even if they look like part of a mul instruction. Sequences like mul(4*,
 * mul(6,9!, ?(12,34), or mul ( 2 , 4 ) do nothing.
 * <p>
 * For example, consider the following section of corrupted memory:
 * <p>
 * xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
 * <p>
 * Only the four highlighted sections are real mul instructions. Adding up the result of each
 * instruction produces 161 (2*4 + 5*5 + 11*8 + 8*5).
 * <p>
 * Scan the corrupted memory for uncorrupted mul instructions. What do you get if you add up all  *
 * the results of the multiplications?
 */
public class DayThree {

    public static void main(String[] args) throws IOException {
        String corruptedInstructions = Files.readString(Paths.get("java/inputs/2024/day_03.txt"));
        System.out.println(partOne(corruptedInstructions));
        partTwo(corruptedInstructions);
    }

    private static void partTwo(String corruptedInstructions) {

        int firstDoIndex = findFirstDo(corruptedInstructions);
        long result = operateTillFirstDo(corruptedInstructions.substring(0, firstDoIndex));
        String corruptInstructionChunk;

        for (int i = firstDoIndex; i < corruptedInstructions.length(); i++) {
            if (corruptedInstructions.charAt(i) == 'd' && corruptedInstructions.charAt(i + 1) == 'o'
                && corruptedInstructions.charAt(i + 2) == '('
                && corruptedInstructions.charAt(i + 3) == ')') {
                int startOfDoNot = findDoNot(corruptedInstructions, i + 3);
                if (startOfDoNot == -1) {
                    startOfDoNot = corruptedInstructions.length();
                }
                corruptInstructionChunk = corruptedInstructions.substring(i + 4, startOfDoNot);
                result += partOne(corruptInstructionChunk);
            }
        }
        System.out.println(result);
    }

    private static int findFirstDo(String instructions) {
        int tillFirstDo = 0;
        for (int i = 0; i < instructions.length() - 3; i++) {
            if (instructions.charAt(i) == 'd' && instructions.charAt(i + 1) == 'o'
                && instructions.charAt(i + 2) == '('
                && instructions.charAt(i + 3) == ')') {
                tillFirstDo = i;
                break;
            }
        }
        return tillFirstDo;
    }

    private static long operateTillFirstDo(String instructions) {
        return partOne(instructions);
    }

    private static int findDoNot(String instructions, int endOfDo) {
        return instructions.indexOf("don't()", endOfDo);
    }

    private static long partOne(String corruptedInstructions) {
        long result = 0L;
        for (int i = 0; i < corruptedInstructions.length() - 3; i++) {
            if (corruptedInstructions.charAt(i) == 'm' && corruptedInstructions.charAt(i + 1) == 'u'
                && corruptedInstructions.charAt(i + 2) == 'l'
                && corruptedInstructions.charAt(i + 3) == '(') {
                result += findClosingIndexAndOperate(corruptedInstructions, i + 3);
            }
        }
        return result;
    }

    private static long findClosingIndexAndOperate(String instructions, int openIndex) {
        int closeIndex = instructions.indexOf(')', openIndex);
        if (closeIndex == -1) {
            return 0L;
        }
        String operationVals = instructions.substring(openIndex, closeIndex);
        String actualValues = operationVals.substring(1);
        return isInvalidInstruction(actualValues) ? 0L : performOperation(actualValues);
    }

    private static boolean isInvalidInstruction(String instruction) {
        return instruction.contains("(") || instruction.contains("/");
    }

    private static long performOperation(String vals) {
        String[] values = vals.split(",");
        long result = 1;
        for (String value : values) {
            result *= Long.parseLong(value);
        }
        return result;
    }
}
