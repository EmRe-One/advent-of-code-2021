package de.emreak.adventofcode.days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day12Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day12_example.txt")
        assertEquals(-1, Day12.part1(input), "Day12, Part1 should be -1.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day12_example.txt")
        assertEquals(-1, Day12.part2(input), "Day12, Part2 should be -1.")
    }

}