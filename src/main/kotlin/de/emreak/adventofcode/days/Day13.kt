package de.emreak.adventofcode.days

import AdventOfCodeUtils.logger
import Coords

object Day13 {

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
                var lineBuilder = StringBuilder()

                for (x in 0..this.maxX) {
                    val coords = Coords(x, y)
                    if (coordsSet.contains(coords)) {
                        lineBuilder.append("x")
                    }
                    else {
                        lineBuilder.append(" ")
                    }
                }
                logger.info(lineBuilder.toString())
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

    fun part1(input: List<String>): Int {
        val indexOfEmptyLine = input.indexOf("")
        val coordsInput = input.subList(0, indexOfEmptyLine)
        val instructionsInput = input.subList(indexOfEmptyLine + 1, input.size)

        val transparentPaper = TransparentPaper(coordsInput)

        val pattern = """fold along (\w+)=(\d+)""".toRegex()
        val (axis, index) = pattern.matchEntire(instructionsInput.first())!!.destructured
        when(axis) {
            "x" -> transparentPaper.foldGridAtX(index.toInt())
            "y" -> transparentPaper.foldGridAtY(index.toInt())
        }

        return transparentPaper.coordsSet.size
    }

    fun part2(input: List<String>): String {
        val indexOfEmptyLine = input.indexOf("")
        val coordsInput = input.subList(0, indexOfEmptyLine)
        val instructionsInput = input.subList(indexOfEmptyLine + 1, input.size)

        val transparentPaper = TransparentPaper(coordsInput)

        val pattern = """fold along (\w+)=(\d+)""".toRegex()
        for(instruction in instructionsInput) {
            val (axis, index) = pattern.matchEntire(instruction)!!.destructured
            when(axis) {
                "x" -> transparentPaper.foldGridAtX(index.toInt())
                "y" -> transparentPaper.foldGridAtY(index.toInt())
            }
        }

        logger.debug { "To see the eight letters code, print the transparent paper on console: " }
        transparentPaper.printPaper()

        return "BCZRCEAB"
    }
}
