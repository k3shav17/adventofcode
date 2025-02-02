/**
* Throughout the Chief's office, the historically significant locations are listed not by name but
* by a unique number called the location ID. To make sure they don't miss a hin The sto ns split
* into two groups, each searching the office and trying to create their own complete list of
* <p>
* location IDs. There's just one problem: by holding the two lists up side y side (your puzzle
* <p>
* input), it quickly becomes clear that the lists aren't very similar. Maybe you can help The
* Historians reconcile their lists?
*
* <p>
* <p>
* For example:
*
* <p>
* 3 4 4 3 2 5 1 3 3 9 3 3
* <p>
* Maybe the lists are only off by a small amount! To find out, pair up the
* <p>
* numbers and measure how
* <p>
* far apart they are. Pair up the smallest number in the left list with the
* <p>
* smallest number in the
* <p>
* right list, then the second-smallest left number with the second-smallest
* <p>
* right number, and so
* <p>
* on.
* <p>
* Within each pair, figure out how far apart the two numbers are; you'll need
* <p>
* to add up all of
* <p>
* those distances. For example, if you pair up a 3 from the left list with a 7 from the right list,
* the distance apart is 4; if you pair up a 9 with a 3, the distance apart is
* <p>
* 6.
* <p>
* In the example list above, the pairs and distances would be as follows:
* <p>
* <p>
* right list is 3. The
* <p>
* and the second-smallest number in the right list is another 3. The distance between them is 1.
* The third-smallest number in both lists is 3, so the distance between them is 0. The next numbers
* to pair up are 3 and 4, a distance of 1. The fifth-smallest numbers in each list are 3 and 5, a
* distance of 2. Finally, the largest number in the left list is 4, while the largest number in the
* right list is 9; these are a distance 5 apart.
* <p>
* To find the total distance between the left list and the right list, add up the distances between
* all the pairs you found. In the example above, this is 2 + 1 + 0 + 1 + 2 + 5, a total distance of
* 11!
* <p>
* Your actual left and right lists contain many location IDs. What is the total distance between
* your lists?
 */

package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"sort"
	"strconv"
	"strings"
)

const (
	xcoordinate = 0
	ycoordinate = 1
)

func main() {

	left, right := readInput()
	fmt.Println(partOne(left, right))
	fmt.Println(partTwo(left, right))

}

func readInput() ([]int, []int) {
	var left_locations, right_locations []int
	file, err := os.Open("/home/keshavak/repos/adventofcode/java/inputs/2024/day01_part1.txt")

	if err != nil {
		fmt.Println("Error reading the input file", err)
		return nil, nil
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		locations := strings.Fields(scanner.Text())
		first, _ := strconv.Atoi(locations[xcoordinate])
		second, _ := strconv.Atoi(locations[ycoordinate])
		left_locations = append(left_locations, first)
		right_locations = append(right_locations, second)
	}

	if err := scanner.Err(); err != nil {
		fmt.Println("Error reading the input file", err)
		return nil, nil
	}
	sort.Ints(left_locations)
	sort.Ints(right_locations)

	return left_locations, right_locations

}

func partOne(left_locations, right_locations []int) int {
	total_distance := 0
	for idx, _ := range left_locations {
		total_distance += int(math.Abs(float64(left_locations[idx]) - float64(right_locations[idx])))
	}
	return total_distance
}

func partTwo(left_locations, right_locations []int) int {
	locationsMap := make(map[int]int)
	similarityScore := 0

	for _, val := range right_locations {
		if containsKey(locationsMap, val) {
			locationsMap[val] = locationsMap[val] + 1
		} else {
			locationsMap[val] = 1
		}
	}

	for _, val := range left_locations {
		if containsKey(locationsMap, val) {
			similarityScore += locationsMap[val] * val
		}
	}

	return similarityScore

}

func containsKey(locationMap map[int]int, key int) bool {
	_, ok := locationMap[key]
	return ok
}
