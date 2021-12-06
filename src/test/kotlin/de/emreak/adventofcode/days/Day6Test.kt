package de.emreak.adventofcode.days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day6Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day6_example.txt")
        assertEquals(-1, Day6.part1(input), "Day6, Part1 should be -1.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day6_example.txt")
        assertEquals(-1, Day6.part2(input), "Day6, Part2 should be -1.")
    }

}