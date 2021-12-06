package de.emreak.adventofcode.days

object Day3 {

    fun part1(list: List<String>): Int {
        val inputLength = list[0].length

        val result = arrayOfNulls<String>(inputLength)

        for(i in 0 until inputLength) {
            val (onesAtI, zerosAtI) = list.partition { it[i] == '1' }

            result[i] = if (onesAtI.size >= zerosAtI.size) "1" else "0"
        }

        val gamma = result.joinToString(separator = "").toInt(2)
        val epsilon = result.joinToString(separator = "") { if (it == "1") "0" else "1" }.toInt(2)

        return gamma * epsilon
    }

    fun part2(list: List<String>): Int {
        var oxygenGeneratorList: List<String> = list
        var i = 0
        while (oxygenGeneratorList.size > 1) {
            val (onesAtI, zerosAtI) = oxygenGeneratorList.partition { it[i] == '1' }

            oxygenGeneratorList = if ( onesAtI.size >= zerosAtI.size) onesAtI else zerosAtI

            if (oxygenGeneratorList.size == 1) break else i++
        }

        var co2ScrubberRatingList: List<String> = list
        i = 0
        while (co2ScrubberRatingList.size > 1) {
            val (onesAtI, zerosAtI) = co2ScrubberRatingList.partition { it[i] == '1' }

            co2ScrubberRatingList = if ( zerosAtI.size <= onesAtI.size) zerosAtI else onesAtI

            if (co2ScrubberRatingList.size == 1) break else i++
        }

        return oxygenGeneratorList[0].toInt(2) * co2ScrubberRatingList[0].toInt(2)
    }

}