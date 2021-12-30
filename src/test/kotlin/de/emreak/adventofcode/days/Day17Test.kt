package de.emreak.adventofcode.days

import tr.emreone.utils.FileLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day17Test {

    @Test
    fun part1() {
        val input = FileLoader.readLines("src/test/resources", "day17_example.txt")
        assertEquals(45, Day17.part1(input.first()), "Day17, Part1 should be 45.")
    }

    @Test
    fun part2() {
        val input = FileLoader.readLines("src/test/resources", "day17_example.txt")
        assertEquals(112, Day17.part2(input.first()), "Day17, Part2 should be 112.")
    }

}