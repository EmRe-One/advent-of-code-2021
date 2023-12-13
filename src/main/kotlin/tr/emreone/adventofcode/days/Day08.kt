package tr.emreone.adventofcode.days

import tr.emreone.kotlin_utils.automation.Day

class Day08 : Day(8, 2021, "Seven Segment Search") {

    /**
     *  00aa
     * 1    2
     * b    c
     *  33dd
     * 4    5
     * e    f
     *  66gg
     *
     *   0abcdefg
     *   0a000000 ---> 0x1 shl 6 = a
     *   00b00000 ---> 0x1 shl 5 = b
     *   000c0000 ---> 0x1 shl 4 = c
     *   0000d000 ---> 0x1 shl 3 = d
     *   00000e00 ---> 0x1 shl 2 = e
     *   000000f0 ---> 0x1 shl 1 = f
     *   0000000g ---> 0x1 shl 0 = g
     */
    class SevenSegmentDigit(var boolRepresentation: Int) {
        companion object {
            val alphabet = mapOf(
                'a' to (0x1 shl 6),
                'b' to (0x1 shl 5),
                'c' to (0x1 shl 4),
                'd' to (0x1 shl 3),
                'e' to (0x1 shl 2),
                'f' to (0x1 shl 1),
                'g' to (0x1 shl 0)
            )

            fun parseFromCharPattern(pattern: String): SevenSegmentDigit {
                var boolRepresentation = 0

                for (char in pattern) {
                    boolRepresentation = boolRepresentation or alphabet[char]!!
                }

                return SevenSegmentDigit(boolRepresentation)
            }
        }

        fun numberOfActivatedSegments(): Int {
            return Integer.bitCount(boolRepresentation)
        }

        infix fun and(other: SevenSegmentDigit): SevenSegmentDigit {
            return SevenSegmentDigit(boolRepresentation and other.boolRepresentation)
        }

        infix fun or(other: SevenSegmentDigit): SevenSegmentDigit {
            return SevenSegmentDigit(boolRepresentation or other.boolRepresentation)
        }

        fun without(other: SevenSegmentDigit): SevenSegmentDigit {
            return SevenSegmentDigit(boolRepresentation and other.boolRepresentation.inv())
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as SevenSegmentDigit

            return boolRepresentation == other.boolRepresentation
        }

        override fun hashCode(): Int {
            return boolRepresentation
        }
    }

    class Entry(val inputs: List<SevenSegmentDigit>, val outputs: List<SevenSegmentDigit>) {

        val decoding = HashMap<SevenSegmentDigit, Int>()

        companion object {
            fun parse(line: String): Entry {
                val parts = line.split("|")
                val inputs = parts[0].trim()
                    .split(" ")
                    .map {
                        SevenSegmentDigit.parseFromCharPattern(it.trim())
                    }
                val outputs = parts[1].trim()
                    .split(" ")
                    .map {
                        SevenSegmentDigit.parseFromCharPattern(it.trim())
                    }

                return Entry(inputs, outputs)
            }
        }

        fun decode() {
            val t1 = this.inputs.first { it.numberOfActivatedSegments() == 2 } // -> 1
            val t2 = this.inputs.first { it.numberOfActivatedSegments() == 3 } // -> 7
            val t3 = this.inputs.first { it.numberOfActivatedSegments() == 4 } // -> 4
            val t4 = this.inputs.first { it.numberOfActivatedSegments() == 7 } // -> 8

            // 0x7F -> 0111 1111 -> all segments light up
            var t5 = SevenSegmentDigit(0x7F)
            this.inputs.filter { it.numberOfActivatedSegments() == 5 }.forEach { it ->
                t5 = t5 and it
            }
            var t6 = SevenSegmentDigit(0x7F)
            this.inputs.filter { it.numberOfActivatedSegments() == 6 }.forEach { it ->
                t6 = t6 and it
            }

            val s1 = t2.without(t1)
            val s2 = t6.without(t5).without(t1)
            val s3 = t3.without(t5).without(t6)
            val s4 = t3 and t5
            val s5 = t4.without(t3).without(t5)
            val s6 = t6.without(t5).without(s2)
            val s7 = t4.without(s1 or s2 or s3 or s4 or s5 or s6)

            decoding[s1 or s2 or s3 or s5 or s6 or s7] = 0
            decoding[s3 or s6] = 1
            decoding[s1 or s3 or s4 or s5 or s7] = 2
            decoding[s1 or s3 or s4 or s6 or s7] = 3
            decoding[s2 or s3 or s4 or s6] = 4
            decoding[s1 or s2 or s4 or s6 or s7] = 5
            decoding[s1 or s2 or s4 or s5 or s6 or s7] = 6
            decoding[s1 or s3 or s6] = 7
            decoding[s1 or s2 or s3 or s4 or s5 or s6 or s7] = 8
            decoding[s1 or s2 or s3 or s4 or s6 or s7] = 9
        }

    }

    override fun part1(): Int {
        return inputAsList.map {
            Entry.parse(it).outputs
        }.sumOf {
            it.filter { segment ->
                val length = segment.numberOfActivatedSegments()

                //Digits:  1   ----  7   -------    4    --------     8     have a unique pattern length
                length == 2 || length == 3 || length == 4 || length == 7
            }.size
        }
    }

    override fun part2(): Int {
        return inputAsList.sumOf { line ->
            val entry = Entry.parse(line)

            entry.decode()

            val outputString = entry.outputs.joinToString("") {
                entry.decoding[it]?.toString() ?: "?"
            }
            outputString.toInt(10)
        }
    }
}
