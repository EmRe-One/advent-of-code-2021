package de.emreak.adventofcode.days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day16Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day16_example.txt")
        assertEquals(16, Day16.part1(input[0]), "Day16, Part1 - 1. Example should be 16.")
        assertEquals(12, Day16.part1(input[1]), "Day16, Part1 - 2. Example should be 12.")
        assertEquals(23, Day16.part1(input[2]), "Day16, Part1 - 3. Example should be 23.")
        assertEquals(31, Day16.part1(input[3]), "Day16, Part1 - 4. Example should be 31.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day16_example.txt")

        assertEquals(3, Day16.part2(input[5]), "Day16, Part2 - 1. Example should be 1 + 2 = 3.")
        assertEquals(54, Day16.part2(input[6]), "Day16, Part2 - 1. Example should be 6 * 9 = 54.")
        assertEquals(7, Day16.part2(input[7]), "Day16, Part2 - 1. Example should be min(7, 8, 9) = 7.")
        assertEquals(9, Day16.part2(input[8]), "Day16, Part2 - 1. Example should be max(7, 8, 9) = 9.")
        assertEquals(1, Day16.part2(input[9]), "Day16, Part2 - 1. Example should be 5 < 15 --> 1 (true).")
        assertEquals(0, Day16.part2(input[10]), "Day16, Part2 - 1. Example should be 5 > 15 --> 0 (false).")
        assertEquals(0, Day16.part2(input[11]), "Day16, Part2 - 1. Example should be 5 == 15 --> 0 (false).")
        assertEquals(1, Day16.part2(input[12]), "Day16, Part2 - 1. Example should be 1 + 3 == 2 * 2 --> 1 (true).")
    }

}