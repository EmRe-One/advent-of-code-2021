package de.emreak.adventofcode.days

import de.emreak.adventofcode.helper.FileReader

object Day2 {

    fun solveExercise1(): Int {
        val input = FileReader.readLinesToList("src/days/day2/input_day2.txt")

        var positionX = 0
        var positionDepth = 0

        input.forEach { commandLine ->
            val (command, amount) = commandLine.split(" ")

            when (command) {
                "forward" -> positionX += amount.toInt()
                "down"    -> positionDepth += amount.toInt()
                "up"      -> positionDepth -= amount.toInt()
                else      -> throw IllegalArgumentException("Unknown command: $command")
            }
        }

        return positionX * positionDepth
    }

    fun solveExercise2(): Int {
        val input = FileReader.readLinesToList("src/days/day2/input_day2.txt")

        var positionX = 0
        var positionDepth = 0
        var aim = 0

        input.forEach { commandLine ->
            val (command, amount) = commandLine.split(" ")

            val amountInt = amount.toInt()
            when (command) {
                "forward" -> {
                    positionX += amount.toInt()
                    positionDepth += aim * amountInt
                }
                "down"    -> aim += amountInt
                "up"      -> aim -= amountInt
                else      -> throw IllegalArgumentException("Unknown command: $command")
            }
        }

        return positionX * positionDepth
    }

}