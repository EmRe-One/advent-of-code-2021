package tr.emreone.adventofcode.days

import tr.emreone.utils.FileLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day21Test {

    @Test
    fun part1() {
        val input = FileLoader.readLines("src/test/resources", "day21_example.txt")
        assertEquals(739785, Day21.part1(input), "Day21, Part1 should be 739785.")
    }

    @Test
    fun part2() {
        val input = FileLoader.readLines("src/test/resources", "day21_example.txt")
        assertEquals(444356092776315L, Day21.part2(input), "Day21, Part2 should be 444.356.092.776.315.")
    }

}