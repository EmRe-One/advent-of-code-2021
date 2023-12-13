package tr.emreone.adventofcode.days

import org.junit.jupiter.api.Test
import tr.emreone.kotlin_utils.Resources
import tr.emreone.kotlin_utils.automation.solve

internal class Day10Test {

    @Test
    fun `execute_tests`() {
        solve<Day10>(false) {
            Resources.resourceAsList("day10_example.txt")
                .joinToString("\n") part1 26_397 part2 288_957
        }
    }

}
