package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day3Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day3_example.txt")
        assertEquals(198, Day3.part1(input), "Day3, Part1 should be 198.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day3_example.txt")
        assertEquals(230, Day3.part2(input), "Day3, Part2 should be 230.")
    }

}