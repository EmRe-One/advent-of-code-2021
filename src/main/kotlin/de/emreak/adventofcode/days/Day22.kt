package de.emreak.adventofcode.days

import de.emreak.adventofcode.Point3D
import kotlin.math.max
import kotlin.math.min

object Day22 {

    fun part1(input: List<String>): Int {
        val pattern = """^(on|off) x=(-?\d+)..(-?\d+),y=(-?\d+)..(-?\d+),z=(-?\d+)..(-?\d+)$""".toRegex()

        val lights = mutableSetOf<Point3D>()
        for(line in input) {
            val (action, x1, x2, y1, y2, z1, z2) = pattern.matchEntire(line)!!.destructured

            val xMin = max(-50, x1.toInt())
            val xMax = min(50, x2.toInt())
            val yMin = max(-50, y1.toInt())
            val yMax = min(50, y2.toInt())
            val zMin = max(-50, z1.toInt())
            val zMax = min(50, z2.toInt())

            for(x in xMin..xMax) {
                for(y in yMin..yMax) {
                    for(z in zMin..zMax) {
                        val point = Point3D(x, y, z)
                        if(action == "on") {
                            lights.add(point)
                        } else if (action == "off") {
                            lights.remove(point)
                        }
                    }
                }
            }
        }
        return lights.size
    }

    fun part2(input: List<String>): Int {
        val pattern = """^(on|off) x=(-?\d+)..(-?\d+),y=(-?\d+)..(-?\d+),z=(-?\d+)..(-?\d+)$""".toRegex()
        
        return 0
    }
}
