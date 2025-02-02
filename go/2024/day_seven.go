package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	fmt.Println(bridgeCalibrationInput())
}

func bridgeCalibrationInput() int64 {

	file, err := os.Open("/Users/keshava.kommaraju/Personal/practice/adventofcode/java/inputs/2024/day_07.txt")
	var calibrationResult int64 = 0

	if err != nil {
		fmt.Println("Error reading the mentioned file, ", err)
		return 0
	}

	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		line := strings.Fields(scanner.Text())
		calibrationResult += resultAndOperands(line)
	}

	return calibrationResult
}

func resultAndOperands(line []string) int64 {

	result, _ := strconv.Atoi(line[0])
	operandsStr := line[1:]
	operands := strToInt(operandsStr)
	var accumulator int64 = operands[0]

	if canCompute(operands, 1, accumulator, int64(result)) {
		return int64(result)
	}
	return 0
}

func canCompute(operands []int64, index int, accumulator, result int64) bool {

	if len(operands) == index {
		return result == accumulator
	}

	additive := accumulator + operands[index]
	multiplicative := accumulator * operands[index]
	or := parsePipe(accumulator, operands[index])

	index = index + 1

	isAdditive := canCompute(operands, index, additive, result)
	isMultiplicative := canCompute(operands, index, multiplicative, result)
	isOr := canCompute(operands, index, or, result)

	return isAdditive || isMultiplicative || isOr

}

func strToInt(strs []string) []int64 {

	operands := make([]int64, len(strs))

	for idx, val := range strs {
		operand, _ := strconv.Atoi(val)
		operands[idx] = int64(operand)
	}

	return operands
}

func parsePipe(accumulator, current int64) int64 {
	parsedInt, _ := strconv.Atoi(fmt.Sprintf("%s%s", accumulator, current))
	if parsedInt != 0 {
		fmt.Println(parsedInt)
	}
	return int64(parsedInt)
}