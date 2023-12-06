package tr.emreone.adventofcode.days

import tr.emreone.utils.FileLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day25Test {

    @Test
    fun part1() {
        val input = FileLoader.readLines("src/test/resources", "day25_example.txt")
        assertEquals(58, Day25.part1(input), "Day25, Part1 should be 58.")
    }

}