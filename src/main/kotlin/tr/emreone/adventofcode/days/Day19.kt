package tr.emreone.adventofcode.days

import tr.emreone.kotlin_utils.Logger.logger
import tr.emreone.kotlin_utils.automation.Day
import tr.emreone.kotlin_utils.extensions.permutations
import tr.emreone.kotlin_utils.math.Point3D
import kotlin.math.max

class Day19 : Day(19, 2021, "Beacon Scanner") {

    class Scanner(input: List<String>) {
        val id: Int
        val beacons = mutableSetOf<Point3D>()
        var beaconsInGlobalCoord: MutableSet<Point3D>? = null
        var originInGlobalCoord: Point3D? = null
        var transformationId: Int = 0

        init {
            val idPattern = """--- scanner (\w+) ---""".toRegex()
            val idMatch = idPattern.matchEntire(input.first())!!
            id = idMatch.groupValues[1].toInt()

            val pointPattern = """(-?\d+),(-?\d+),(-?\d+)""".toRegex()

            for (line in input.drop(1)) {
                if (line.isNotBlank()) {
                    val pointMatch = pointPattern.matchEntire(line)!!
                    val x = pointMatch.groupValues[1].toLong()
                    val y = pointMatch.groupValues[2].toLong()
                    val z = pointMatch.groupValues[3].toLong()
                    beacons.add(Point3D(x, y, z))
                }
            }
        }

        private val transformations = listOf(0, 1, 2).permutations()

        fun transformPoint(point: Point3D, transformationId: Int): Point3D {
            assert(transformationId in 0 until 48)
            val transformedPoint = mutableListOf(point.x, point.y, point.z)

            // 0 - 1 - 0 - 1 - 0 - 1 - 0 - 1 ...
            if (transformationId % 2 == 1) {
                transformedPoint[0] = transformedPoint[0] * (-1)
            }
            // 0 - 0 - 1 - 1 - 0 - 0 - 1 - 1 ...
            if ((transformationId / 2) % 2 == 1) {
                transformedPoint[1] = transformedPoint[1] * (-1)
            }
            // 0 - 0 - 0 - 0 - 1 - 1 - 1 - 1 - 0 ...
            if ((transformationId / 4) % 2 == 1) {
                transformedPoint[2] = transformedPoint[2] * (-1)
            }

            transformations[transformationId / 8].also { perm ->
                return Point3D(
                    transformedPoint[perm[0]],
                    transformedPoint[perm[1]],
                    transformedPoint[perm[2]]
                )
            }
        }

        fun detectPositionWithHelpOf(otherScanner: Scanner): Boolean {
            if (otherScanner.originInGlobalCoord == null) {
                throw IllegalArgumentException("otherScanner.relativeOrigin is null")
            }

            // try all possible directions
            directionLoop@
            for (direction in 0 until 48) {
                val transformedPoints = this.beacons.map { transformPoint(it, direction) }

                ownBeaconsLoop@
                for (beacon in transformedPoints) {

                    otherBeaconsLoop@
                    for (otherBeacon in otherScanner.beaconsInGlobalCoord!!) {
                        // assume that the beacon and otherBeacon are in the same position
                        // otherBeacon + transformVector = beacon
                        val translateVector = beacon - otherBeacon

                        // now try to find at least 11 other matching beacons
                        val translatedPoints = transformedPoints.map { it - translateVector }

                        val numberOfMatches = translatedPoints.count {
                            it in otherScanner.beaconsInGlobalCoord!!
                        }

                        if (numberOfMatches >= 12) {
                            this.transformationId = direction
                            this.beaconsInGlobalCoord = translatedPoints.toMutableSet()

                            translatedPoints.forEach {
                                otherScanner.beaconsInGlobalCoord!!.add(it)
                            }
                            this.originInGlobalCoord = Point3D(0, 0, 0) - translateVector
                            logger.debug { "Locate $this" }
                            return true
                        }
                    } // otherBeaconsLoop
                } // ownBeaconsLoop
            } // directionLoop

            return false
        }

        override fun toString(): String {
            return "Scanner $id (transformationId: $transformationId, origin: ${originInGlobalCoord})"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Scanner

            return id == other.id
        }

        override fun hashCode(): Int {
            return id
        }
    }

    private val localizedScanner = mutableSetOf<Scanner>()

    override fun part1(): Int {
        val scanner = mutableListOf<Scanner>()
        var lastIndex = 0
        var startIndex = 0
        while (lastIndex < inputAsList.size) {
            while (lastIndex < inputAsList.size && inputAsList[lastIndex].isNotBlank()) {
                lastIndex++
            }
            scanner.add(Scanner(inputAsList.subList(startIndex, lastIndex)))
            lastIndex++ // skip empty line
            startIndex = lastIndex
        }

        val scanner0 = scanner.first()
        scanner0.originInGlobalCoord = Point3D(0, 0, 0)
        scanner0.beaconsInGlobalCoord = scanner0.beacons.toMutableSet()

        localizedScanner.add(scanner0)
        var notLocalizedScanner = mutableListOf<Scanner>().also { it.addAll(scanner.drop(1)) }

        while (notLocalizedScanner.isNotEmpty()) {
            val tempNotLocalized = notLocalizedScanner.toMutableSet()
            val tempLocalized = localizedScanner.toMutableSet()

            for (ls in localizedScanner) {
                for (nls in notLocalizedScanner) {
                    if (nls.originInGlobalCoord == null && nls.detectPositionWithHelpOf(ls)) {
                        tempLocalized.add(nls)
                        tempNotLocalized.remove(nls)
                    }
                }
            }

            notLocalizedScanner = tempNotLocalized.toMutableList()
            localizedScanner.addAll(tempLocalized.toMutableList())
        }

        val setOfBeaconsRelativToScanner0 = mutableSetOf<Point3D>()

        for (s in localizedScanner) {
            s.beaconsInGlobalCoord!!.forEach { setOfBeaconsRelativToScanner0.add(it) }
        }

        return setOfBeaconsRelativToScanner0.size
    }

    override fun part2(): Long {
        if (localizedScanner.isEmpty()) {
            this.part1()
        }

        var maxManhattanDistance = Long.MIN_VALUE
        for (s1 in 0 until (localizedScanner.size - 1)) {
            for (s2 in (s1 + 1) until localizedScanner.size) {
                val manhattanDistance = localizedScanner.elementAt(s1).originInGlobalCoord!!
                    .manhattanDistanceTo(localizedScanner.elementAt(s2).originInGlobalCoord!!)
                logger.debug {
                    "Distance between scanner ${localizedScanner.elementAt(s1).id} and scanner ${
                        localizedScanner.elementAt(
                            s2
                        ).id
                    }: $manhattanDistance"
                }
                maxManhattanDistance = max(maxManhattanDistance, manhattanDistance)
            }
        }

        return maxManhattanDistance
    }

}
