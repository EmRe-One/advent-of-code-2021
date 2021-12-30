package de.emreak.adventofcode.days

import tr.emreone.utils.FileLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day4Test {

    @Test
    fun part1() {
        val input = FileLoader.readLines("src/test/resources", "day4_example.txt")
        assertEquals(4512, Day4.part1(input), "Day4, Part1 should be 4512.")
    }

    @Test
    fun part2() {
        val input = FileLoader.readLines("src/test/resources", "day4_example.txt")
        assertEquals(1924, Day4.part2(input), "Day4, Part2 should be 1924.")
    }

}