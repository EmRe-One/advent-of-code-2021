package tr.emreone.adventofcode.days

import kotlin.math.abs

object Day7 {

    fun part1(input: List<String>): Int {
        val positions = input[0].split(",").map { it.toInt() }
        val min = positions.minOf { it }
        val max = positions.maxOf { it }

        var minFuel = Int.MAX_VALUE
        for(i in min..max) {
            val totalFuel = positions.sumOf { abs(it - i) }
            if(totalFuel < minFuel) {
                minFuel = totalFuel
            }
        }
        return minFuel
    }

    fun part2(input: List<String>): Int {
        val positions = input[0].split(",").map { it.toInt() }
        val min = positions.minOf { it }
        val max = positions.maxOf { it }

        var minFuel = Int.MAX_VALUE
        for(i in min..max) {
            // fuel costs -> 1 + 2 + 3 + 4 + 5  -> sum (i = 1..n) -> i -> n * (n + 1) / 2
            val totalFuel = positions.sumOf { it ->
                val n = abs(it - i)
                n * (n + 1) / 2
            }
            if(totalFuel < minFuel) {
                minFuel = totalFuel
            }
        }
        return minFuel
    }
}
