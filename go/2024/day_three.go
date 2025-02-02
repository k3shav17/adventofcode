package main

import (
	"bufio"
	"fmt"
	"os"
	"regexp"
	"strconv"
	"strings"
)

func main() {

	corruptInsts := readInput()
	fmt.Println(parseInstructions(corruptInsts))
}

func readInput() string {

	file, err := os.Open("/Users/keshava.kommaraju/Personal/practice/adventofcode/java/inputs/2024/day_03.txt")

	if err != nil {
		fmt.Println("Error reading the input file ", err)
		return ""
	}
	defer file.Close()

	reader := bufio.NewReader(file)
	data, err := reader.ReadString('\n')
	return data

}

func parseInstructions(corruptInstructions string) int32 {

	var productOfInstructions int32 = 0

	validInstructRegex := "mul\\(\\d+,\\d+\\)"
	regCompiler := regexp.MustCompile(validInstructRegex)
	matches := regCompiler.FindAllString(corruptInstructions, -1)

	for _, match := range matches {
		productOfInstructions += parseMatcher(match)

	}
	return productOfInstructions
}

func parseMatcher(match string) int32 {
	strs := strings.Split(match[4:len(match)-1], ",")
	first, _ := strconv.Atoi(strs[0])
	second, _ := strconv.Atoi(strs[1])
	return int32(first) * int32(second)
}