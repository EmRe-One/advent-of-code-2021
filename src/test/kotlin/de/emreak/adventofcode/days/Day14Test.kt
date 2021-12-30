package de.emreak.adventofcode.days

import tr.emreone.utils.FileLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day14Test {

    @Test
    fun part1() {
        val input = FileLoader.readLines("src/test/resources", "day14_example.txt")
        assertEquals(1588, Day14.part1(input), "Day14, Part1 should be 1588.")
    }

    @Test
    fun part2() {
        val input = FileLoader.readLines("src/test/resources", "day14_example.txt")
        assertEquals(2188189693529, Day14.part2(input), "Day14, Part2 should be 2188189693529.")
    }

}