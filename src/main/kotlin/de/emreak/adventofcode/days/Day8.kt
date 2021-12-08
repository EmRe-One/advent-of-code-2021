package de.emreak.adventofcode.days

object Day8 {

    /**
     *  00aa
     * 1    2
     * b    c
     *  33dd
     * 4    5
     * e    f
     *  66gg
     */
    class SevenSegmentDigit(val segments: CharArray) {
        companion object {
            fun parseFromCharPattern(pattern: String): SevenSegmentDigit {
                return SevenSegmentDigit(pattern
                    .map { it }
                    .sortedBy { it }
                    .toCharArray()
                )
            }
        }

        operator fun minus(other: SevenSegmentDigit): CharArray? {
            var resultArray = this.segments.map { it }.toList()
            
            return resultArray.toCharArray()
        }
    }

    class Entry(val inputs: List<SevenSegmentDigit>, val outputs: List<SevenSegmentDigit>) {

        val decoding = HashMap<CharArray, Char>()

        /*
        1 -->        2        5
        7 -->  0     2        5

        4 -->     1  2  3     5

        2 -->  0     2  3  4     6
        3 -->  0     2  3     5  6
        5 -->  0  1     3     5  6

        0 -->  0  1  2     4  5  6
        6 -->  0  1     3  4  5  6
        9 -->  0  1  2  3     5  6

        8 -->  0  1  2  3  4  5  6
         */

        companion object {
            fun parse(line: String): Entry {
                val parts = line.split("|")
                val inputs = parts[0].split(" ")
                    .map {
                        SevenSegmentDigit.parseFromCharPattern(it.trim())
                    }.sortedBy {
                        it.segments.size
                    }
                val outputs = parts[1].split(" ").map {
                    SevenSegmentDigit.parseFromCharPattern(it.trim())
                }

                return Entry(inputs, outputs)
            }
        }

        fun decode() {
            this.inputs.forEach {
                when (it.segments.size) {
                    2    -> this.decoding[it.segments] = '1'
                    3    -> this.decoding[it.segments] = '7'
                    4    -> this.decoding[it.segments] = '4'
                    7    -> this.decoding[it.segments] = '8'
                    else -> {} // do nothing for now
                }
            }

            for(i in )


        }

    }

    fun part1(input: List<String>): Int {
        return input.map {
            Entry.parse(it).outputs
        }.sumOf {
            it.filter { segment ->
                val length = segment.segments.size

                //Digits:  1   ----  7   -------    4    --------     8     have a unique pattern length
                length == 2 || length == 3 || length == 4 || length == 7
            }.size
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            val entry = Entry.parse(line)

            entry.decode()

            val outputString = entry.outputs.joinToString("") {
                entry.decoding[it.segments]?.toString() ?: "0"
            }

            outputString.toInt(10)
        }
    }
}
