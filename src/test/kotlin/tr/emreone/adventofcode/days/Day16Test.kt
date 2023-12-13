package tr.emreone.adventofcode.days

import org.junit.jupiter.api.Test
import tr.emreone.kotlin_utils.Resources
import tr.emreone.kotlin_utils.automation.solve

internal class Day16Test {

    @Test
    fun `execute_tests`() {
        val lines = Resources.resourceAsList("day16_example.txt")

        solve<Day16>(false) {
            lines[0] part1 16
            lines[1] part1 12
            lines[2] part1 23
            lines[3] part1 31

            lines[5] part2 3
            lines[6] part2 54
            lines[7] part2 7
            lines[8] part2 9
            lines[9] part2 1
            lines[10] part2 0
            lines[11] part2 0
            lines[12] part2 1
        }
    }

}
