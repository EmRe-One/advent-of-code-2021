package tr.emreone.adventofcode.days

import org.junit.jupiter.api.Test
import tr.emreone.kotlin_utils.Resources
import tr.emreone.kotlin_utils.automation.solve

internal class Day18Test {

    @Test
    fun `execute_tests`() {
        solve<Day18>(false) {
            Resources.resourceAsList("day18_example.txt")
                .joinToString("\n") part1 4140 part2 3993
        }
    }

}
