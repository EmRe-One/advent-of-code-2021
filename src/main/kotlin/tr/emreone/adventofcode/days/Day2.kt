package tr.emreone.adventofcode.days


object Day2 {

    fun part1(input: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0

        // ^ start line with one of (forward, down or up) ( also possible ^(\w+)
        // _ one whitespace
        // (\d+)$ finish line with a number with at least one digit
        val regex = """^(?<direction>forward|down|up) (?<distance>\d+)$""".toRegex()

        val modifiedInput = input.map {
            val (a, b) = regex.find(it)?.destructured ?: error("Invalid line: $it")
            Pair(a, b.toInt())
        }

        modifiedInput.forEach { (direction, distance) ->
            when (direction) {
                "forward" -> horizontalPosition += distance
                "down"    -> depth += distance
                "up"      -> depth -= distance
                else      -> throw IllegalArgumentException("Unknown direction: $direction")
            }

        }

        return horizontalPosition * depth
    }

    fun part2(input: List<String>): Int {
        var positionX = 0
        var depth = 0
        var aim = 0

        val regex = """^(?<direction>forward|down|up) (?<distance>\d+)$""".toRegex()

        val modifiedInput = input.map {
            val (a, b) = regex.find(it)?.destructured ?: error("Invalid line: $it")
            Pair(a, b.toInt())
        }

        modifiedInput.forEach { (direction, distance) ->
            when (direction) {
                "forward" -> {
                    positionX += distance
                    depth += aim * distance
                }
                "down"    -> aim += distance
                "up"      -> aim -= distance
                else      -> throw IllegalArgumentException("Unknown command: $direction")
            }
        }

        return positionX * depth
    }

}