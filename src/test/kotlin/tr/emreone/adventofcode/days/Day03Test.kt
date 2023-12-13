package tr.emreone.adventofcode.days

import org.junit.jupiter.api.Test
import tr.emreone.kotlin_utils.Resources
import tr.emreone.kotlin_utils.automation.solve

internal class Day03Test {

    @Test
    fun `execute_tests`() {
        solve<Day03>(false) {
            Resources.resourceAsList("day03_example.txt")
                .joinToString("\n") part1 198 part2 230
        }
    }

}
