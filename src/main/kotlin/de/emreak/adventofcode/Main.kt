package de.emreak.adventofcode

import AdventOfCodeUtils
import de.emreak.adventofcode.days.Day1
import de.emreak.adventofcode.days.Day2
import de.emreak.adventofcode.days.Day3
import de.emreak.adventofcode.days.Day4

fun main() {

    val day = 4

    when(day) {
        1 -> {
            val input = AdventOfCodeUtils.readLinesAsInts(filename = "day1.txt")

            val solution1 = Day1.part1(input)
            println("Solution1: $solution1")

            val solution2 = Day1.part2(input)
            println("Solution2: $solution2")
        }
        2 -> {
            val input = AdventOfCodeUtils.readLines(filename = "day2.txt")

            val solution1 = Day2.part1(input)
            println("Solution1: $solution1")

            val solution2 = Day2.part2(input)
            println("Solution2: $solution2")
        }
        3 -> {
            val input = AdventOfCodeUtils.readLines(filename = "day3.txt")

            val solution1 = Day3.part1(input)
            println("Solution1: $solution1")

            val solution2 = Day3.part2(input)
            println("Solution2: $solution2")
        }
        4 -> {
            val input = AdventOfCodeUtils.readLines(filename = "day4.txt")

            val solution1 = Day4.part1(input)
            println("Solution1: $solution1")

            val solution2 = Day4.part2(input)
            println("Solution2: $solution2")
        }
        else -> {
            throw IllegalArgumentException("Day $day is not implemented yet.")
        }
    }


}
