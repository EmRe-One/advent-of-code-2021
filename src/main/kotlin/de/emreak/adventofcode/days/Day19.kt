package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils.logger
import de.emreak.adventofcode.Point3D
import de.emreak.adventofcode.permutations

object Day19 {

    private val transformations = listOf(0, 1, 2).permutations()

    private fun transformedPoint(point: Point3D, transformationId: Int): Point3D {
        assert(transformationId in 0 until 48)
        val transformedPoint = mutableListOf(point.x, point.y, point.z)

        // 0 - 1 - 0 - 1 - 0 - 1 - 0 - 1 ...
        if (transformationId % 2 == 1) {
            transformedPoint[0] *= -1
        }
        // 0 - 0 - 1 - 1 - 0 - 0 - 1 - 1 ...
        if ((transformationId / 2) % 2 == 1) {
            transformedPoint[1] *= -1
        }
        // 0 - 0 - 0 - 0 - 1 - 1 - 1 - 1 - 0 ...
        if ((transformationId / 4) % 2 == 1) {
            transformedPoint[2] *= -1
        }

        transformations[transformationId / 8].also { perm ->
            return Point3D(transformedPoint[perm[0]], transformedPoint[perm[1]], transformedPoint[perm[2]])
        }
    }

    class Scanner(input: List<String>) {
        val id: Int
        val beacons = mutableSetOf<Point3D>()
        var relativeOrigin: Point3D? = null
        var transformationId: Int = 0

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

        fun detectPositionRelativeTo(otherScanner: Scanner): Boolean {
            if (otherScanner.relativeOrigin == null) {
                return false
            }

            // try all possible directions
            directionLoop@
            for(direction in 0 until 48) {
                val transformedPoints = this.beacons.map { transformedPoint(it, direction) }

                ownBeaconsLoop@
                for(beacon in transformedPoints) {

                    otherBeaconsLoop@
                    for (otherBeacon in otherScanner.beacons) {
                        // assume that the beacon and otherBeacon are in the same position
                        // otherBeacon + transformeVector = beacon
                        val translateVector = beacon - otherBeacon

                        // now try to find at least 11 other matching beacons
                        val translatedPoints = transformedPoints.map { it - translateVector }

                        val numberOfMatches = translatedPoints.count {
                            it in otherScanner.beacons
                        }

                        if (numberOfMatches >= 12) {
                            this.transformationId = direction
                            translatedPoints.forEach {
                                otherScanner.beacons.add(it)
                            }
                            this.relativeOrigin = otherScanner.relativeOrigin!! - translateVector

                            return true
                        }
                    } // otherBeaconsLoop
                } // ownBeaconsLoop
            } // directionLoop

            return false
        }

        override fun toString(): String {
            return "Scanner $id (transformationId: $transformationId, relativ origin to Scanner 0: ${relativeOrigin})"
        }

    }

    fun part1(input: List<String>): Int {
        val scanner = mutableListOf<Scanner>()
        var lastIndex = 0
        var startIndex = 0
        while(lastIndex < input.size) {
            while(lastIndex < input.size && input[lastIndex].isNotBlank()) {
                lastIndex++
            }
            scanner.add(Scanner(input.subList(startIndex, lastIndex)))
            lastIndex++ // skip empty line
            startIndex = lastIndex
        }

        val scanner0 = scanner.first()
        scanner0.relativeOrigin = Point3D(0, 0, 0)

        val localizedScanner = mutableListOf(scanner0)
        val notLocalizedScanner = mutableListOf<Scanner>()
        notLocalizedScanner.addAll(scanner.drop(1))

        while(notLocalizedScanner.isNotEmpty()) {
            outerLoop@
            for(s in notLocalizedScanner) {
                for(ls in localizedScanner) {
                    if (s.detectPositionRelativeTo(ls)) {
                        localizedScanner.add(s)
                        notLocalizedScanner.remove(s)
                        break@outerLoop
                    }
                }
            }
        }

        val setOfBeaconsRelativToScanner0 = mutableSetOf<Point3D>()
        scanner0.beacons.forEach { setOfBeaconsRelativToScanner0.add(it) }

        for(s in localizedScanner) {
            s.beacons.forEach { setOfBeaconsRelativToScanner0.add(it) }
        }

        return setOfBeaconsRelativToScanner0.size
    }

    fun part2(input: List<String>): Int {

        return 0
    }
}
