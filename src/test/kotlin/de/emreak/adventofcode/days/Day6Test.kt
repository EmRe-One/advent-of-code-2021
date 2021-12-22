package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day6Test {

    @Test
    fun part1() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day6_example.txt")[0]

        assertEquals(26, Day6.countAllFishesAfterDays(input, 18), "Day6, Part1 - 1. Example should be 26.")
        assertEquals(5934, Day6.countAllFishesAfterDays(input, 80), "Day6, Part1 - 2. Example should be 5934.")
    }

    @Test
    fun part2() {
        val input = AdventOfCodeUtils.readLines("src/test/resources", "day6_example.txt")[0]
        assertEquals(26984457539, Day6.countAllFishesAfterDays(input, 256), "Day6, Part2 should be 26984457539.")
    }

}