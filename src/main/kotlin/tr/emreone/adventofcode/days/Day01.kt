package tr.emreone.adventofcode.days

import tr.emreone.kotlin_utils.automation.Day


class Day01: Day(1, 2021, "Sonar Sweep") {

    override fun part1(): Int {
        return inputAsList
            .map {
                it.toInt()
            }
            .windowed(2)
            .count { (a, b) ->
                a < b
            }
    }

    override fun part2(): Int {
        return inputAsList
            .map {
                it.toInt()
            }
            .windowed(3)
            .windowed(2)
            .count { (triple1, triple2) ->
                triple1.sum() < triple2.sum()
            }
    }

}
