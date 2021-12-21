package de.emreak.adventofcode

import javax.naming.OperationNotSupportedException


open class Point3D(val x: Int, val y: Int, val z: Int) {
    fun getList(): List<Int> {
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


class Vector3D(val x: Int, val y:Int, val z:Int) {

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