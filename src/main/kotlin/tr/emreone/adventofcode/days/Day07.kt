package tr.emreone.adventofcode.days

import tr.emreone.kotlin_utils.automation.Day
import kotlin.math.abs

class Day07 : Day(7, 2021, "The Treachery of Whales") {

    override fun part1(): Int {
        val positions = inputAsList[0].split(",").map { it.toInt() }
        val min = positions.minOf { it }
        val max = positions.maxOf { it }

        var minFuel = Int.MAX_VALUE
        for (i in min..max) {
            val totalFuel = positions.sumOf { abs(it - i) }
            if (totalFuel < minFuel) {
                minFuel = totalFuel
            }
        }
        return minFuel
    }

    override fun part2(): Int {
        val positions = inputAsList[0].split(",").map { it.toInt() }
        val min = positions.minOf { it }
        val max = positions.maxOf { it }

        var minFuel = Int.MAX_VALUE
        for (i in min..max) {
            // fuel costs -> 1 + 2 + 3 + 4 + 5  -> sum (i = 1..n) -> i -> n * (n + 1) / 2
            val totalFuel = positions.sumOf { it ->
                val n = abs(it - i)
                n * (n + 1) / 2
            }
            if (totalFuel < minFuel) {
                minFuel = totalFuel
            }
        }
        return minFuel
    }

}
