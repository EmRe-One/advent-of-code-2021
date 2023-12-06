package tr.emreone.adventofcode.days

object Day4 {

    class Field(var value: Int) {
        var isMarked = false
    }

    class Board(lines: List<String>) {
        val fields = Array(5) {
            Array(5) {
                Field(0)
            }
        }
        var finished = false

        init {
            check(lines.size == 6) { "Invalid input. Lines have one empty line and 5 lines for the 5x5 matrix" }

            val regex = """^(\d+) (\d+) (\d+) (\d+) (\d+)$""".toRegex()
            for (i in 1..5) {
                val preparedLine = lines[i].trim().replace("""(\s+)""".toRegex(), " ")
                val (a, b, c, d, e) = regex.find(preparedLine)!!.destructured

                fields[i - 1][0].value = a.toInt()
                fields[i - 1][1].value = b.toInt()
                fields[i - 1][2].value = c.toInt()
                fields[i - 1][3].value = d.toInt()
                fields[i - 1][4].value = e.toInt()
            }
        }

        fun checkWin(): Boolean {
            for (row in fields) {
                if (row.all { it.isMarked }) {
                    this.finished = true
                    return true
                }
            }
            for (col in 0..4) {
                val tempCol = arrayOf(fields[0][col], fields[1][col], fields[2][col], fields[3][col], fields[4][col])
                if (tempCol.all { it.isMarked }) {
                    this.finished = true
                    return true
                }
            }

            return false
        }

        fun mark(d: Int) {
            outer@ for(row in 0..4) {
                inner@ for(f in 0 .. 4) {
                    if (fields[row][f].value == d) {
                        fields[row][f].isMarked = true
                        break@outer
                    }
                }
            }
        }

        fun sumOfUnmarked(): Int {
            return fields.sumOf { row ->
                row.sumOf { field ->
                    if (field.isMarked)
                        0
                    else
                        field.value
                }
            }
        }
    }

    fun part1(list: List<String>): Int {
        val drawingNumbers = list[0].split(",").map { it.toInt() }

        val boards = list.subList(1, list.size).windowed(6, 6) {
            Board(it)
        }

        var winningPair: Pair<Int, Board>? = null

        outer@ for (d in drawingNumbers) {
            inner@ for (b in boards) {
                b.mark(d)
                if(b.checkWin()) {
                    winningPair = Pair(d, b)
                    break@outer
                }
            }
        }

        if (winningPair != null) {
            return winningPair.first * winningPair.second.sumOfUnmarked()
        }
        return 0
    }

    fun part2(list: List<String>): Int {
        val drawingNumbers = list[0].split(",").map { it.toInt() }

        val boards = list.subList(1, list.size).windowed(6, 6) {
            Board(it)
        }
        var numberOfFinishedBoards = 0

        var lastWinningPair: Pair<Int, Board>? = null

        outer@ for (d in drawingNumbers) {
            inner@ for (b in boards) {
                if (b.finished) {
                    continue@inner
                }

                b.mark(d)
                if(b.checkWin()) {
                    numberOfFinishedBoards++
                }

                if (numberOfFinishedBoards == boards.size) {
                    lastWinningPair = Pair(d, b)
                    break@outer
                }
            }
        }

        if (lastWinningPair != null) {
            return lastWinningPair.first * lastWinningPair.second.sumOfUnmarked()
        }
        return 0
    }

}
