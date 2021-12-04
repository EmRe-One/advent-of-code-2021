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
        var oxygenGeneratorGroup: List<String> = list
        var co2ScrubberRatingGroup: List<String> = list

        var currentIndex = 0
        while (oxygenGeneratorGroup.size > 1) {
            var temp = oxygenGeneratorGroup.groupBy { it[currentIndex] }
            oxygenGeneratorGroup = if ( (temp['1']?.size ?: 0) >= (temp['0']?.size ?: 0)) {
                temp['1']!!
            } else {
                temp['0']!!
            }

            if (oxygenGeneratorGroup.size == 1) break else currentIndex++
        }

        currentIndex = 0
        while (co2ScrubberRatingGroup.size > 1) {
            var temp = co2ScrubberRatingGroup.groupBy { it[currentIndex] }
            co2ScrubberRatingGroup = if ( (temp['0']?.size ?: 0) <= (temp['1']?.size ?: 0)) {
                temp['0']!!
            } else {
                temp['1']!!
            }

            if (co2ScrubberRatingGroup.size == 1) break else currentIndex++
        }

        return oxygenGeneratorGroup[0].toInt(2) * co2ScrubberRatingGroup[0].toInt(2)
    }

}