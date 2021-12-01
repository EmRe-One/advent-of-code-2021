package days.day1

import helper.FileReader

object Day1 {

    fun solveExercise1(): Int {
        val list = FileReader.readLinesToNumberList("./src/days/day1/input1.txt")

        var result = 0
        list.forEachIndexed { index, i ->
            if (index + 1 < list.size) {
                if (i < list[index + 1]) {
                    result += 1
                }
            }
        }
        return result
    }

    fun solveExercise2(): Int {
        val input = FileReader.readLinesToNumberList("./src/days/day1/input1.txt")
        val map = mutableMapOf<Int, MutableList<Int>>()

        input.forEachIndexed { index, i ->
            val key1 = ((index / 4)) * 4
            val key2 = ((index - 1) / 4) * 4 + 1
            val key3 = ((index - 2) / 4) * 4 + 2
            val key4 = ((index - 3) / 4) * 4 + 3

            if ( (index + 1) % 4 != 0 && index >= 0) {
                map.getOrPut(key1) { mutableListOf() }.add(i)
            }
            if ( (index + 0) % 4 != 0 && index >= 1) {
                map.getOrPut(key2) { mutableListOf() }.add(i)
            }
            if ( (index + 3) % 4 != 0 && index >= 2) {
                map.getOrPut(key3) { mutableListOf() }.add(i)
            }
            if ( (index + 2) % 4 != 0 && index >= 3) {
                map.getOrPut(key4) { mutableListOf() }.add(i)
            }
        }

        val list = map.values.map {
            if (it.size == 3) {
                it.sum()
            } else {
                null
            }
        }.toList()

        var result = 0
        list.forEachIndexed { index, i ->
            if (index + 1 < list.size) {
                if (i != null && list[index + 1] != null && i < list[index + 1]!!) {
                    result += 1
                }
            }
        }
        return result
    }

}
