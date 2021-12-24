package de.emreak.adventofcode

import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.sign

class Point2D(val x: Int, val y: Int) {

    infix fun sharesAxisWith(that: Point2D): Boolean =
        x == that.x || y == that.y

    infix fun lineTo(that: Point2D): List<Point2D> {
        val xDelta = (that.x - x).sign
        val yDelta = (that.y - y).sign
        val steps = maxOf((x - that.x).absoluteValue, (y - that.y).absoluteValue)
        return (1..steps).scan(this) { last, _ ->
            Point2D(last.x + xDelta, last.y + yDelta)
        }
    }

    fun neighbors(): List<Point2D> =
        listOf(
            Point2D(x, y - 1),  // north
            Point2D(x + 1, y),  // east
            Point2D(x, y + 1),  // south
            Point2D(x - 1, y)   // west
        )

    fun allNeighbors(): List<Point2D> =
        neighbors() + listOf(
            Point2D(x + 1, y - 1),  // north-east
            Point2D(x + 1, y + 1),  // south-east
            Point2D(x - 1, y + 1),  // south-west
            Point2D(x - 1, y - 1)   // north-west
        )

}

class Point3D(val x: Int, val y: Int, val z: Int) {

    fun manhattanDistanceTo(other: Point3D): Int {
        return abs(x - other.x) + abs(y - other.y) + abs(z - other.z)
    }

    fun asList(): List<Int> {
        return listOf(x, y, z)
    }

    operator fun plus(translate: Vector3D): Point3D {
        return Point3D(x + translate.x, y + translate.y, z + translate.z)
    }

    operator fun minus(translate: Vector3D): Point3D {
        return Point3D(x - translate.x, y - translate.y, z - translate.z)
    }

    operator fun minus(other: Point3D): Vector3D {
        return Vector3D(x - other.x, y - other.y, z - other.z)
    }

    fun face(facing: Int): Point3D =
        when (facing) {
            0 -> this
            1 -> Point3D(x, -y, -z)
            2 -> Point3D(x, -z, y)
            3 -> Point3D(-y, -z, x)
            4 -> Point3D(y, -z, -x)
            5 -> Point3D(-x, -z, -y)
            else -> error("Invalid facing")
        }

    fun rotate(rotating: Int): Point3D =
        when (rotating) {
            0 -> this
            1 -> Point3D(-y, x, z)
            2 -> Point3D(-x, -y, z)
            3 -> Point3D(y, -x, z)
            else -> error("Invalid rotation")
        }

    override fun toString(): String {
        return "($x|$y|$z)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point3D

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + z
        return result
    }

}

class Vector3D(val x: Int, val y: Int, val z: Int) {

    operator fun plus(other: Vector3D): Vector3D {
        return Vector3D(x + other.x, y + other.y, z + other.z)
    }

    override fun toString(): String {
        return "[$x,$y,$z)]"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vector3D

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + z
        return result
    }
}
