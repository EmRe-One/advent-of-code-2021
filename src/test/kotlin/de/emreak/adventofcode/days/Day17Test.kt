package de.emreak.adventofcode.days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day17Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day17_example.txt")
        assertEquals(-1, Day17.part1(input), "Day17, Part1 should be -1.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day17_example.txt")
        assertEquals(-1, Day17.part2(input), "Day17, Part2 should be -1.")
    }

}