package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils.logger

object Day14 {

    fun part1(input: List<String>): Int {
        val template = input.first()
        val rules = input.drop(2).map {
            val (pattern, result) = it.split(" -> ")
            pattern to result
        }.toMap()

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

    fun part2(input: List<String>): Long {
        val template = input.first()
        val lastPolymer = template.last()

        val rules = input.drop(2).map {
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
