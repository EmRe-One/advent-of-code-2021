package tr.emreone.adventofcode.days

import tr.emreone.kotlin_utils.automation.Day

class Day05 : Day(5, 2021, "Hydrothermal Venture") {

    class Point(var x: Int, var y: Int) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Point

            if (x != other.x) return false
            if (y != other.y) return false

            return true
        }

        override fun hashCode(): Int {
            var result = x
            result = 31 * result + y
            return result
        }
    }

    enum class Direction {
        HORIZONTAL, VERTICAL, DIAGONAL
    }

    class Line(val start: Point, val end: Point) {
        val direction: Direction = when {
            start.x == end.x -> Direction.VERTICAL
            start.y == end.y -> Direction.HORIZONTAL
            else -> Direction.DIAGONAL
        }
        val pointSet: MutableSet<Point> = mutableSetOf()

        init {
            var currentPoint = Point(start.x, start.y)
            var directionX = if (end.x >= start.x) 1 else -1
            var directionY = if (end.y >= start.y) 1 else -1

            // add start
            pointSet.add(Point(currentPoint.x, currentPoint.y))
            while (currentPoint != end) {
                when (direction) {
                    Direction.HORIZONTAL -> currentPoint.x += directionX
                    Direction.VERTICAL -> currentPoint.y += directionY
                    Direction.DIAGONAL -> {
                        currentPoint.x += directionX
                        currentPoint.y += directionY
                    }
                }
                pointSet.add(Point(currentPoint.x, currentPoint.y))
            }
        }

        fun intersects(other: Line): Boolean {
            return pointSet.intersect(other.pointSet).isNotEmpty()
        }
    }

    private fun countOverlaps(list: List<String>, ignoreDiagonals: Boolean = true, atLeast: Int = 2): Int {
        val regex = """^(?<P1x>\d+),(?<P1y>\d+) -> (?<P2x>\d+),(?<P2y>\d+)$""".toRegex()

        val lines = list.map { line ->
            val (p1x, p1y, p2x, p2y) = regex.find(line)!!.destructured

            Line(Point(p1x.toInt(), p1y.toInt()), Point(p2x.toInt(), p2y.toInt()))
        }

        val map = mutableMapOf<Point, Int>()

        for (line in lines) {
            if (ignoreDiagonals && line.direction == Direction.DIAGONAL) continue

            for (point in line.pointSet) {
                map[point] = map.getOrDefault(point, 0) + 1
            }
        }

        return map.count {
            it.value >= atLeast
        }
    }

    override fun part1(): Int {
        return countOverlaps(inputAsList)
    }

    override fun part2(): Int {
        return countOverlaps(inputAsList, false, 2)
    }

}
