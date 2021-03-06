package de.emreak.adventofcode.days

import tr.emreone.utils.FileLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day13Test {

    @Test
    fun part1() {
        val input = FileLoader.readLines("src/test/resources", "day13_example.txt")
        assertEquals(17, Day13.part1(input), "Day13, Part1 should be 17.")
    }

}