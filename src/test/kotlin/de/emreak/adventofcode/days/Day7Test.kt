package de.emreak.adventofcode.days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day7Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day7_example.txt")
        assertEquals(-1, Day7.part1(input), "Day7, Part1 should be -1.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day7_example.txt")
        assertEquals(-1, Day7.part2(input), "Day7, Part2 should be -1.")
    }

}