package de.emreak.adventofcode.days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day16Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day16_example.txt")
        assertEquals(-1, Day16.part1(input), "Day16, Part1 should be -1.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day16_example.txt")
        assertEquals(-1, Day16.part2(input), "Day16, Part2 should be -1.")
    }

}