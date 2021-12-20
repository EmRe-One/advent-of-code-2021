package de.emreak.adventofcode.days

import AdventOfCodeUtils.logger

object Day18 {

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
                x = null
                y = null
                field = value
            }

        fun depth(): Int {
            var depth = 0
            var current = this
            while (current.parent != null) {
                depth++
                current = current.parent!!
            }
            return depth
        }

        fun getMagnitude(): Int {
            return if (regularNumber != null) {
                regularNumber!!
            } else {
                3 * x!!.getMagnitude() + 2 * y!!.getMagnitude()
            }
        }

        fun process(): Boolean {
            // explode

            // then splits
            return false
        }

        operator fun plus(other: SnailfishNumber): SnailfishNumber {
            return SnailfishNumber().also {
                it.x = this
                it.y = other
            }
        }

        override fun toString(): String {
            if (regularNumber != null) {
                return regularNumber.toString()
            } else {
                return "[${x.toString()},${y.toString()}]"
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

    fun part1(input: List<String>): Int {
        val s1 = SnailfishNumber.parse(input.first())

        logger.debug { "$s1" }
        return 0
    }

    fun part2(input: List<String>): Int {

        return 0
    }
}
