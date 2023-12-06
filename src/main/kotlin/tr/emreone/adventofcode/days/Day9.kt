package tr.emreone.adventofcode.days

import tr.emreone.utils.extensions.Coords

object Day9 {

    class Grid(var input: List<String>) {
        private val grid = mutableMapOf<Coords, Int>()
        private val width = input.first().length
        private val length = input.size

        init {
            input.forEachIndexed { y, line ->
                line.forEachIndexed { x, c ->
                    grid[Pair(x, y)] = c.digitToInt()
                }
            }
        }

        fun getLowPoints(): List<Pair<Coords, Int>> {
            return this.grid.toList().filter { pair ->
                var minValueInNeighborhood = 10
                val coords = pair.first

                if (coords.second > 0) { // not top edge
                    minValueInNeighborhood = minOf(minValueInNeighborhood, grid[coords.first to (coords.second - 1)]!!)
                }
                if (coords.first < width - 1) { // not right edge
                    minValueInNeighborhood = minOf(minValueInNeighborhood, grid[(coords.first + 1) to coords.second]!!)
                }
                if (coords.second < length - 1) { // not bottom edge
                    minValueInNeighborhood = minOf(minValueInNeighborhood, grid[coords.first to (coords.second + 1)]!!)
                }
                if (coords.first > 0) { // not left edge
                    minValueInNeighborhood = minOf(minValueInNeighborhood, grid[(coords.first - 1) to coords.second]!!)
                }

                pair.second < minValueInNeighborhood
            }
        }

        private fun addNeighborsToSet(current: Pair<Coords, Int>, coords: MutableSet<Coords>) {
            val x = current.first.first
            val y = current.first.second
            val height = current.second

            if (height > 8) {
                return
            } else {
                coords.add(current.first)
            }

            // not top edge
            if (y > 0 && grid[x to (y - 1)]!! > height) {
                val topNeighbor = (x to (y - 1)) to grid[x to (y - 1)]!!
                addNeighborsToSet(topNeighbor, coords)
            }
            // not right edge
            if (x < width - 1 && grid[(x + 1) to y]!! > height) {
                val rightNeighbor = (x + 1 to y) to grid[(x + 1) to y]!!
                addNeighborsToSet(rightNeighbor, coords)
            }
            // not bottom edge
            if (y < length - 1 && grid[x to (y + 1)]!! > height) {
                val bottomNeighbor = (x to (y + 1)) to grid[x to (y + 1)]!!
                addNeighborsToSet(bottomNeighbor, coords)
            }
            // not left edge
            if (x > 0 && grid[(x - 1) to y]!! > height) {
                val leftNeighbor = ((x - 1) to y) to grid[(x - 1) to y]!!
                addNeighborsToSet(leftNeighbor, coords)
            }
        }

        fun getBasins(): List<Set<Coords>> {
            val basins = mutableListOf<Set<Coords>>()

            getLowPoints().forEach {
                val coords = mutableSetOf<Coords>()
                addNeighborsToSet(it, coords)
                basins.add(coords)
            }

            return basins
        }
    }

    fun part1(input: List<String>): Int {
        val grid = Grid(input)
        return grid.getLowPoints().sumOf {
            it.second + 1
        }
    }

    fun part2(input: List<String>): Int {
        val grid = Grid(input)
        val basins = grid.getBasins()

        val sortedBasins = basins.sortedByDescending {
            it.size
        }

        return sortedBasins[0].size * sortedBasins[1].size * sortedBasins[2].size
    }
}
