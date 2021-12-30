package de.emreak.adventofcode.days

import tr.emreone.utils.Logger.logger
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object Day17 {

    class Trajectory(val initialVelocity: Pair<Int, Int>, targetArea: TargetArea) {
        private val points = mutableListOf<Pair<Int, Int>>()

        init {
            var xPos = 0
            var yPos = 0
            var xV = initialVelocity.first
            var yV = initialVelocity.second

            this.points.add(Pair(xPos, yPos))
            while (true) {
                xPos += xV
                yPos += yV
                yV -= 1
                if (xV != 0) {
                    xV += if (xV > 0) -1 else 1
                }
                this.points.add(Pair(xPos, yPos))
                if (targetArea.isInRange(xPos, yPos)) {
                    break
                }
            }
        }

        fun getHighestPoint(): Int {
            return this.points.maxOf { it.second }
        }

        fun printTrajectoryWithTargetArea(target: TargetArea) {
            val yMax = max(this.points.maxOf { it.second }, target.yRange.last) + 1
            val yMin = min(this.points.minOf { it.second }, target.yRange.first) - 1
            val xMax = max(this.points.maxOf { it.first }, target.xRange.last) + 1
            val xMin = min(this.points.minOf { it.first }, target.xRange.first) - 1

            val path = buildString {
                appendLine()
                for (y in yMax downTo yMin) {
                    for (x in xMin..xMax) {
                        val current = Pair(x, y)

                        if (current == Pair(0, 0)) {
                            append("S")
                            continue
                        } else if (current in this@Trajectory.points) {
                            append("#")
                        } else if (target.isInRange(x, y)) {
                            append("T")
                        } else {
                            append(".")
                        }
                    }
                    appendLine()
                }
            }
            logger.debug { path }
        }
    }

    class TargetArea(val xRange: IntRange, val yRange: IntRange) {
        val possibleInitialVelocities = mutableSetOf<Pair<Int, Int>>()
        val trajectories = mutableListOf<Trajectory>()

        fun isInRange(x: Int, y: Int): Boolean {
            return xRange.contains(x) && yRange.contains(y)
        }

        private fun findPossibleInitialVelocities() {
            this.possibleInitialVelocities.clear()

            val xDirection = if (xRange.first < 0) -1 else 1

            for (initialYVelocity in yRange.first..(-1) * yRange.first) {
                for (initialXVelocity in xDirection..xRange.last) {
                    var steps = 0
                    var yPos = 0
                    var xPos = 0
                    var yV = initialYVelocity
                    var xV = initialXVelocity

                    while (true) {
                        steps++
                        yPos += yV
                        xPos += xV

                        if (yPos < yRange.first || abs(xPos) > abs(xRange.last)) {
                            break
                        } else if (isInRange(xPos, yPos)) {
                            this.possibleInitialVelocities.add(Pair(initialXVelocity, initialYVelocity))
                        } else {
                            yV += -1
                            if (xV != 0) {
                                xV += (-1) * xDirection
                            }
                        }
                    }
                }
            }
        }

        fun calcTrajectories() {
            this.findPossibleInitialVelocities()

            for ((xV, yV) in this.possibleInitialVelocities) {
                this.trajectories.add(Trajectory(Pair(xV, yV), this))
            }
        }
    }

    fun part1(targetArea: String): Int {
        val pattern = """target area: x=(-?\d+)..(-?\d+), y=(-?\d+)..(-?\d+)""".toRegex()
        val (xStart, xEnd, yStart, yEnd) = pattern.matchEntire(targetArea)!!.destructured

        val target = TargetArea(xStart.toInt()..xEnd.toInt(), yStart.toInt()..yEnd.toInt())
        target.calcTrajectories()

        val trajectory = target.trajectories
            .maxWithOrNull { t1, t2 ->
                t1.getHighestPoint() - t2.getHighestPoint()
            }!!
        trajectory.printTrajectoryWithTargetArea(target)

        return trajectory.getHighestPoint()
    }

    fun part2(targetArea: String): Int {
        val pattern = """target area: x=(-?\d+)..(-?\d+), y=(-?\d+)..(-?\d+)""".toRegex()
        val (xStart, xEnd, yStart, yEnd) = pattern.matchEntire(targetArea)!!.destructured

        val target = TargetArea(xStart.toInt()..xEnd.toInt(), yStart.toInt()..yEnd.toInt())
        target.calcTrajectories()

        return target.possibleInitialVelocities.size
    }
}
