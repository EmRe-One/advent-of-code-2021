package tr.emreone.adventofcode.days

import org.junit.jupiter.api.Test
import tr.emreone.kotlin_utils.Resources
import tr.emreone.kotlin_utils.automation.solve

internal class Day12Test {

    @Test
    fun `execute_tests_small`() {
        solve<Day12>(false) {
            Resources.resourceAsList("day12_small_example.txt")
                .joinToString("\n") part1 10 part2 36
        }
    }

    @Test
    fun `execute_tests_large`() {
        solve<Day12>(false) {
            Resources.resourceAsList("day12_large_example.txt")
                .joinToString("\n") part1 226 part2 3509
        }
    }

}
