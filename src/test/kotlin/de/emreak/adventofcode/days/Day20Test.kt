package de.emreak.adventofcode.days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day20Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day20_example.txt")
        assertEquals(-1, Day20.part1(input), "Day20, Part1 should be -1.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day20_example.txt")
        assertEquals(-1, Day20.part2(input), "Day20, Part2 should be -1.")
    }

}