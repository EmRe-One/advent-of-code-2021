package de.emreak.adventofcode.days

object Day19 {

    class Point3D(val x: Int, val y: Int, val z: Int) {

        fun distanceTo(other: Point3D): Triple<Int, Int, Int> {
            return Triple(x - other.x, y - other.y, z - other.z)
        }
    }

    class Scanner(input: List<String>) {
        private val id: Int
        private val detectedBeacons = mutableListOf<Point3D>()

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
                    detectedBeacons.add(Point3D(x, y, z))
                }
            }
        }
    }

    fun part1(input: List<String>): Int {
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
