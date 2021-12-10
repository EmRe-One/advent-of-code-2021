package de.emreak.adventofcode.days

typealias Coords = Pair<Int, Int>

object Day9 {

    class Grid(input: List<String>) {
        private val grid = mutableMapOf<Coords, Int>()
        val width = input.first().length
        val length = input.size
        val total = width * length

        init {
            input.forEachIndexed { y, line ->
                line.forEachIndexed { x, c ->
                    grid[Pair(x, y)] = c.code - 48
                }
            }
        }

        fun getLowPoints(): List<Pair<Coords, Int>> {
            return this.grid.toList().filter { pair ->
                var minValueInNeighborhood = 10
                var coords = pair.first

                if (coords.second > 0) { // not top edge
                    minValueInNeighborhood = minOf(minValueInNeighborhood, grid[coords.first to (coords.second - 1)]!!)
                }
                if (coords.first < width - 1) { // not right edge
                    minValueInNeighborhood = minOf(minValueInNeighborhood, grid[coords.first + 1 to coords.second]!!)
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

        private fun addNeighboorsToSet(current: Pair<Coords, Int>, coords: MutableSet<Coords>) {
            val x = current.first.first
            val y = current.first.second
            val height = current.second

            if (height == 9) {
                return
            } else {
                coords.add(current.first)
            }

            if (y > 0) { // not top edge
                val topNeighbor = (x to (y - 1)) to grid[x to (y - 1)]!!
                if (topNeighbor.second == height + 1) {
                    addNeighboorsToSet(topNeighbor, coords)
                }
            }
            if (x < width - 1) { // not right edge
                val rightNeighbor = (x + 1 to y) to grid[(x + 1) to y]!!
                if (rightNeighbor.second == height + 1) {
                    addNeighboorsToSet(rightNeighbor, coords)
                }
            }
            if (y < length - 1) { // not bottom edge
                val bottomNeighbor = (x to (y + 1)) to grid[x to (y + 1)]!!
                if (bottomNeighbor.second == height + 1) {
                    addNeighboorsToSet(bottomNeighbor, coords)
                }
            }
            if (x > 0) { // not left edge
                val leftNeighbor = ((x - 1) to y) to grid[(x - 1) to y]!!
                if (leftNeighbor.second == height + 1) {
                    addNeighboorsToSet(leftNeighbor, coords)
                }
            }
        }

        fun getBasins(): List<Set<Coords>> {
            val baseins = mutableListOf<Set<Coords>>()

            getLowPoints().forEach {
                val coords = mutableSetOf<Coords>()
                addNeighboorsToSet(it, coords)
                baseins.add(coords)
            }

            return baseins
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
