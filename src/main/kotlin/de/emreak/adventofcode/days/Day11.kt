package de.emreak.adventofcode.days

object Day11 {

    class Octopus(var energyLevel: Int = 0) {
        var isFlashing = false

        fun increaseEnergyLevel() {
            energyLevel++
        }
    }

    class OctopusField(input: List<String>) {
        var currentStep = 0

        private val width = input[0].length
        private val height = input.size

        val grid: List<List<Octopus>> = input.map { line ->
            line.map { energyLevel ->
                Octopus(energyLevel.code - 48)
            }
        }

        private fun increasingPhase() {
            for (x in 0 until width) {
                for (y in 0 until height) {
                    grid[x][y].increaseEnergyLevel()
                }
            }
        }

        private fun flashingPhase() {
            for (x in 0 until width) {
                for (y in 0 until height) {
                    if (grid[x][y].energyLevel > 9 && !grid[x][y].isFlashing) {
                        letOctopusesFlash(x, y)
                    }
                }
            }
        }

        private fun letOctopusesFlash(x: Int, y: Int) {
            this.grid[x][y].isFlashing = true

            val neighbors = getNeighbors(x, y)

            for ((nx, ny) in neighbors) {
                if (!grid[nx][ny].isFlashing) {
                    grid[nx][ny].increaseEnergyLevel()
                    if (grid[nx][ny].energyLevel > 9) {
                        letOctopusesFlash(nx, ny)
                    }
                }
            }
        }

        private fun getNeighbors(x: Int, y: Int): List<Pair<Int, Int>> {
            val neighbors = mutableListOf<Pair<Int, Int>>()
            // left column
            if (x > 0) {
                if (y > 0) {
                    neighbors.add(Pair(x - 1, y - 1))
                }
                neighbors.add(Pair(x - 1, y))
                if (y < height - 1) {
                    neighbors.add(Pair(x - 1, y + 1))
                }
            }

            // middle column
            if (y > 0) {
                neighbors.add(Pair(x, y - 1))
            }
            neighbors.add(Pair(x, y))
            if (y < height - 1) {
                neighbors.add(Pair(x, y + 1))
            }

            // right column
            if (x < width - 1) {
                if (y > 0) {
                    neighbors.add(Pair(x + 1, y - 1))
                }
                neighbors.add(Pair(x + 1, y))
                if (y < height - 1) {
                    neighbors.add(Pair(x + 1, y + 1))
                }
            }

            return neighbors
        }

        private fun resetPhase(): Int {
            var numberOfFlashingOctopus = 0
            for (x in 0 until width) {
                for (y in 0 until height) {
                    if (grid[x][y].isFlashing) {
                        numberOfFlashingOctopus++
                        grid[x][y].energyLevel = 0
                        grid[x][y].isFlashing = false
                    }
                }
            }
            return numberOfFlashingOctopus
        }

        fun stepForward(): Int {
            currentStep++
            increasingPhase()
            flashingPhase()
            return resetPhase()
        }

        fun printFieldInCurrentStep() {
            println("Step: $currentStep")
            println("+-------------------+")
            for (y in 0 until height) {
                grid[y].map { it.energyLevel }.joinToString(" ", prefix = "|", postfix = "|").let { println(it) }
            }
            println("+-------------------+")
        }
    }

    fun part1(input: List<String>): Int {
        val field = OctopusField(input)
        var sumOfFlashes = 0
        repeat(100) {
            sumOfFlashes += field.stepForward()
        }
        return sumOfFlashes
    }

    fun part2(input: List<String>): Int {
        val field = OctopusField(input)
        while (true) {
            val totalSum = field.grid.sumOf {
                it.sumOf { octopus ->
                    octopus.energyLevel
                }
            }

            if (totalSum == 0) {
                break
            }
            field.stepForward()
        }
        return field.currentStep
    }
}
