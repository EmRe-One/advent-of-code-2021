package tr.emreone.adventofcode.days

import kotlin.math.max

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


    val cache = mutableMapOf<List<Int>, Pair<Long, Long>>()
    fun countWins(position1: Int, score1: Int, position2: Int, score2: Int): Pair<Long, Long> {
        if (score1 >= 21) return Pair(1L, 0L)
        if (score2 >= 21) return Pair(0L, 1L)
        if (listOf(position1, score1, position2, score2) in cache) {
            return cache[listOf(position1, score1, position2, score2)]!!
        }

        var result = Pair(0L, 0L)

        for(d1 in 1..3) {
            for(d2 in 1..3) {
                for(d3 in 1..3) {
                    val newP1 = (position1 + d1 + d2 + d3 - 1) % 10 + 1
                    val newS1 = score1 + newP1

                    val wins = countWins(position2, score2, newP1, newS1)
                    // switched order of the player
                    result = result.first + wins.second to result.second + wins.first
                }
            }
        }
        cache[listOf(position1, score1, position2, score2)] = result
        return result
    }

    fun part2(input: List<String>): Long {
        val pattern = """Player (\w+) starting position: (\w+)""".toRegex()
        val player = mutableListOf<Player>()
        for(line in input) {
            val (id, position) = pattern.matchEntire(line)!!.destructured
            player.add(Player(id.toInt(), position.toInt()))
        }
        cache.clear()
        val totalWins = countWins(player[0].position, player[0].score, player[1].position, player[1].score)

        return max(totalWins.first, totalWins.second)
    }
}
