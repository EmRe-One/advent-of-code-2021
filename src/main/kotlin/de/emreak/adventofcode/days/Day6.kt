package de.emreak.adventofcode.days

object Day6 {

    class LanternFish(private var internalTimer: Int) {
        private val children = mutableListOf<LanternFish>()

        fun tick() {
            for(child in children) {
                child.tick()
            }

            this.internalTimer--
            if (this.internalTimer == -1) {
                this.internalTimer = 6
                this.children.add(LanternFish(8))
            }
        }

        fun totalSum(): Int {
            return 1 + children.sumOf { it.totalSum() }
        }
    }

    fun part1(input: String, days: Int): Int {
        val fishes = input.split(",")
            .map {
                LanternFish(it.toInt())
            }.toMutableList()

        for (i in 0 until days) {
            for (fish in fishes) {
                fish.tick()
            }
        }

        return fishes.sumOf { it.totalSum() }
    }

    fun part2(input: String, days: Int): Int {
        return part1(input, days)
    }
}
