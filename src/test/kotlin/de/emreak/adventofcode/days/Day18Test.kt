package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day18Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day18_example.txt")
        assertEquals(4140, Day18.part1(input), "Day18, Part1 should be 4140.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day18_example.txt")
        assertEquals(3993, Day18.part2(input), "Day18, Part2 should be 3993.")
    }

}