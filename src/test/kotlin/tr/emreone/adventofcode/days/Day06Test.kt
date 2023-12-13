package tr.emreone.adventofcode.days

import tr.emreone.kotlin_utils.FileLoader
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import tr.emreone.kotlin_utils.Resources
import tr.emreone.kotlin_utils.automation.solve

internal class Day06Test {

    @Test
    fun `execute_tests`() {
        solve<Day06>(false) {
            Resources.resourceAsList("day06_example.txt")
                .joinToString("\n") part1 5934 part2 26_984_457_539
        }
    }

}
