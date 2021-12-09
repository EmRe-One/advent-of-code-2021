package de.emreak.adventofcode

import AdventOfCodeUtils
import de.emreak.adventofcode.days.*

fun main() {

    val day = 9

    when (day) {
        1 -> solveDay1()
        2 -> solveDay2()
        3 -> solveDay3()
        4 -> solveDay4()
        5 -> solveDay5()
        6 -> solveDay6()
        7 -> solveDay7()
        8 -> solveDay8()
        9 -> solveDay9()
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

    val solution1 = Day6.countAllFishesAfterDays(input, 80)
    println("Solution1: $solution1")

    val solution2 = Day6.countAllFishesAfterDays(input, 256)
    println("Solution2: $solution2")
}

fun solveDay7() {
    val input = AdventOfCodeUtils.readLines(filename = "day7.txt")

    val solution1 = Day7.part1(input)
    println("Solution1: $solution1")

    val solution2 = Day7.part2(input)
    println("Solution2: $solution2")
}

fun solveDay8() {
    val input = AdventOfCodeUtils.readLines(filename = "day8.txt")

    val solution1 = Day8.part1(input)
    println("Solution1: $solution1")

    val solution2 = Day8.part2(input)
    println("Solution2: $solution2")
}

fun solveDay9() {
    val input = AdventOfCodeUtils.readLines(filename = "day9.txt")

    val solution1 = Day9.part1(input)
    println("Solution1: $solution1")

    val solution2 = Day9.part2(input)
    println("Solution2: $solution2")
}

// $2
