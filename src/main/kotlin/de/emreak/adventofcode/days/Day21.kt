package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils.logger

object Day21 {

    class DeterministicDice {
        var current = 0
        var numberOfRolls = 0

        fun roll(): Int {
            numberOfRolls++
            current++
            if (current > 100) {
                current -= 100
            }
            return current
        }
    }

    class Player(val id: Int, var position: Int) {
        var score = 0

        fun play(dice: DeterministicDice) {
            var roll = 0
            repeat(3) {
                roll += dice.roll()
            }
            position = (position + roll - 1) % 10 + 1

            score += position
        }
    }

    fun part1(input: List<String>): Int {
        val dice = DeterministicDice()
        val pattern = """Player (\w+) starting position: (\w+)""".toRegex()
        val player = mutableListOf<Player>()
        for(line in input) {
            val (id, position) = pattern.matchEntire(line)!!.destructured
            player.add(Player(id.toInt(), position.toInt()))
        }
        var currentPlayer = 0

        while (player.all { it.score < 1000 }) {
            player[currentPlayer].play(dice)
            currentPlayer = (currentPlayer + 1) % player.size
        }
        val loser = player.first { it.score < 1000 }
        return loser.score * dice.numberOfRolls
    }

    fun part2(input: List<String>): Int {

        return 0
    }
}
