package tr.emreone.adventofcode.days

import org.junit.jupiter.api.Test
import tr.emreone.kotlin_utils.Resources
import tr.emreone.kotlin_utils.automation.solve

internal class Day17Test {

    @Test
    fun `execute_tests`() {
        solve<Day17>(false) {
            Resources.resourceAsList("day17_example.txt")
                .joinToString("\n") part1 45 part2 112
        }
    }

}
