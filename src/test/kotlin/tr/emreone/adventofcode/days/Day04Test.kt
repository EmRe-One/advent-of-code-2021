package tr.emreone.adventofcode.days

import tr.emreone.kotlin_utils.FileLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import tr.emreone.kotlin_utils.Resources
import tr.emreone.kotlin_utils.automation.solve

internal class Day04Test {

    @Test
    fun `execute_tests`() {
        solve<Day04>(false) {
            Resources.resourceAsList("day04_example.txt")
                .joinToString("\n") part1 4512 part2 1924
        }
    }

}
