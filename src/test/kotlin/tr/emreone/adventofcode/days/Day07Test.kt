package tr.emreone.adventofcode.days

import tr.emreone.kotlin_utils.FileLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import tr.emreone.kotlin_utils.Resources
import tr.emreone.kotlin_utils.automation.solve

internal class Day07Test {

    @Test
    fun `execute_tests`() {
        solve<Day07>(false) {
            Resources.resourceAsList("day07_example.txt")
                .joinToString("\n") part1 37 part2 168
        }
    }

}
