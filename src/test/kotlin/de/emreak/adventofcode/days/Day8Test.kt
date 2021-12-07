package de.emreak.adventofcode.days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day8Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day8_example.txt")
        assertEquals(-1, Day8.part1(input), "Day8, Part1 should be -1.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day8_example.txt")
        assertEquals(-1, Day8.part2(input), "Day8, Part2 should be -1.")
    }

}