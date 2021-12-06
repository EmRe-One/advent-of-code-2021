package de.emreak.adventofcode

import AdventOfCodeUtils
import de.emreak.adventofcode.days.*

fun main() {

    val day = 6

    when (day) {
        1 -> solveDay1()
        2 -> solveDay2()
        3 -> solveDay3()
        4 -> solveDay4()
        5 -> solveDay5()
        6 -> solveDay6()
// $1
        else -> {
            throw IllegalArgumentException("Day $day is not implemented yet.")
        }
    }

}

fun solveDay1() {
    val input = AdventOfCodeUtils.readLinesAsInts(filename = "day1.txt")

    val solution1 = Day1.part1(input)
    println("Solution1: $solution1")

    val solution2 = Day1.part2(input)
    println("Solution2: $solution2")
}

fun solveDay2() {
    val input = AdventOfCodeUtils.readLines(filename = "day2.txt")

    val solution1 = Day2.part1(input)
    println("Solution1: $solution1")

    val solution2 = Day2.part2(input)
    println("Solution2: $solution2")
}

fun solveDay3() {
    val input = AdventOfCodeUtils.readLines(filename = "day3.txt")

    val solution1 = Day3.part1(input)
    println("Solution1: $solution1")

    val solution2 = Day3.part2(input)
    println("Solution2: $solution2")
}

fun solveDay4() {
    val input = AdventOfCodeUtils.readLines(filename = "day4.txt")

    val solution1 = Day4.part1(input)
    println("Solution1: $solution1")

    val solution2 = Day4.part2(input)
    println("Solution2: $solution2")
}

fun solveDay5() {
    val input = AdventOfCodeUtils.readLines(filename = "day5.txt")

    val solution1 = Day5.part1(input)
    println("Solution1: $solution1")

    val solution2 = Day5.part2(input)
    println("Solution2: $solution2")
}

fun solveDay6() {
    val input = AdventOfCodeUtils.readLines(filename = "day6.txt")[0]

    // val solution1 = Day6.part1(input, 80)
    // println("Solution1: $solution1")

    // val solution2 = Day6.part2(input, 256)
    // println("Solution2: $solution2")

    println("0,   2: " + Day6.part2("0", 2))
    println("1,   2: " + Day6.part2("1", 2))
    println("2,   2: " + Day6.part2("2", 2))
    println("3,   2: " + Day6.part2("3", 2))
    println("4,   2: " + Day6.part2("4", 2))
    println("5,   2: " + Day6.part2("5", 2))
    println("6,   2: " + Day6.part2("6", 2))
    println()
    println("0,   4: " + Day6.part2("0", 4))
    println("1,   4: " + Day6.part2("1", 4))
    println("2,   4: " + Day6.part2("2", 4))
    println("3,   4: " + Day6.part2("3", 4))
    println("4,   4: " + Day6.part2("4", 4))
    println("5,   4: " + Day6.part2("5", 4))
    println("6,   4: " + Day6.part2("6", 4))
    println()
    println("0,   8: " + Day6.part2("0", 8))
    println("1,   8: " + Day6.part2("1", 8))
    println("2,   8: " + Day6.part2("2", 8))
    println("3,   8: " + Day6.part2("3", 8))
    println("4,   8: " + Day6.part2("4", 8))
    println("5,   8: " + Day6.part2("5", 8))
    println("6,   8: " + Day6.part2("6", 8))
    println()
    println("0,  16: " + Day6.part2("0", 16))
    println("1,  16: " + Day6.part2("1", 16))
    println("2,  16: " + Day6.part2("2", 16))
    println("3,  16: " + Day6.part2("3", 16))
    println("4,  16: " + Day6.part2("4", 16))
    println("5,  16: " + Day6.part2("5", 16))
    println("6,  16: " + Day6.part2("6", 16))
    println()
    println("0,  32: " + Day6.part2("0", 32))
    println("1,  32: " + Day6.part2("1", 32))
    println("2,  32: " + Day6.part2("2", 32))
    println("3,  32: " + Day6.part2("3", 32))
    println("4,  32: " + Day6.part2("4", 32))
    println("5,  32: " + Day6.part2("5", 32))
    println("6,  32: " + Day6.part2("6", 32))
    println()
    println("0,  64: " + Day6.part2("0", 64))
    println("1,  64: " + Day6.part2("1", 64))
    println("2,  64: " + Day6.part2("2", 64))
    println("3,  64: " + Day6.part2("3", 64))
    println("4,  64: " + Day6.part2("4", 64))
    println("5,  64: " + Day6.part2("5", 64))
    println("6,  64: " + Day6.part2("6", 64))
    println()
    println("0, 128: " + Day6.part2("0", 128))
    println("1, 128: " + Day6.part2("1", 128))
    println("2, 128: " + Day6.part2("2", 128))
    println("3, 128: " + Day6.part2("3", 128))
    println("4, 128: " + Day6.part2("4", 128))
    println("5, 128: " + Day6.part2("5", 128))
    println("6, 128: " + Day6.part2("6", 128))
    println()


}

// $2
