package de.emreak.adventofcode.days

import tr.emreone.utils.FileLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day5Test {

    @Test
    fun part1() {
        val input = FileLoader.readLines("src/test/resources", "day5_example.txt")
        assertEquals(5, Day5.part1(input), "Day5, Part1 should be 5.")
    }

    @Test
    fun part2() {
        val input = FileLoader.readLines("src/test/resources", "day5_example.txt")
        assertEquals(12, Day5.part2(input), "Day5, Part2 should be 12.")
    }

}