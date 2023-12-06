package tr.emreone.adventofcode.days

import tr.emreone.utils.FileLoader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


internal class Day2Test {

    @Test
    fun part1() {
        val input = FileLoader.readLines("src/test/resources", "day2_example.txt")
        assertEquals(150, Day2.part1(input), "Day2, Part1 should be 150.")
    }

    @Test
    fun part2() {
        val input = FileLoader.readLines("src/test/resources", "day2_example.txt")
        assertEquals(900, Day2.part2(input), "Day2, Part2 should be 900.")
    }
}