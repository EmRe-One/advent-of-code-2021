package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day24Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day24_example.txt")
        assertEquals(-1, Day24.part1(input), "Day24, Part1 should be -1.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day24_example.txt")
        assertEquals(-1, Day24.part2(input), "Day24, Part2 should be -1.")
    }

}