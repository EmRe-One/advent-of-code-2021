package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day22Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day22_example.txt")
        assertEquals(-1, Day22.part1(input), "Day22, Part1 should be -1.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day22_example.txt")
        assertEquals(-1, Day22.part2(input), "Day22, Part2 should be -1.")
    }

}