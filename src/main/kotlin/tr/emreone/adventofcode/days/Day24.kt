package tr.emreone.adventofcode.days

import tr.emreone.kotlin_utils.automation.Day

class Day24 : Day(24, 2021, "Arithmetic Logic Unit") {

    class Parameters(val a: Int, val b: Int)
    class QueueItem(val digitIndex: Int, val addend: Int)

    /*
     * 0    inp w           inp w           inp w           ....
     * 1    mul x 0         mul x 0         mul x 0
     * 2    add x z         add x z         add x z
     * 3    mod x 26        mod x 26        mod x 26
     * 4    div z 1         div z 1         div z 1         <-- parameter a
     * 5    add x 13        add x 12        add x 10
     * 6    eql x w         eql x w         eql x w
     * 7    eql x 0         eql x 0         eql x 0
     * 8    mul y 0         mul y 0         mul y 0
     * 9    add y 25        add y 25        add y 25
     * 10   mul y x         mul y x         mul y x
     * 11   add y 1         add y 1         add y 1
     * 12   mul z y         mul z y         mul z y
     * 13   mul y 0         mul y 0         mul y 0
     * 14   add y w         add y w         add y w
     * 15   add y 8         add y 16        add y 4         <-- parameter b
     * 16   mul y x         mul y x         mul y x
     * 17   add z y         add z y         add z y
     *
     * split input to chunked blocks with a size of 18
     * only lines with index 5 and 15 are relevant
     */
    class ALU(input: List<String>) {
        private val parameters: List<Parameters>

        init {
            this.parameters = input.chunked(18).map { lines ->
                val a = lines[5].substringAfterLast(" ").toInt()
                val b = lines[15].substringAfterLast(" ").toInt()
                Parameters(a, b)
            }
        }

        fun findModelNumber(largest: Boolean = true): Long {
            val queue = ArrayDeque<QueueItem>()
            val digits = Array(14) { 0 }

            parameters.forEachIndexed { digitIndex, parameters ->
                if (parameters.a >= 10) {
                    queue.add(QueueItem(digitIndex, parameters.b))
                } else {
                    val popped = queue.removeLast()
                    val addend = popped.addend + parameters.a
                    val digit = (if (largest) 9 downTo 1 else 1..9).first {
                        it + addend in 1..9
                    }
                    digits[popped.digitIndex] = digit
                    digits[digitIndex] = digit + addend
                }
            }

            // convert digits to a number with the horners rule
            // i.e. ( 1 2 3 ) -> (((0L + 1) * 10 + 2) * 10 + 3) = 123
            //                           ‾         ‾         ‾
            return digits.fold(0L) { acc, d -> acc * 10 + d }
        }
    }

    override fun part1(): Long {
        val alu = ALU(inputAsList)
        return alu.findModelNumber()
    }

    override fun part2(): Long {
        val alu = ALU(inputAsList)
        return alu.findModelNumber(largest = false)
    }

}
