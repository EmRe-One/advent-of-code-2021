package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils.debugLogger
import de.emreak.adventofcode.AdventOfCodeUtils.logger
import de.emreak.adventofcode.copy
import de.emreak.adventofcode.times
import java.util.*
import kotlin.math.abs

object Day23 {
    const val SIGN_WALL = '#'
    const val SIGN_EMPTY_CELL = '.'

    /**
     * This class represents a burrow with a hallway and 4 rooms with a size of roomSize.
     *
     * #        : wall
     * .        : empty cell
     * A,B,C,D  : roomMembers
     *
     * The hallway is numbered from left to the right
     * The rooms are numbered from hallway to bottom.
     *  #############
     *  #012........#   <- hallway
     *  ###0#.#.#.###
     *    #1#.#.#.#
     *    #2#.#.#.#
     *    #3#.#.#.#
     *    #########
     *     A B C D      <- rooms, here roomSize: 4
     *
     */
    class Burrow {
        var hallway: MutableList<Char> = mutableListOf()
        var roomSize: Int = 0
        val rooms: MutableMap<Char, MutableList<Char>> = mutableMapOf()
        var sortHistory: MutableList<Pair<String, Long>> = mutableListOf()

        private fun isCorrectSorted(): Boolean {
            for ((roomLabel, roomMates) in rooms) {
                for (amphipod in roomMates) {
                    if (amphipod != roomLabel) {
                        return false
                    }
                }
            }
            return true
        }

        private fun canMoveFrom(roomLabel: Char): Boolean {
            assert(this.rooms.containsKey(roomLabel)) { "Cannot move from unknown room $roomLabel" }

            // If there is still a foreign amphipod in the room
            for (roomMate in rooms[roomLabel]!!) {
                if (roomMate != roomLabel && roomMate != SIGN_EMPTY_CELL) {
                    return true
                }
            }
            // In the room are only correct amphipods or empty places.
            return false
        }

        private fun canMoveTo(roomLabel: Char): Boolean {
            assert(this.rooms.containsKey(roomLabel)) { "Cannot move to unknown room $roomLabel" }

            // There are still foreign amphipods in the room.
            // No entry of correct amphipod allowed.
            for (roomMate in rooms[roomLabel]!!) {
                if (roomMate != roomLabel && roomMate != SIGN_EMPTY_CELL) {
                    return false
                }
            }
            return true
        }

        /**
         * helper method
         *
         * start < a < end
         *      or
         * end < a < start  (for the reversed direction)
         *
         */
        private fun between(a: Int, start: Int, end: Int): Boolean {
            return (a in (start + 1) until end) || (a in (end + 1) until start)
        }

        /**
         * check here if index is between the current hallwayIndex and the corresponding roomIndex
         * i.e. the D is at index 3 and want to move to index 8
         * this method returns true if the path is clear between 3 and 8
         * otherwise false
         *
         * 0 1 2 3 4 5 6 7 8 9 10   <- hallwayIndex
         *     A   B   C   D        <- roomEntries at correct positions
         */
        private fun pathIsClear(roomLabel: Char, hallwayIndex: Int): Boolean {
            for (i in 0 until this.hallway.size) {
                val roomIndex = ROOM_INDEX[roomLabel] ?: throw IllegalArgumentException("Unknown room label $roomLabel")

                // first:
                //      check if i is between current hallwayIndex and roomIndex
                // then:
                //      check if the position i is empty
                if (between(i, hallwayIndex, roomIndex) && this.hallway[i] != SIGN_EMPTY_CELL) {
                    return false
                }
            }
            return true
        }

        /**
         * returns the last empty cell index in the room
         * or null
         */
        private fun getLastEmptyIndexInRoom(roomLabel: Char): Int? {
            assert(this.rooms[roomLabel] != null) { "Cannot get destination index in unknown room $roomLabel" }

            this.rooms[roomLabel]!!.mapIndexed { index, amphipod ->
                index to amphipod
            }.reversed().forEach { (index, amphipod) ->
                if (amphipod == SIGN_EMPTY_CELL) {
                    return index
                }
            }

            return null
        }

        /**
         * returns the first occupied index in the room next to the hallway
         * or null
         */
        private fun getFirstOccupiedIndexInRoom(roomLabel: Char): Int? {
            assert(this.rooms[roomLabel] != null) { "Cannot get top index in unknown room $roomLabel" }

            this.rooms[roomLabel]!!.mapIndexed { index, amphipod ->
                index to amphipod
            }.forEach { (index, amphipod) ->
                if (amphipod != SIGN_EMPTY_CELL) {
                    return index
                }
            }

            return null
        }

        /**
         * Print the current state of the burrow
         */
        fun getBurrowRepresentation(): String {
            return buildString {
                // print top wall
                appendLine()
                append(SIGN_WALL * (hallway.size + 2)); appendLine()

                // print hallway
                append(SIGN_WALL)
                hallway.forEach {
                    append(it)
                }
                append(SIGN_WALL); appendLine()

                // print side rooms
                for (i in 0 until roomSize) {
                    if (i == 0) append(SIGN_WALL * 2) else append("  ")

                    val t = mutableListOf<Char>()
                    for (j in 0 until rooms.size) {
                        t.add(rooms['A' + j]!![i])
                    }
                    append(
                        t.joinToString(
                            prefix = SIGN_WALL.toString(),
                            separator = SIGN_WALL.toString(),
                            postfix = SIGN_WALL.toString()
                        )
                    )
                    if (i == 0) append(SIGN_WALL * 2) else append("  ")
                    appendLine()
                }
                append("  "); append(SIGN_WALL * (hallway.size - 2)); append("  "); appendLine()
            }
        }

        /**
         * Convert the burrow to a unique string representation
         */
        fun uniqueString(): String {
            return buildString {
                append(this@Burrow.hallway.joinToString(prefix = "{H:", separator = "", postfix = "||"))
                val t = mutableListOf<String>()
                for ((roomName, amphipods) in this@Burrow.rooms) {
                    t.add("$roomName:" + amphipods.joinToString(separator = ""))
                }
                append(t.joinToString(separator = "|"))
                append('}')
            }
        }

        fun copy(): Burrow {
            return Burrow().also {
                it.hallway = this.hallway.toMutableList()
                it.roomSize = this.roomSize
                this.rooms.forEach { (label, rooms) ->
                    it.rooms[label] = rooms.toMutableList()
                }
                this.sortHistory.forEach { (uniqueString, cost) ->
                    it.sortHistory.add(uniqueString to cost)
                }
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Burrow

            if (this.sortHistory != other.hallway) return false
            if (this.uniqueString() != other.uniqueString()) return false

            return true
        }

        override fun hashCode(): Int {
            return this.uniqueString().hashCode()
        }

        fun printSortHistory() {
            val minCost = this.sortHistory.last().second

            this.sortHistory.reversed().windowed(2).forEach { (pre, post) ->
                logger.debug { "Sum of Energy:  " + String.format("%6d", minCost - pre.second)}
                logger.debug { "Next Step need: " + String.format("%6d", pre.second - post.second)}
                logger.debug { pre.first }
            }
            logger.debug { "Total Energy: " + String.format("%6d", minCost)}
            logger.debug { this.sortHistory[0].first }
        }

        companion object {
            private val ENERGY_COST = mapOf(
                'A' to 1,
                'B' to 10,
                'C' to 100,
                'D' to 1000
            )

            private val ROOM_INDEX: MutableMap<Char, Int> = mutableMapOf()
            private val TOTAL_ENERGY_FOR_STATES: MutableMap<String, MutableList<Pair<String, Long>>> = mutableMapOf()

            fun parse(input: List<String>): Burrow {
                val burrow = Burrow()

                // read the hallway
                input[1].forEach {
                    if (it == SIGN_EMPTY_CELL) {
                        burrow.hallway.add(it)
                    }
                }

                // read the side rooms
                val pattern = """(\w)+""".toRegex()
                for (i in 2 until (input.size - 1)) {
                    val rooms = pattern.findAll(input[i]).toList()
                    for (roomNumber in rooms.indices) {
                        val amphipod = rooms[roomNumber].value.first()
                        if (!burrow.rooms.containsKey('A' + roomNumber)) {
                            burrow.rooms['A' + roomNumber] = Stack()
                            ROOM_INDEX['A' + roomNumber] = (roomNumber + 1) * 2
                        }
                        burrow.rooms['A' + roomNumber]!!.add(amphipod)
                    }
                    burrow.roomSize++
                }

                return burrow
            }

            fun calculateEnergy(burrow: Burrow): Long {
                if (burrow.isCorrectSorted()) {
                    burrow.sortHistory.add(burrow.getBurrowRepresentation() to 0)
                    return 0L
                }
                if (TOTAL_ENERGY_FOR_STATES.containsKey(burrow.uniqueString())) {
                    val cachedList = TOTAL_ENERGY_FOR_STATES[burrow.uniqueString()]!!
                    burrow.sortHistory.copy(cachedList)
                    return cachedList.lastOrNull()?.second ?: Long.MAX_VALUE
                }

                // if an amphipod can move to his correct room
                burrow.hallway.forEachIndexed { hallwayIndex, cell ->
                    if (burrow.rooms.containsKey(cell)
                        && burrow.canMoveTo(cell)
                        && burrow.pathIsClear(cell, hallwayIndex)
                    ) {
                        val lastEmptyIndex = burrow.getLastEmptyIndexInRoom(cell)
                        assert(lastEmptyIndex != null) { "Room has no empty place." }

                        val distance = lastEmptyIndex!! + 1 + abs(hallwayIndex - ROOM_INDEX[cell]!!)
                        val energy = ENERGY_COST[cell]!! * distance

                        val newBurrow = burrow.copy()

                        newBurrow.hallway[hallwayIndex] = SIGN_EMPTY_CELL
                        newBurrow.rooms[cell]!![lastEmptyIndex] = cell

                        val cost = energy + calculateEnergy(newBurrow)

                        return if (cost < 0) {
                            // Long value overflow: ignoring this value
                            Long.MAX_VALUE
                        } else {
                            newBurrow.sortHistory.add(burrow.getBurrowRepresentation() to cost)
                            burrow.sortHistory.copy(newBurrow.sortHistory, true)
                            cost
                        }
                    }
                }

                var minimalCost = Long.MAX_VALUE
                val minimalCostHistory: MutableList<Pair<String, Long>> = mutableListOf()
                minimalCostHistory.copy(burrow.sortHistory)

                sideRoomsLoop@
                for ((roomLabel: Char, roomMates: MutableList<Char>) in burrow.rooms) {
                    if (!burrow.canMoveFrom(roomLabel)) {
                        continue@sideRoomsLoop
                    }

                    val ki = burrow.getFirstOccupiedIndexInRoom(roomLabel) ?: continue@sideRoomsLoop

                    val amphipodOnTop = roomMates[ki]

                    hallwayLoop@
                    for (hallwayIndex in 0 until burrow.hallway.size) {
                        if (hallwayIndex in ROOM_INDEX.values) {
                            // the position directly above the rooms are not allowed
                            continue@hallwayLoop
                        }
                        if (burrow.hallway[hallwayIndex] != SIGN_EMPTY_CELL) {
                            // if the hallway position is not empty we can't move
                            continue@hallwayLoop
                        }
                        // amphipod go out of the room to all possible hallway positions
                        if (burrow.pathIsClear(roomLabel, hallwayIndex)) {
                            val dist = ki + 1 + abs(hallwayIndex - ROOM_INDEX[roomLabel]!!)
                            val energy = ENERGY_COST[amphipodOnTop]!! * dist
                            val newBurrow = burrow.copy()

                            assert(newBurrow.hallway[hallwayIndex] == SIGN_EMPTY_CELL) {
                                "Hallway position at $hallwayIndex not empty."
                            }
                            newBurrow.hallway[hallwayIndex] = amphipodOnTop

                            assert(newBurrow.rooms[roomLabel]!![ki] == amphipodOnTop) {
                                "Amphipod on top of the stack is not $amphipodOnTop."
                            }
                            newBurrow.rooms[roomLabel]!![ki] = SIGN_EMPTY_CELL

                            val cost = energy + calculateEnergy(newBurrow)
                            if (cost < 0) {
                                // Long value overflow: ignoring this value
                                Long.MAX_VALUE
                            } else if (cost < minimalCost) {
                                minimalCost = cost
                                newBurrow.sortHistory.add(burrow.getBurrowRepresentation() to cost)
                                minimalCostHistory.copy(newBurrow.sortHistory, true)
                            }
                        }
                    }
                }

                TOTAL_ENERGY_FOR_STATES[burrow.uniqueString()] = minimalCostHistory
                burrow.sortHistory.copy(minimalCostHistory, true)
                return minimalCost
            }
        }
    }

    fun part1(input: List<String>): Long {
        val burrow = Burrow.parse(input)
        val minCost = Burrow.calculateEnergy(burrow)
        burrow.printSortHistory()
        return minCost
    }

    fun part2(input: List<String>): Long {
        val extendedInput = input.toMutableList().also {
            it.add(3, "#D#C#B#A#")
            it.add(4, "#D#B#A#C#")
        }
        val burrow = Burrow.parse(extendedInput)

        val minCost = Burrow.calculateEnergy(burrow)
        burrow.printSortHistory()
        return minCost
    }
}
