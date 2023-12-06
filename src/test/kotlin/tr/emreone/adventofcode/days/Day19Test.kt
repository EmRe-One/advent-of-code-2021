package tr.emreone.adventofcode.days

import tr.emreone.utils.FileLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day19Test {

    @Test
    fun part1() {
        val input = FileLoader.readLines("src/test/resources", "day19_example.txt")
        assertEquals(79, Day19.part1(input), "Day19, Part1 should be 79.")
    }

    @Test
    fun part2() {
        val input = FileLoader.readLines("src/test/resources", "day19_example.txt")
        assertEquals(3621, Day19.part2(input), "Day19, Part2 should be 3621.")
    }

}