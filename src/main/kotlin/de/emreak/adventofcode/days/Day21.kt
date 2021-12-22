package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils.logger

object Day21 {

    class DeterministicDice() {
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

    class Player(var position: Int) {
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
        val player1 = Player(7)
        val player2 = Player(4)
        var currentPlayer = player1

        while (player1.score < 1000 && player2.score < 1000) {
            currentPlayer.play(dice)
            currentPlayer = if (currentPlayer == player1) player2 else player1
            logger.debug { "Player 1: ${player1.score}, Player 2: ${player2.score}" }
        }

        return player2.score * dice.numberOfRolls
    }

    fun part2(input: List<String>): Int {

        return 0
    }
}
