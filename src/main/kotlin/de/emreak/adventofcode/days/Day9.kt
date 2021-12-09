package de.emreak.adventofcode.days

object Day9 {

    fun part1(input: List<String>): Int {
        val width = input.first().length
        val length = input.size
        val total = width * length

        val flatInput = input.joinToString("") {
            it.trim()
        }.map {
            it.code - 48
        }

        return flatInput.filterIndexed { index, height ->
            var minValueInNeighborhood = 10

            val isTopEdge = (index < width)
            val isRightEdge = (index % width == width - 1)
            val isBottomEdge = (index >= total - width)
            val isLeftEdge = (index % width == 0)

            if (!isTopEdge) {
                minValueInNeighborhood = minOf(minValueInNeighborhood, flatInput[index - width])
            }
            if (!isRightEdge) {
                minValueInNeighborhood = minOf(minValueInNeighborhood, flatInput[index + 1])
            }
            if (!isBottomEdge) {
                minValueInNeighborhood = minOf(minValueInNeighborhood, flatInput[index + width])
            }
            if (!isLeftEdge){
                minValueInNeighborhood = minOf(minValueInNeighborhood, flatInput[index - 1])
            }

            height < minValueInNeighborhood
        }.sumOf { it + 1 }
    }

    fun part2(input: List<String>): Int {

        return 0
    }
}
