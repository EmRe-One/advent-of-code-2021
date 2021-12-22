package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day11Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day11_example.txt")
        assertEquals(1656, Day11.part1(input), "Day11, Part1 should be 1656.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day11_example.txt")
        assertEquals(195, Day11.part2(input), "Day11, Part2 should be 195.")
    }

}