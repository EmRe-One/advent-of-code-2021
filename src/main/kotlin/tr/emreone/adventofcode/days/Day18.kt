package tr.emreone.adventofcode.days

import tr.emreone.kotlin_utils.automation.Day
import kotlin.math.ceil
import kotlin.math.floor

class Day18 : Day(18, 2021, "Snailfish") {

    class SnailfishNumber {
        var parent: SnailfishNumber? = null

        var x: SnailfishNumber? = null
            set(value) {
                field = value
                value?.parent = this
            }

        var y: SnailfishNumber? = null
            set(value) {
                field = value
                value?.parent = this
            }

        var regularNumber: Int? = null
            set(value) {
                if (value != null) {
                    x = null
                    y = null
                }
                field = value
            }

        private fun depth(): Int {
            var depth = 0
            var current = this
            while (current.parent != null) {
                depth++
                current = current.parent!!
            }
            return depth
        }

        private fun getFirstLeftSnailfish(): SnailfishNumber? {
            var child = this // have to be a regular number
            var parent = child.parent
            while (parent != null && parent.x == child) {
                child = parent
                parent = child.parent
            }

            if (parent?.x != null) {
                var result = parent.x
                while (result != null && result.regularNumber == null) {
                    result = result.y
                }
                return result
            } else {
                return null
            }
        }

        private fun getFirstRightSnailfish(): SnailfishNumber? {
            var child = this // have to be a regular number
            var parent = child.parent
            while (parent != null && parent.y == child) {
                child = parent
                parent = child.parent
            }

            if (parent?.y != null) {
                var result = parent.y
                while (result != null && result.regularNumber == null) {
                    result = result.x
                }
                return result
            } else {
                return null
            }
        }

        fun getMagnitude(): Int {
            return if (regularNumber != null) {
                regularNumber!!
            } else {
                3 * x!!.getMagnitude() + 2 * y!!.getMagnitude()
            }
        }

        fun reduceAll() {
            while (reduce()) {
                // do nothing
            }
        }

        private fun reduce(): Boolean {
            return explode() || split()
        }

        private fun explode(): Boolean {
            if (this.x != null && this.y != null && this.depth() >= 4) {
                var hasExploded = false
                val left = this.x!!
                val right = this.y!!

                // find left
                val leftSnailfish = left.getFirstLeftSnailfish()
                if (leftSnailfish != null) {
                    leftSnailfish.regularNumber = leftSnailfish.regularNumber!! + left.regularNumber!!
                    hasExploded = true
                }

                // find right
                val rightSnailFish = right.getFirstRightSnailfish()
                if (rightSnailFish != null) {
                    rightSnailFish.regularNumber = rightSnailFish.regularNumber!! + right.regularNumber!!
                    hasExploded = true
                }

                if (hasExploded) {
                    this.regularNumber = 0
                }

                return hasExploded
            } else {
                return this.x?.explode() ?: false || this.y?.explode() ?: false
            }
        }

        private fun split(): Boolean {
            return if (this.regularNumber != null && this.regularNumber!! >= 10) {
                val regNumber = this.regularNumber!!
                this.regularNumber = null
                this.x = SnailfishNumber().also { it.regularNumber = floor(regNumber / 2.0).toInt() }
                this.y = SnailfishNumber().also { it.regularNumber = ceil(regNumber / 2.0).toInt() }
                true
            } else {
                this.x?.split() ?: false || this.y?.split() ?: false
            }
        }

        operator fun plus(other: SnailfishNumber): SnailfishNumber {
            return SnailfishNumber().also {
                it.x = parse(this.toString())
                it.y = parse(other.toString())
            }
        }

        override fun toString(): String {
            return if (regularNumber != null) {
                regularNumber.toString()
            } else {
                "[${x.toString()},${y.toString()}]"
            }
        }

        companion object {
            fun parse(input: String): SnailfishNumber {
                return if (input[0] != '[') {
                    SnailfishNumber().also { it.regularNumber = input.toInt() }
                } else {
                    val endOfPair: Int = findClosingBraceIndex(input)
                    val pair = input.substring(0, endOfPair)
                    parsePair(pair)
                }
            }

            private fun findClosingBraceIndex(input: String): Int {
                // the first brace open at index 0
                var openBraces = 1
                for (i in 1 until input.length) {
                    if (input[i] == ']') {
                        openBraces--
                    } else if (input[i] == '[') {
                        openBraces++
                    }

                    if (openBraces == 0) {
                        return i + 1
                    }
                }
                throw RuntimeException()
            }

            private fun parsePair(input: String): SnailfishNumber {
                var openBraces = 1
                var commaIndex = -1

                for (i in 1 until input.length) {
                    when (input[i]) {
                        '[' -> openBraces++
                        ']' -> openBraces--
                        ',' -> if (openBraces == 1) {
                            commaIndex = i
                            break
                        }
                    }
                }

                val leftElement: String = input.substring(1, commaIndex)
                val rightElement: String = input.substring(commaIndex + 1, input.length - 1)

                return SnailfishNumber().also {
                    it.x = parse(leftElement)
                    it.y = parse(rightElement)
                }
            }
        }
    }

    override fun part1(): Int {
        var sum = SnailfishNumber.parse(inputAsList.first())

        for (i in 1 until inputAsList.size) {
            sum = sum + SnailfishNumber.parse(inputAsList[i])
            sum.reduceAll()
        }

        return sum.getMagnitude()
    }

    override fun part2(): Int {
        val possibleMagnitudes = mutableListOf<Int>()
        val numberOfSnailfishNumbers = inputAsList.size

        for (first in 0 until numberOfSnailfishNumbers - 1) {
            for (second in first + 1 until numberOfSnailfishNumbers) {
                val sum1 = SnailfishNumber.parse(inputAsList[first]) + SnailfishNumber.parse(inputAsList[second])
                sum1.reduceAll()
                possibleMagnitudes.add(sum1.getMagnitude())

                val sum2 = SnailfishNumber.parse(inputAsList[second]) + SnailfishNumber.parse(inputAsList[first])
                sum2.reduceAll()
                possibleMagnitudes.add(sum2.getMagnitude())
            }
        }

        return possibleMagnitudes.maxOf { it }
    }

}
