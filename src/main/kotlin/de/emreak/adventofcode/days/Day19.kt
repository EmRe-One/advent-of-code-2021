package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils.logger
import de.emreak.adventofcode.permutations

object Day19 {
    class Point3D(val x: Int, val y: Int, val z: Int) {

        fun getList(): List<Int> {
            return listOf(x, y, z)
        }

        fun distanceTo(other: Point3D): Triple<Int, Int, Int> {
            return Triple(x - other.x, y - other.y, z - other.z)
        }

        override fun toString(): String {
            return "($x, $y, $z)"
        }

        companion object {

            fun transformedPoint(point: Point3D, transformationId: Int): Point3D {
                assert(transformationId in 0..48)
                val newPoint = mutableListOf(point.x, point.y, point.z)

                if (transformationId % 2 == 1) {
                    newPoint[0] *= -1
                }
                if ((transformationId / 2) % 2 == 1) {
                    newPoint[1] *= -1
                }
                if ((transformationId / 4) % 2 == 1) {
                    newPoint[2] *= -1
                }

                listOf(0, 1, 2).permutations().forEachIndexed { index, perm ->
                    if ((transformationId / 8) == index) {
                        return Point3D( newPoint[perm[0]], newPoint[perm[1]], newPoint[perm[2]])
                    }
                }
                throw IllegalStateException("Transformed point not found")
            }

        }
    }

    class Scanner(input: List<String>) {
        private val id: Int
        private val beacons = mutableListOf<Point3D>()
        var origin: Point3D? = null
        var located: Boolean = false

        init {
            val idPattern = """--- scanner (\w+) ---""".toRegex()
            val idMatch = idPattern.matchEntire(input.first())!!
            id = idMatch.groupValues[1].toInt()

            val pointPattern = """(-?\d+),(-?\d+),(-?\d+)""".toRegex()

            for (line in input.drop(1)) {
                if (line.isNotBlank()) {
                    val pointMatch = pointPattern.matchEntire(line)!!
                    val x = pointMatch.groupValues[1].toInt()
                    val y = pointMatch.groupValues[2].toInt()
                    val z = pointMatch.groupValues[3].toInt()
                    beacons.add(Point3D(x, y, z))
                }
            }
        }
    }

    fun part1(input: List<String>): Int {
        val point = Point3D(5, 10, 15)

        for(i in 0..48) {
            val transformedPoint = Point3D.transformedPoint(point, i)
            logger.debug { "Transformed point: $transformedPoint" }
        }

        val inputCopy = input.toMutableList()
        if (inputCopy.last().isNotBlank()) {
            inputCopy.add("")
        }
        val scanner = mutableListOf<Scanner>()
        var lastIndex = 0
        var startIndex = 0
        while(lastIndex < inputCopy.size) {
            while(inputCopy[lastIndex].isNotBlank()) {
                lastIndex++
            }
            scanner.add(Scanner(inputCopy.subList(startIndex, lastIndex)))
            lastIndex++ // skip empty line
            startIndex = lastIndex
        }

        return 0
    }

    fun part2(input: List<String>): Int {

        return 0
    }
}
