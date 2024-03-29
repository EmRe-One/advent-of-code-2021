package tr.emreone.adventofcode.days

import tr.emreone.kotlin_utils.automation.Day
import tr.emreone.kotlin_utils.extensions.intersect
import tr.emreone.kotlin_utils.extensions.intersects
import tr.emreone.kotlin_utils.extensions.size


class Day22 : Day(22, 2021, "Reactor Reboot") {

    private class Cuboid(val on: Boolean, val x: IntRange, val y: IntRange, val z: IntRange) {
        fun volume(): Long =
            (if (on) 1 else -1) * (x.size().toLong() * y.size().toLong() * z.size().toLong())

        fun intersect(other: Cuboid): Cuboid? =
            if (!intersects(other)) null
            else Cuboid(!on, x intersect other.x, y intersect other.y, z intersect other.z)

        fun intersects(other: Cuboid): Boolean =
            x.intersects(other.x) && y.intersects(other.y) && z.intersects(other.z)

        companion object {
            const val ON = true
            const val OFF = false

            private val pattern =
                """^(on|off) x=(-?\d+)..(-?\d+),y=(-?\d+)..(-?\d+),z=(-?\d+)..(-?\d+)$""".toRegex()

            fun of(input: String): Cuboid {
                val (l, x1, x2, y1, y2, z1, z2) = pattern.matchEntire(input)?.destructured
                    ?: error("Cannot parse input: $input")
                return Cuboid(
                    l == "on",
                    x1.toInt()..x2.toInt(),
                    y1.toInt()..y2.toInt(),
                    z1.toInt()..z2.toInt(),
                )
            }
        }
    }

    private fun calcVolume(cuboids: List<Cuboid>): Long {
        val volumes = mutableListOf<Cuboid>()

        cuboids.forEach { cube ->
            volumes.addAll(volumes.mapNotNull { it.intersect(cube) })
            if (cube.on) {
                volumes.add(cube)
            }
        }

        return volumes.sumOf { it.volume() }
    }

    override fun part1(): Int {
        val initCuboid = Cuboid(Cuboid.ON, -50..50, -50..50, -50..50)
        val lights = inputAsList
            .map {
                Cuboid.of(it)
            }
            .filter {
                it.intersects(initCuboid)
            }

        return calcVolume(lights).toInt()
    }

    override fun part2(): Long {
        val lights = inputAsList.map { Cuboid.of(it) }

        return calcVolume(lights)
    }

}
