package tr.emreone.adventofcode.days

import tr.emreone.kotlin_utils.automation.Day

class Day03 : Day(3, 2021, "Binary Diagnostic") {

    override fun part1(): Int {
        val inputLength = inputAsList[0].length

        val result = arrayOfNulls<String>(inputLength)

        for (i in 0 until inputLength) {
            val (onesAtI, zerosAtI) = inputAsList.partition { it[i] == '1' }

            result[i] = if (onesAtI.size >= zerosAtI.size) "1" else "0"
        }

        val gamma = result.joinToString(separator = "").toInt(2)
        val epsilon = result.joinToString(separator = "") { if (it == "1") "0" else "1" }.toInt(2)

        return gamma * epsilon
    }

    override fun part2(): Int {
        var oxygenGeneratorList: List<String> = inputAsList
        var i = 0
        while (oxygenGeneratorList.size > 1) {
            val (onesAtI, zerosAtI) = oxygenGeneratorList.partition { it[i] == '1' }

            oxygenGeneratorList = if (onesAtI.size >= zerosAtI.size) onesAtI else zerosAtI

            if (oxygenGeneratorList.size == 1) break else i++
        }

        var co2ScrubberRatingList: List<String> = inputAsList
        i = 0
        while (co2ScrubberRatingList.size > 1) {
            val (onesAtI, zerosAtI) = co2ScrubberRatingList.partition { it[i] == '1' }

            co2ScrubberRatingList = if (zerosAtI.size <= onesAtI.size) zerosAtI else onesAtI

            if (co2ScrubberRatingList.size == 1) break else i++
        }

        return oxygenGeneratorList[0].toInt(2) * co2ScrubberRatingList[0].toInt(2)
    }

}
