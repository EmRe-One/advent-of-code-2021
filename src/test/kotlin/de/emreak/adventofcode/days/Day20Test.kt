package de.emreak.adventofcode.days

import tr.emreone.utils.FileLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day20Test {

    @Test
    fun part1() {
        val input = FileLoader.readLines("src/test/resources", "day20_example.txt")
        assertEquals(35, Day20.part1(input), "Day20, Part1 should be 35.")
    }

    @Test
    fun part2() {
        val input = FileLoader.readLines("src/test/resources", "day20_example.txt")
        assertEquals(3351, Day20.part2(input), "Day20, Part2 should be 3351.")
    }

}