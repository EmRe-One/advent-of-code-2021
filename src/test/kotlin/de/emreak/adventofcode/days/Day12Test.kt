package de.emreak.adventofcode.days

import tr.emreone.utils.FileLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day12Test {

    @Test
    fun part1Small() {
        val inputSmall = FileLoader.readLines("src/test/resources", "day12_small_example.txt")
        assertEquals(10, Day12.part1(inputSmall), "Day12, Part1 - small example should be 10.")
    }

    @Test
    fun part1Large() {
        val inputLarge = FileLoader.readLines("src/test/resources", "day12_large_example.txt")
        assertEquals(226, Day12.part1(inputLarge), "Day12, Part1 - big example should be 226.")
    }

    @Test
    fun part2Small() {
        val inputSmall = FileLoader.readLines("src/test/resources", "day12_small_example.txt")
        assertEquals(36, Day12.part2(inputSmall), "Day12, Part2 - small example should be 105.")
    }

    @Test
    fun part2Large() {
        val inputLarge = FileLoader.readLines("src/test/resources", "day12_large_example.txt")
        assertEquals(3509, Day12.part2(inputLarge), "Day12, Part2 - big example should be 3509.")
    }
}