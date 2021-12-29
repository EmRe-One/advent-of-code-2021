package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day25Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day25_example.txt")
        assertEquals(58, Day25.part1(input), "Day25, Part1 should be 58.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day25_example.txt")
        assertEquals(-1, Day25.part2(input), "Day25, Part2 should be -1.")
    }

}