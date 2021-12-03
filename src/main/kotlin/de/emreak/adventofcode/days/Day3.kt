package de.emreak.adventofcode.days

object Day3 {

    fun part1(list: List<String>): Int {
        val inputLength = list[0].length

        val result = arrayOfNulls<String>(inputLength)

        for(i in 0 until inputLength) {
            val groups = list.groupBy { it[i] == '1' }

            result[i] = if (groups.getValue(true).size > groups.getValue(false).size) "1" else "0"
        }

        val gamma = result.joinToString(separator = "").toInt(2)
        val epsilon = result.joinToString(separator = "") { if (it == "1") "0" else "1" }.toInt(2)

        return gamma * epsilon
    }

    fun part2(list: List<String>): Int {
        // for the oxygen generator rating look at first digit --> 1 more then 0 --> filter ones
        // 11110, 10110, 10111, 10101, 11100, 10000, and 11001
        //         -      -      -             -
        // look at second digit --> 0 more then 1 --> filter
        // 10110, 10111, 10101, and 10000
        //   -      -      -

        // 10110, 10111, 10101
        //    _      _
        //
        // 10110, 10111,
        //            -  (at equality, select 1)
        // 10111
        val listLength = list.size
        val lineLength = list[0].length

        var oxygenGeneratorGroup: List<String> = mutableListOf()
        var co2ScrubberRatingGroup: List<String> = mutableListOf()

        var currentIndex = 0

        while (true) {
            var groups = list.groupBy { it[currentIndex] }

            var detectWhichDigit = if (groups['1']!!.size >= groups['0']!!.size) { '1' } else { '0' }
            oxygenGeneratorGroup = groups[detectWhichDigit]!!


            break
        }

        // val numberOfOnes = list.count { it[index] == '1' }
        // val detectedDigit = if (numberOfOnes >= listLength / 2) "1" else "0"



        return oxygenGeneratorGroup[0].toInt(2) * co2ScrubberRatingGroup[0].toInt(2)
    }

}