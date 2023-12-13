package tr.emreone.adventofcode.days

import org.junit.jupiter.api.Test
import tr.emreone.kotlin_utils.Resources
import tr.emreone.kotlin_utils.automation.solve

internal class Day08Test {

    @Test
    fun `execute_tests`() {
        solve<Day08>(false) {
            Resources.resourceAsList("day08_example.txt")
                .joinToString("\n") part1 26 part2 61_229
        }
    }

}
