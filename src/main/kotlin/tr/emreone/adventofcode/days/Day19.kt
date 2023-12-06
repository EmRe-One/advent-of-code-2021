package tr.emreone.adventofcode.days

import tr.emreone.utils.Logger.logger
import tr.emreone.utils.extensions.permutations
import tr.emreone.utils.math.Point3D
import kotlin.math.max

object Day19 {

    private val transformations = listOf(0, 1, 2).permutations()

    private fun transformPoint(point: Point3D, transformationId: Int): Point3D {
        assert(transformationId in 0 until 48)
        val transformedPoint = mutableListOf(point.x.toInt(), point.y.toInt(), point.z.toInt())

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
            return Point3D(transformedPoint[perm[0]].toLong(), transformedPoint[perm[1]].toLong(), transformedPoint[perm[2]].toLong())
        }
    }

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

        fun detectPositionWithHelpOf(otherScanner: Scanner): Boolean {
            if (otherScanner.originInGlobalCoord == null) {
                throw IllegalArgumentException("otherScanner.relativeOrigin is null")
            }

            // try all possible directions
            directionLoop@
            for(direction in 0 until 48) {
                val transformedPoints = this.beacons.map { transformPoint(it, direction) }

                ownBeaconsLoop@
                for(beacon in transformedPoints) {

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

            if (id != other.id) return false

            return true
        }

        override fun hashCode(): Int {
            return id
        }
    }

    private val localizedScanner = mutableSetOf<Scanner>()

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
        scanner0.originInGlobalCoord = Point3D(0, 0, 0)
        scanner0.beaconsInGlobalCoord = scanner0.beacons.toMutableSet()

        localizedScanner.add(scanner0)
        var notLocalizedScanner = mutableListOf<Scanner>().also { it.addAll(scanner.drop(1)) }

        while(notLocalizedScanner.isNotEmpty()) {
            val tempNotLocalized = notLocalizedScanner.toMutableSet()
            val tempLocalized = localizedScanner.toMutableSet()

            for(ls in localizedScanner) {
                for(nls in notLocalizedScanner) {
                    if(nls.originInGlobalCoord == null && nls.detectPositionWithHelpOf(ls)) {
                        tempLocalized.add(nls)
                        tempNotLocalized.remove(nls)
                    }
                }
            }

            notLocalizedScanner = tempNotLocalized.toMutableList()
            localizedScanner.addAll(tempLocalized.toMutableList())
        }

        val setOfBeaconsRelativToScanner0 = mutableSetOf<Point3D>()

        for(s in localizedScanner) {
            s.beaconsInGlobalCoord!!.forEach { setOfBeaconsRelativToScanner0.add(it) }
        }

        return setOfBeaconsRelativToScanner0.size
    }

    fun part2(input: List<String>): Long {
        if (localizedScanner.isEmpty()) {
            part1(input)
        }

        var maxManhattanDistance = Long.MIN_VALUE
        for(s1 in 0 until (localizedScanner.size - 1)) {
            for(s2 in (s1 + 1) until localizedScanner.size) {
                val manhattanDistance = localizedScanner.elementAt(s1).originInGlobalCoord!!
                    .manhattanDistanceTo(localizedScanner.elementAt(s2).originInGlobalCoord!!)
                logger.debug { "Distance between scanner ${localizedScanner.elementAt(s1).id} and scanner ${localizedScanner.elementAt(s2).id}: $manhattanDistance" }
                maxManhattanDistance = max(maxManhattanDistance, manhattanDistance)
            }
        }

        return maxManhattanDistance
    }
}
