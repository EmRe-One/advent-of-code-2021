package tr.emreone.adventofcode.days

object Day6 {

    fun countAllFishesAfterDays(input: String, days: Int): Long {
        val fishes = input.split(",")
            .groupBy { it.toInt() }
            .mapValues { it.value.size }

        val fishDistribution = LongArray(9)

        for(f in fishes) {
            fishDistribution[f.key] = f.value.toLong()
        }

        var temp: Long
        for(i in 0 until days) {
            temp = fishDistribution[0]
            fishDistribution[0] = fishDistribution[1]
            fishDistribution[1] = fishDistribution[2]
            fishDistribution[2] = fishDistribution[3]
            fishDistribution[3] = fishDistribution[4]
            fishDistribution[4] = fishDistribution[5]
            fishDistribution[5] = fishDistribution[6]
            fishDistribution[6] = temp + fishDistribution[7]
            fishDistribution[7] = fishDistribution[8]
            fishDistribution[8] = temp
        }

        return fishDistribution.sumOf { it }
    }

}
