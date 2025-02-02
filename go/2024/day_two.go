package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

func main() {

	reactorLevels := readInput()
	partOne(reactorLevels)
}

func readInput() [][]int {
	var reactorReport [][]int

	file, err := os.Open("/home/keshavak/repos/adventofcode/java/inputs/2024/day_02.txt")
	if err != nil {
		fmt.Println("Error reading the input file ", err)
		return nil
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		levels := strings.Fields(scanner.Text())
		reactorReport = append(reactorReport, strToIntArr(levels))
	}
	return reactorReport
}

func strToIntArr(levels []string) []int {
	int_arr := make([]int, len(levels))

	for idx, val := range levels {
		int_val, _ := strconv.Atoi(val)
		int_arr[idx] = int_val
	}
	return int_arr
}

func partOne(reactorReport [][]int) {
	var safeReports [][]int
	var offByOneSafeReports [][]int

	for _, val := range reactorReport {
		if isIncreasing(val) || isDecreasing(val) {
			safeReports = append(safeReports, val)
		} else {
			if isSafeReportBarringOneElement(val) {
				offByOneSafeReports = append(offByOneSafeReports, val)
			}
		}
	}

	fmt.Printf("safe reports -> %s\n", len(safeReports))
	fmt.Printf("safe reports by removing one element -> %s\n", len(offByOneSafeReports))
	fmt.Printf("total safe reports -> %s\n", (len(safeReports) + len(offByOneSafeReports)))

}

func isSafeReportBarringOneElement(report []int) bool {
	for i := 0; i < len(report); i++ {
		temp_slice := append([]int(nil), report...)
		temp_slice = append(temp_slice[:i], temp_slice[i+1:]...)
		if isIncreasing(temp_slice) || isDecreasing(temp_slice) {
			return true
		}
	}
	return false
}

func isIncreasing(report []int) bool {
	for i := 1; i < len(report); i++ {
		current, previous := report[i], report[i-1]
		if current < previous || isNotInThreshold(current, previous) {
			return false
		}
	}
	return true
}

func isDecreasing(report []int) bool {
	for i := 1; i < len(report); i++ {
		current, previous := report[i], report[i-1]
		if current > previous || isNotInThreshold(current, previous) {
			return false
		}
	}
	return true
}

func isNotInThreshold(current, previous int) bool {
	diff := math.Abs(float64(previous - current))
	return diff < 1 || diff > 3
}
