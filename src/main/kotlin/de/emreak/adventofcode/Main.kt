import de.emreak.adventofcode.days.Day1

fun main() {

    val day = 1

    when(day) {
        1 -> {
            val input = AdventOfCodeUtils.readLinesAsInts(parent = "src/main/resources/day1/input_1.txt", name = "input_day1")

            val solution1 = Day1.part1(input)
            println("Solution1: $solution1")

            val solution2 = Day1.part2(input)
            println("Solution2: $solution2")
        }
        2 -> {

        }
        else -> {
            throw IllegalArgumentException("Day $day is not implemented yet.")
        }
    }


}
