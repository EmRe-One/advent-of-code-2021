package tr.emreone.adventofcode.days

import tr.emreone.utils.FileLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day15Test {

    @Test
    fun part1() {
        val input = FileLoader.readLines("src/test/resources", "day15_example.txt")
        assertEquals(40, Day15.part1(input), "Day15, Part1 should be 40.")
    }

    @Test
    fun part2() {
        val input = FileLoader.readLines("src/test/resources", "day15_example.txt")
        assertEquals(315, Day15.part2(input), "Day15, Part2 should be 315.")
    }

}