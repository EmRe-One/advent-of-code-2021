package tr.emreone.adventofcode.days

import org.junit.jupiter.api.Test
import tr.emreone.kotlin_utils.Resources
import tr.emreone.kotlin_utils.automation.solve

internal class Day22Test {

    @Test
    fun `execute_tests`() {
        solve<Day22>(false) {
            Resources.resourceAsList("day22_example_1.txt")
                .joinToString("\n") part1 590_784
        }
        solve<Day22>(false) {
            Resources.resourceAsList("day22_example_2.txt")
                .joinToString("\n") part2 2_758_514_936_282_235
        }

    }

}
