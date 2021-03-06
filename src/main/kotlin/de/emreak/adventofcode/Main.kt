package de.emreak.adventofcode

import de.emreak.adventofcode.days.*
import tr.emreone.utils.FileLoader
import tr.emreone.utils.Logger.logger

fun main() {

    val day = 25

    logger.info { "Day $day: " }

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
        10 -> solveDay10()
        11 -> solveDay11()
        12 -> solveDay12()
        13 -> solveDay13()
        14 -> solveDay14()
        15 -> solveDay15()
        16 -> solveDay16()
        17 -> solveDay17()
        18 -> solveDay18()
        19 -> solveDay19()
        20 -> solveDay20()
        21 -> solveDay21()
        22 -> solveDay22()
        23 -> solveDay23()
        24 -> solveDay24()
        25 -> solveDay25()
// $1
        else -> {
            throw IllegalArgumentException("Day $day is not implemented yet.")
        }
    }

}

fun solveDay1() {
    val input = FileLoader.readLinesAsInts(filename = "day1.txt")

    val solution1 = Day1.part1(input)
    logger.info {"Solution1: $solution1" }

    val solution2 = Day1.part2(input)
    logger.info {"Solution2: $solution2" }
}

fun solveDay2() {
    val input = FileLoader.readLines(filename = "day2.txt")

    val solution1 = Day2.part1(input)
    logger.info {"Solution1: $solution1" }

    val solution2 = Day2.part2(input)
    logger.info {"Solution2: $solution2" }
}

fun solveDay3() {
    val input = FileLoader.readLines(filename = "day3.txt")

    val solution1 = Day3.part1(input)
    logger.info {"Solution1: $solution1" }

    val solution2 = Day3.part2(input)
    logger.info {"Solution2: $solution2" }
}

fun solveDay4() {
    val input = FileLoader.readLines(filename = "day4.txt")

    val solution1 = Day4.part1(input)
    logger.info {"Solution1: $solution1" }

    val solution2 = Day4.part2(input)
    logger.info {"Solution2: $solution2" }
}

fun solveDay5() {
    val input = FileLoader.readLines(filename = "day5.txt")

    val solution1 = Day5.part1(input)
    logger.info {"Solution1: $solution1" }

    val solution2 = Day5.part2(input)
    logger.info {"Solution2: $solution2" }
}

fun solveDay6() {
    val input = FileLoader.readLines(filename = "day6.txt")[0]

    val solution1 = Day6.countAllFishesAfterDays(input, 80)
    logger.info {"Solution1: $solution1" }

    val solution2 = Day6.countAllFishesAfterDays(input, 256)
    logger.info {"Solution2: $solution2" }
}

fun solveDay7() {
    val input = FileLoader.readLines(filename = "day7.txt")

    val solution1 = Day7.part1(input)
    logger.info {"Solution1: $solution1" }

    val solution2 = Day7.part2(input)
    logger.info {"Solution2: $solution2" }
}

fun solveDay8() {
    val input = FileLoader.readLines(filename = "day8.txt")

    val solution1 = Day8.part1(input)
    logger.info {"Solution1: $solution1" }

    val solution2 = Day8.part2(input)
    logger.info {"Solution2: $solution2" }
}

fun solveDay9() {
    val input = FileLoader.readLines(filename = "day9.txt")

    val solution1 = Day9.part1(input)
    logger.info {"Solution1: $solution1" }

    val solution2 = Day9.part2(input)
    logger.info {"Solution2: $solution2" }
}

fun solveDay10() {
    val input = FileLoader.readLines(filename = "day10.txt")

    val solution1 = Day10.part1(input)
    logger.info {"Solution1: $solution1" }

    val solution2 = Day10.part2(input)
    logger.info {"Solution2: $solution2" }
}

fun solveDay11() {
    val input = FileLoader.readLines(filename = "day11.txt")

    val solution1 = Day11.part1(input)
    logger.info {"Solution1: $solution1" }

    val solution2 = Day11.part2(input)
    logger.info {"Solution2: $solution2" }
}

fun solveDay12() {
    val input = FileLoader.readLines(filename = "day12.txt")

    val solution1 = Day12.part1(input)
    logger.info { "Solution1: $solution1" }

    val solution2 = Day12.part2(input)
    logger.info { "Solution2: $solution2" }
}

fun solveDay13() {
    val input = FileLoader.readLines(filename = "day13.txt")

    val solution1 = Day13.part1(input)
    logger.info { "Solution1: $solution1" }

    val solution2 = Day13.part2(input)
    logger.info { "Solution2: $solution2" }
}

fun solveDay14() {
    val input = FileLoader.readLines(filename = "day14.txt")

    val solution1 = Day14.part1(input)
    logger.info { "Solution1: $solution1" }

    val solution2 = Day14.part2(input)
    logger.info { "Solution2: $solution2" }
}

fun solveDay15() {
    val input = FileLoader.readLines(filename = "day15.txt")

    val solution1 = Day15.part1(input)
    logger.info { "Solution1: $solution1" }

    val solution2 = Day15.part2(input)
    logger.info { "Solution2: $solution2" }
}

fun solveDay16() {
    val input = FileLoader.readLines(filename = "day16.txt")

    val solution1 = Day16.part1(input.first())
    logger.info { "Solution1: $solution1" }

    val solution2 = Day16.part2(input.first())
    logger.info { "Solution2: $solution2" }
}
fun solveDay17() {
    val input = FileLoader.readLines(filename = "day17.txt")

    val solution1 = Day17.part1(input.first())
    logger.info { "Solution1: $solution1" }

    val solution2 = Day17.part2(input.first())
    logger.info { "Solution2: $solution2" }
}

fun solveDay18() {
    val input = FileLoader.readLines(filename = "day18.txt")

    val solution1 = Day18.part1(input)
    logger.info { "Solution1: $solution1" }

    val solution2 = Day18.part2(input)
    logger.info { "Solution2: $solution2" }
}

fun solveDay19() {
    val input = FileLoader.readLines(filename = "day19.txt")

    val solution1 = Day19.part1(input)
    logger.info { "Solution1: $solution1" }

    val solution2 = Day19.part2(input)
    logger.info { "Solution2: $solution2" }
}

fun solveDay20() {
    val input = FileLoader.readLines(filename = "day20.txt")

    val solution1 = Day20.part1(input)
    logger.info { "Solution1: $solution1" }

    val solution2 = Day20.part2(input)
    logger.info { "Solution2: $solution2" }
}

fun solveDay21() {
    val input = FileLoader.readLines(filename = "day21.txt")

    val solution1 = Day21.part1(input)
    logger.info { "Solution1: $solution1" }

    val solution2 = Day21.part2(input)
    logger.info { "Solution2: $solution2" }
}

fun solveDay22() {
    val input = FileLoader.readLines(filename = "day22.txt")

    val solution1 = Day22.part1(input)
    logger.info { "Solution1: $solution1" }

    val solution2 = Day22.part2(input)
    logger.info { "Solution2: $solution2" }
}

fun solveDay23() {
    val input = FileLoader.readLines(filename = "day23.txt")

    val solution1 = Day23.part1(input)
    logger.info { "Solution1: $solution1" }

    val solution2 = Day23.part2(input)
    logger.info { "Solution2: $solution2" }
}

fun solveDay24() {
    val input = FileLoader.readLines(filename = "day24.txt")

    val solution1 = Day24.part1(input)
    logger.info { "Solution1: $solution1" }

    val solution2 = Day24.part2(input)
    logger.info { "Solution2: $solution2" }
}

fun solveDay25() {
    val input = FileLoader.readLines(filename = "day25.txt")

    val solution1 = Day25.part1(input)
    logger.info { "Solution1: $solution1" }
}

// $2
