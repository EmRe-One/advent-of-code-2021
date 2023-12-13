package tr.emreone.adventofcode.days

import tr.emreone.kotlin_utils.Logger.logger
import tr.emreone.kotlin_utils.automation.Day


class Day14 : Day(14, 2021, "Extended Polymerization") {

    override fun part1(): Int {
        val template = inputAsList.first()
        val rules = inputAsList.drop(2).associate {
            val (pattern, result) = it.split(" -> ")
            pattern to result
        }

        val polymerChain = StringBuilder(template)
        var currentPolymer = polymerChain.toString()

        for (i in 0 until 10) {
            polymerChain.clear()

            currentPolymer.windowed(2).forEach { pair ->
                polymerChain.append(pair[0] + rules[pair]!!)
            }
            polymerChain.append(currentPolymer.last())

            currentPolymer = polymerChain.toString()
            logger.debug { "Polymer after iteration $i: $currentPolymer" }
        }

        val countElements = currentPolymer.groupingBy { it }.eachCount()

        return countElements.maxOf { it.value } - countElements.minOf { it.value }
    }

    override fun part2(): Long {
        val template = inputAsList.first()
        val lastPolymer = template.last()

        val rules = inputAsList.drop(2).map {
            val (pattern, result) = it.split(" -> ")
            pattern to result
        }.toMap()

        val polymerChain = template.windowed(2).groupingBy {
            it
        }.eachCount().map {
            it.key to it.value.toLong()
        }.toMap().toMutableMap()

        for (i in 0 until 40) {
            val tempChain = polymerChain.toMap().filter {
                it.value > 0
            }
            for ((key, value) in tempChain) {
                polymerChain[key] = polymerChain[key]!! - value
                val newPolymer = rules[key]!!
                val firstPart = key[0] + newPolymer
                val secondPart = newPolymer + key[1]

                polymerChain[firstPart] = polymerChain.getOrDefault(firstPart, 0) + value
                polymerChain[secondPart] = polymerChain.getOrDefault(secondPart, 0) + value
            }
        }

        val countingElements = mutableMapOf<Char, Long>()

        for ((key, value) in polymerChain) {
            countingElements[key[0]] = countingElements.getOrDefault(key[0], 0) + value
        }
        countingElements[lastPolymer] = countingElements.getOrDefault(lastPolymer, 0) + 1

        return countingElements.maxOf { it.value } - countingElements.minOf { it.value }
    }

}
