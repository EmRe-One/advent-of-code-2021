package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day23Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day23_example.txt")
        assertEquals(-1, Day23.part1(input), "Day23, Part1 should be -1.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day23_example.txt")
        assertEquals(-1, Day23.part2(input), "Day23, Part2 should be -1.")
    }

}