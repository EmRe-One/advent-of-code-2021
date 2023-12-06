package tr.emreone.adventofcode.days

import tr.emreone.utils.Logger.logger
import tr.emreone.utils.extensions.Coords
import tr.emreone.utils.extensions.times


object Day20 {

    class ImageEnhancementAlgorithm(private val lookUpString: String) {
        private var minX: Int = 0
        private var maxX: Int = 0
        private var minY: Int = 0
        private var maxY: Int = 0

        var lightPixels = mutableSetOf<Coords>()
        private var steps: Int = 0

        // if index 0 is '#' and index 512 is '.' the border is switching from '#' to '.' and vice versa
        private var togglingBorder = lookUpString.first() == '#' && lookUpString.last() == '.'

        // use an expanded border during the algorithm or not for cutting
        private var expandBorder = true

        fun readImage(image: List<String>) {
            minY = 0
            maxY = image.size - 1
            minX = 0
            maxX = image.first().length - 1

            for (y in 0..maxY) {
                for (x in 0..maxX) {
                    if (image[y][x] == '#') {
                        lightPixels.add(Coords(x, y))
                    }
                }
            }
        }

        private fun calculateIndexAt(point: Coords): Int {
            // -1, -1    0, -1     1, -1
            // -1,  0    0,  0     1,  0
            // -1,  1    0,  1     1,  1
            val indexString = buildString {
                for (yi in -1..1) {
                    for (xi in -1..1) {
                        if (lightPixels.contains(Coords(point.first + xi, point.second + yi))) {
                            append('1')
                        } else {
                            append('0')
                        }
                    }
                }
            }
            return indexString.toInt(2)
        }

        fun process() {
            var newImage = lightPixels.toMutableSet()
            val borderSize = if (expandBorder) 3 else 1

            val xRange = ((minX - borderSize)..(maxX + borderSize))
            val yRange = ((minY - borderSize)..(maxY + borderSize))

            for (y in yRange) {
                for (x in xRange) {
                    val current = Coords(x, y)
                    val index = calculateIndexAt(current)
                    if (lookUpString[index] == '#') {
                        newImage.add(current)
                    } else {
                        newImage.remove(current)
                    }
                }
            }

            if (togglingBorder) {
                if (!expandBorder) { // if there is a light border, cut it
                    newImage = cutBorder(newImage, xRange, yRange).toMutableSet()
                }
                expandBorder = !expandBorder
            }

            lightPixels = newImage.toMutableSet()
            calculateBoundingBox()
            steps++
        }

        private fun cutBorder(image: MutableSet<Coords>, xRange: IntRange, yRange: IntRange): List<Coords> {
            return image.filter {
                it.first in (xRange.first + 2)..(xRange.last - 2)
                        && it.second in (yRange.first + 2)..(yRange.last - 2)
            }
        }

        private fun calculateBoundingBox() {
            minX = lightPixels.minOf { it.first }
            maxX = lightPixels.maxOf { it.first }
            minY = lightPixels.minOf { it.second }
            maxY = lightPixels.maxOf { it.second }
        }

        fun drawImage() {
            val output = buildString {
                append("=" * 50)
                appendLine()
                append("Steps: $steps")
                appendLine()
                for (y in minY..maxY) {
                    for (x in minX..maxX) {
                        if (lightPixels.contains(Coords(x, y))) {
                            append('#')
                        } else {
                            append('.')
                        }
                    }
                    appendLine()
                }
            }

            logger.debug { output }
        }
    }

    fun part1(input: List<String>): Int {
        val imageEnhancementAlgorithm = ImageEnhancementAlgorithm(input.first())
        imageEnhancementAlgorithm.readImage(input.drop(2))

        imageEnhancementAlgorithm.drawImage()
        repeat(2) {
            imageEnhancementAlgorithm.process()
        }
        imageEnhancementAlgorithm.drawImage()

        return imageEnhancementAlgorithm.lightPixels.size
    }

    fun part2(input: List<String>): Int {
        val imageEnhancementAlgorithm = ImageEnhancementAlgorithm(input.first())
        imageEnhancementAlgorithm.readImage(input.drop(2))
        imageEnhancementAlgorithm.drawImage()
        repeat(50) {
            imageEnhancementAlgorithm.process()
        }
        imageEnhancementAlgorithm.drawImage()
        return imageEnhancementAlgorithm.lightPixels.size
    }
}
