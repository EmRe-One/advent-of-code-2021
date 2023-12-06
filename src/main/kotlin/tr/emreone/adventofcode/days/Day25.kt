package tr.emreone.adventofcode.days

import tr.emreone.utils.Logger.logger
import tr.emreone.utils.math.Point2D

object Day25 {

    class SeaCucumber(input: List<String>) {
        private val width  = input.first().length
        private val height = input.size
        private val region = input.flatMapIndexed { y: Int, line: String ->
            line.mapIndexed { x: Int, c: Char ->
                Point2D(x.toLong(), y.toLong()) to c
            }
        }.filterNot {
            it.second == SIGN_EMPTY
        }.associate {
            it.first to it.second
        }

        // extend the Point2d class with the following methods:
        // the east() point is the east neighbor of the point or if reach the border then the most west point in the sea
        // the south() point is the south neighbor of the point or if reach the border then the most nord point in the sea
        private fun Point2D.east() = Point2D((x + 1) % width, y)
        private fun Point2D.south() = Point2D(x, (y + 1) % height)

        private fun next(region: Map<Point2D, Char>): Map<Point2D, Char> {
            val nextRegion = region.toMutableMap()

            // First go to the east
            nextRegion.filterValues { it == SIGN_EAST }
                .keys
                .map {
                    it to it.east()
                }
                .filter {
                    it.second !in nextRegion
                }
                .forEach {
                    nextRegion.remove(it.first)
                    nextRegion[it.second] = SIGN_EAST
                }

            // Then go to the south
            nextRegion.filterValues { it == SIGN_SOUTH }
                .keys
                .map {
                    it to it.south()
                }
                .filter {
                    it.second !in nextRegion
                }
                .forEach {
                    nextRegion.remove(it.first)
                    nextRegion[it.second] = SIGN_SOUTH
                }

            return nextRegion
        }

        fun countStepsToStopMoving(): Int {
            val sequence = generateSequence(this.region) {
                printSeaCucumber(it)
                next(it)
            }.zipWithNext().takeWhile {
                it.first != it.second
            }

            return sequence.count() + 1
        }

        private fun printSeaCucumber(seaCucumber: Map<Point2D, Char>) {
            val output = buildString {
                appendLine()
                for(y in 0 until height) {
                    for (x in 0 until width) {
                        append(seaCucumber[Point2D(x.toLong(), y.toLong())] ?: SIGN_EMPTY)
                    }
                    appendLine()
                }
            }
            logger.debug { output }
        }

        companion object {
            const val SIGN_EMPTY  = '.'
            const val SIGN_EAST   = '>'
            const val SIGN_SOUTH  = 'v'
        }
    }

    fun part1(input: List<String>): Int {
        val seaCucumber = SeaCucumber(input)
        return seaCucumber.countStepsToStopMoving()
    }

    fun part2(input: List<String>): Int {

        return 0
    }
}
