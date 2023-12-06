package tr.emreone.adventofcode.days

object Day1 {

    fun part1(list: List<Int>): Int {
        return list.windowed(2)
            .count { (a, b) ->
                a < b
            }
    }

    fun part2(list: List<Int>): Int {
        // solution 1
        return list.windowed(3)
            .windowed(2)
            .count { (triple1, triple2) ->
                triple1.sum() < triple2.sum()
            }

        /*
        // solution 2
        // because a(0) + a(1) + a(2) < a(1) + a(2) + a(3)
        // this is equivalent to a(0) < a(3)

        return list.windowed(4).count {
            it[0] < it[3]
        }
         */
    }

}
