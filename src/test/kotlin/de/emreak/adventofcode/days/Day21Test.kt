package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day21Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day21_example.txt")
        assertEquals(739785, Day21.part1(input), "Day21, Part1 should be 739785.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day21_example.txt")
        assertEquals(-1, Day21.part2(input), "Day21, Part2 should be -1.")
    }

}