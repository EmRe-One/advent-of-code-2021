package tr.emreone.adventofcode.days

import tr.emreone.kotlin_utils.Logger.logger
import tr.emreone.kotlin_utils.automation.Day
import tr.emreone.kotlin_utils.math.Coords


class Day13 : Day(13, 2021, "Transparent Origami") {

    class TransparentPaper(val input: List<String>) {
        val coordsSet = mutableSetOf<Coords>()
        var maxX = Int.MAX_VALUE
        var maxY = Int.MAX_VALUE

        init {
            val pattern = """^(\d+),(\d+)$""".toRegex()
            input.forEach { line ->
                val (x, y) = pattern.matchEntire(line)!!.destructured
                coordsSet.add(Coords(x.toInt(), y.toInt()))
            }
            updateLimits()
        }

        private fun updateLimits() {
            this.maxX = coordsSet.maxOf { it.first }
            this.maxY = coordsSet.maxOf { it.second }
        }

        fun printPaper() {
            for (y in 0..this.maxY) {
                val paper = buildString {
                    for (x in 0..this@TransparentPaper.maxX) {
                        val coords = Coords(x, y)
                        if (coordsSet.contains(coords)) {
                            append("x")
                        } else {
                            append(" ")
                        }
                    }
                }

                logger.info(paper)
            }
        }

        fun foldGridAtX(foldX: Int) {
            this.coordsSet.filter {
                it.first > foldX
            }.forEach {
                // fold x to the left
                // distance --> it.first - foldX
                // new x --> foldX - distance = foldX - (it.first - foldX) = 2 * foldX - it.first
                this.coordsSet.remove(it)
                this.coordsSet.add(Coords(2 * foldX - it.first, it.second))
            }

            updateLimits()
        }

        fun foldGridAtY(foldY: Int) {
            this.coordsSet.filter {
                it.second > foldY
            }.forEach {
                // fold y to the up
                // distance --> it.second - foldY
                // new y --> foldY - distance = foldY - (it.second - foldY) = 2 * foldY - it.second
                this.coordsSet.remove(it)
                this.coordsSet.add(Coords(it.first, 2 * foldY - it.second))
            }

            updateLimits()
        }
    }

    override fun part1(): Int {
        val indexOfEmptyLine = inputAsList.indexOf("")
        val coordsInput = inputAsList.subList(0, indexOfEmptyLine)
        val instructionsInput = inputAsList.subList(indexOfEmptyLine + 1, inputAsList.size)

        val transparentPaper = TransparentPaper(coordsInput)

        val pattern = """fold along (\w+)=(\d+)""".toRegex()
        val (axis, index) = pattern.matchEntire(instructionsInput.first())!!.destructured
        when (axis) {
            "x" -> transparentPaper.foldGridAtX(index.toInt())
            "y" -> transparentPaper.foldGridAtY(index.toInt())
        }

        return transparentPaper.coordsSet.size
    }

    override fun part2(): String {
        val indexOfEmptyLine = inputAsList.indexOf("")
        val coordsInput = inputAsList.subList(0, indexOfEmptyLine)
        val instructionsInput = inputAsList.subList(indexOfEmptyLine + 1, inputAsList.size)

        val transparentPaper = TransparentPaper(coordsInput)

        val pattern = """fold along (\w+)=(\d+)""".toRegex()
        for (instruction in instructionsInput) {
            val (axis, index) = pattern.matchEntire(instruction)!!.destructured
            when (axis) {
                "x" -> transparentPaper.foldGridAtX(index.toInt())
                "y" -> transparentPaper.foldGridAtY(index.toInt())
            }
        }

        logger.debug { "To see the eight letters code, print the transparent paper on console: " }
        transparentPaper.printPaper()

        return "BCZRCEAB"
    }

}
