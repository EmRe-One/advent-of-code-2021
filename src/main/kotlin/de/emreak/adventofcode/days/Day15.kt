package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils.logger
import de.emreak.adventofcode.Coords
import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min

object Day15 {

    class Traversal(val location: Coords, val totalRisk: Int, val totalPath: List<Coords>) : Comparable<Traversal> {
        override fun compareTo(other: Traversal): Int =
            this.totalRisk - other.totalRisk
    }

    class Cavern(var grid: Array<IntArray>) {
        val height = grid.size
        val width = grid[0].size

        fun get(coord: Coords): Int {
            return grid[coord.second][coord.first]
        }

        fun traverse(start: Coords, destination: Coords): Traversal {
            val toBeEvaluated = PriorityQueue<Traversal>().also {
                it.add(Traversal(start, 0, mutableListOf(start)))
            }
            val visited = mutableSetOf<Coords>()

            while (toBeEvaluated.isNotEmpty()) {
                val currentTraversal = toBeEvaluated.poll()
                if (currentTraversal.location == destination) {
                    return currentTraversal
                }
                if (currentTraversal.location !in visited) {
                    visited.add(currentTraversal.location)
                    this.getNeighbors(currentTraversal.location).filter {
                        it.first in 0..destination.first && it.second in 0..destination.second
                    }.forEach {
                        toBeEvaluated.offer(Traversal(it,
                            currentTraversal.totalRisk + this.get(it),
                            currentTraversal.totalPath + it)
                        )
                    }
                }
            }
            error("No path to destination (which is really weird, right?)")
        }

        private fun getNeighbors(location: Coords): List<Coords> {
            val neighbors = mutableSetOf<Coords>()

            // up
            neighbors.add(Coords(location.first, max(0, location.second - 1)))
            // right
            neighbors.add(Coords(min(this.width - 1, location.first + 1), location.second))
            // down
            neighbors.add(Coords(location.first, min(this.height - 1, location.second + 1)))
            // left
            neighbors.add(Coords(max(0, location.first - 1), location.second))

            return neighbors.filter { it != location }
        }

        fun drawPath(minRiskLevelPath: List<Coords>) {
            buildString {
                appendLine()
                this@Cavern.grid.forEachIndexed { y, row ->
                    row.forEachIndexed { x, riskLevel ->
                        if (minRiskLevelPath.contains(Coords(x, y))) {
                            append("[")
                            append(riskLevel)
                            append("]")
                        } else {
                            append(" $riskLevel ")
                        }
                    }
                    appendLine()
                }
                logger.debug { this.toString() }
            }
        }
    }

    fun part1(input: List<String>): Int {
        val grid = input.mapIndexed { _, line ->
            line.mapIndexed { _, c ->
                c.digitToInt()
            }.toIntArray()
        }.toTypedArray()

        val cavern = Cavern(grid)
        val start = Coords(0, 0)
        val destination = Coords(cavern.width - 1, cavern.height - 1)

        val traversal = cavern.traverse(start, destination)
        cavern.drawPath(traversal.totalPath)

        return traversal.totalRisk
    }

    fun part2(input: List<String>): Int {
        val repeatX = 5
        val repeatY = 5

        val grid = input.mapIndexed { _, line ->
            line.mapIndexed { _, c ->
                c.digitToInt()
            }.toIntArray()
        }.toTypedArray()

        val width = grid[0].size
        val height = grid.size

        val fullMap = Array(repeatY * height) { IntArray(repeatX * width) { 0 } }

        for (y in 0 until height) {
            for (x in 0 until width) {
                val riskLevel = grid[y][x]

                for(yIter in 0 until repeatY) {
                    for(xIter in 0 until repeatX) {
                        val newValue = riskLevel + yIter + xIter
                        fullMap[yIter * height + y][xIter * width + x] = if (newValue > 9) {
                            newValue - (newValue / 9) * 9
                        } else {
                            newValue
                        }
                    }
                }
            }
        }

        val cavern = Cavern(fullMap)
        var start = Coords(0, 0)
        val destination = Coords(cavern.width - 1, cavern.height - 1)

        val traversal = cavern.traverse(start, destination)
        cavern.drawPath(traversal.totalPath)

        return traversal.totalRisk
    }
}
