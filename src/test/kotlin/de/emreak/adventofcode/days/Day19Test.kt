package de.emreak.adventofcode.days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day19Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day19_example.txt")
        assertEquals(-1, Day19.part1(input), "Day19, Part1 should be -1.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day19_example.txt")
        assertEquals(-1, Day19.part2(input), "Day19, Part2 should be -1.")
    }

}