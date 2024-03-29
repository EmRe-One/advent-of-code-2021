package tr.emreone.adventofcode.days

import org.junit.jupiter.api.Test
import tr.emreone.kotlin_utils.Resources
import tr.emreone.kotlin_utils.automation.solve

internal class Day14Test {

    @Test
    fun `execute_tests`() {
        solve<Day14>(false) {
            Resources.resourceAsList("day14_example.txt")
                .joinToString("\n") part1 1588 part2 2_188_189_693_529
        }
    }

}
