package tr.emreone.adventofcode.days

import org.junit.jupiter.api.Test
import tr.emreone.kotlin_utils.Resources
import tr.emreone.kotlin_utils.automation.solve

internal class Day11Test {

    @Test
    fun `execute_tests`() {
        solve<Day11>(false) {
            Resources.resourceAsList("day11_example.txt")
                .joinToString("\n") part1 1656 part2 195
        }
    }

}
