package tr.emreone.adventofcode.days

import tr.emreone.utils.FileLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day22Test {

    @Test
    fun part1() {
        val input = FileLoader.readLines("src/test/resources", "day22_example_1.txt")
        assertEquals(590784, Day22.part1(input), "Day22, Part1 should be 590784.")
    }

    @Test
    fun part2() {
        val input = FileLoader.readLines("src/test/resources", "day22_example_2.txt")
        assertEquals(2758514936282235L, Day22.part2(input), "Day22, Part2 should be 2.758.514.936.282.235.")
    }

}