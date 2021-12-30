package de.emreak.adventofcode.days

import tr.emreone.utils.Logger.logger

object Day12 {

    class Cave(var name: String) {
        val neighbors = mutableSetOf<Cave>()
        val isSmall = name.all { it.isLowerCase() }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Cave

            return name == other.name
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }
    }

    class CaveSystem() {
        private val caves = mutableMapOf<String, Cave>()
        private val possiblePathes = mutableSetOf<List<Cave>>()

        fun addConnection(line: String) {
            val pattern = """^(\w+)-(\w+)$""".toRegex()
            val (from, to) = pattern.matchEntire(line)!!.destructured

            val caveFrom = caves.getOrPut(from) { Cave(from) }
            val caveTo = caves.getOrPut(to) { Cave(to) }

            caveFrom.neighbors.add(caveTo)
            caveTo.neighbors.add(caveFrom)
        }

        /**
         * Your goal is to find the number of distinct paths that
         * - start at start,
         * - end at end,
         * - and don't visit small caves more than once.
         *
         * There are two types of caves: big caves (written in uppercase, like A)
         * and small caves (written in lowercase, like b).
         * It would be a waste of time to visit any small cave more than once,
         * but big caves are large enough that it might be worth visiting them multiple times.
         * So, all paths you find should visit small caves at most once,
         * and can visit big caves any number of times.
         */
        fun getNumberOfPossiblePathesWithFirstRule(): Int {
            val start = caves["start"] ?: throw IllegalStateException("No start cave found")
            val end = caves["end"] ?: throw IllegalStateException("No end cave found")

            this.possiblePathes.clear()

            fun getPossiblePathes(cave: Cave, path: List<Cave> = listOf(cave)) {
                if (cave == end) {
                    possiblePathes.add(path)
                    logger.debug { path.joinToString(" -> ") { c -> c.name } }
                    return
                }

                cave.neighbors.forEach {
                    if (!it.isSmall || it !in path) {
                        getPossiblePathes(it, path + it)
                    }
                }
            }

            getPossiblePathes(start)

            return this.possiblePathes.size
        }

        /**
         * After reviewing the available paths, you realize
         * you might have time to visit a single small cave twice.
         *
         * Specifically, big caves can be visited any number of times,
         * a single small cave can be visited at most twice,
         * and the remaining small caves can be visited at most once.
         *
         * However, the caves named start and end can only be visited exactly once each:
         * once you leave the start cave, you may not return to it,
         * and once you reach the end cave, the path must end immediately.
         */
        fun getNumberOfPossiblePathesWithSecondRule(): Int {
            val start = caves["start"] ?: throw IllegalStateException("No start cave found")
            val end = caves["end"] ?: throw IllegalStateException("No end cave found")

            this.possiblePathes.clear()

            fun getPossiblePathes(cave: Cave, path: List<Cave> = listOf(cave)) {
                if (cave == end) {
                    possiblePathes.add(path)
                    logger.debug { path.joinToString(" -> ") { c -> c.name } }
                    return
                }

                cave.neighbors.forEach {
                    if (!it.isSmall) {
                        getPossiblePathes(it, path + it)
                    } else {
                        val anyTwiceVisitedSmallCave = path.groupBy { cave -> cave }.filter { (cave, _) ->
                            cave.isSmall
                        }.any { (_, list) ->
                            list.size == 2
                        }

                        if (it != start && (it !in path || !anyTwiceVisitedSmallCave)) {
                            getPossiblePathes(it, path + it)
                        }
                    }
                }
            }

            getPossiblePathes(start)

            return this.possiblePathes.size
        }

    }

    fun part1(input: List<String>): Int {
        val caveSystem = CaveSystem()
        input.forEach {
            caveSystem.addConnection(it)
        }
        return caveSystem.getNumberOfPossiblePathesWithFirstRule()
    }

    fun part2(input: List<String>): Int {
        val caveSystem = CaveSystem()
        input.forEach {
            caveSystem.addConnection(it)
        }
        return caveSystem.getNumberOfPossiblePathesWithSecondRule()
    }
}
