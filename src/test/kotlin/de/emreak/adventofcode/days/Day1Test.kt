package de.emreak.adventofcode.days

import org.junit.jupiter.api.Test
import tr.emreone.utils.FileLoader
import kotlin.test.assertEquals


internal class Day1Test {

    @Test
    fun part1() {
        val input = FileLoader.readLinesAsInts("src/test/resources", "day1_example.txt")
        assertEquals(7, Day1.part1(input), "Day 1, Part 1 should be 7.")
    }

    @Test
    fun part2() {
        // same input
        val input = FileLoader.readLinesAsInts("src/test/resources", "day1_example.txt")
        assertEquals(5, Day1.part2(input), "Day 1, Part 2 should be 5.")
    }

}