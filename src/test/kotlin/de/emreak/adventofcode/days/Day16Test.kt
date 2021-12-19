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
        assertEquals(-1, Day16.part2(input.first()), "Day16, Part2 should be -1.")
    }

}