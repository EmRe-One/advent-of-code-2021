package de.emreak.adventofcode.days

import de.emreak.adventofcode.AdventOfCodeUtils.debugLogger
import de.emreak.adventofcode.AdventOfCodeUtils.logger
import de.emreak.adventofcode.times
import java.util.*
import kotlin.math.abs
import kotlin.math.min

object Day23 {
    const val SIGN_WALL = '#'
    const val SIGN_EMPTY_CELL = '.'

    class Burrow {
        val hallway: MutableList<Char> = mutableListOf()
        var roomSize: Int = 0
        val sideRooms: MutableMap<Char, Stack<Char>> = mutableMapOf()

        private fun isCorrectSorted(): Boolean {
            for ((roomName, roomMates) in sideRooms) {
                for (amphipod in roomMates) {
                    if (amphipod != roomName) {
                        return false
                    }
                }
            }
            return true
        }

        private fun canMoveTo(roomLabel: Char): Boolean {
            assert(this.sideRooms.containsKey(roomLabel)) { "Cannot move to unknown room $roomLabel" }

            // room is full
            if (sideRooms[roomLabel]!!.size == this.roomSize) {
                return false
            }
            // There are still foreign amphipods in the room.
            // No entry of correct amphipod allowed.
            for (roomMate in sideRooms[roomLabel]!!) {
                if (roomMate != roomLabel) {
                    return false
                }
            }
            return true
        }

        private fun canMoveFrom(roomLabel: Char): Boolean {
            assert(this.sideRooms.containsKey(roomLabel)) { "Cannot move from unknown room $roomLabel" }

            // If there is still a foreign amphipod in the room
            for (roomMate in sideRooms[roomLabel]!!) {
                if (roomMate != roomLabel) {
                    return true
                }
            }
            // The room is empty or all amphipods in the room are all in the correct room.
            // They don't have to leave the room.
            return false
        }

        private fun between(a: Int, b: Int, c: Int): Boolean {
            return (b in (a + 1) until c) || (b in (c + 1) until a)
        }

        /**
         * check here if index is between the current hallwayIndex and the corresponding roomIndex
         * i.e. the D is at index 3 and want to move to index 8
         * this method returns true if the index is between 4 and 8
         * otherwise false
         *
         * 0 1 2 3 4 5 6 7 8 9 10
         *     A   B   C   D
         */
        private fun pathIsPossible(roomLabel: Char, hallwayIndex: Int): Boolean {
            for (i in 0 until this.hallway.size) {
                val roomIndex = ROOM_INDEXES[roomLabel] ?: throw IllegalArgumentException("Unknown room label $roomLabel")

                if (between(roomIndex, i, hallwayIndex) && this.hallway[i] != SIGN_EMPTY_CELL) {
                    return false
                }
            }
            return true
        }

        private fun getIndexInRoom(roomLabel: Char): Int {
            return this.roomSize - this.sideRooms[roomLabel]!!.size
        }

        private fun getIndexInHallway() {

        }

        fun printBurrow() {
            val burrowString = buildString {
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
                for (i in (roomSize - 1) downTo 0) {
                    if (i == (roomSize - 1)) append(SIGN_WALL * 2) else append("  ")
                    val t = mutableListOf<Char>()
                    for (j in 0 until sideRooms.size) {
                        val room = sideRooms['A' + j]!!
                        if (i < room.size) {
                            t.add(room[i])
                        } else {
                            t.add(SIGN_EMPTY_CELL)
                        }
                    }
                    append(
                        t.joinToString(
                            prefix = SIGN_WALL.toString(),
                            separator = SIGN_WALL.toString(),
                            postfix = SIGN_WALL.toString()
                        )
                    )
                    if (i == (roomSize - 1)) append(SIGN_WALL * 2) else append("  ")
                    appendLine()
                }
                append("  "); append(SIGN_WALL * (hallway.size - 2)); append("  "); appendLine()
            }
            debugLogger.debug { burrowString }
        }

        fun uniqueString(): String {
            return buildString {
                append('{')
                append(this@Burrow.hallway.joinToString(separator = ""))
                for ((roomName, amphipods) in this@Burrow.sideRooms) {
                    append(",$roomName:" + amphipods.joinToString(separator = ""))
                }
                append('}')
            }
        }

        fun copy(): Burrow {
            return Burrow().also {
                it.hallway.addAll(this.hallway)
                it.roomSize = this.roomSize
                it.sideRooms.putAll(this.sideRooms)
            }
        }

        companion object {
            private val ENERGY_COST = mapOf(
                'A' to 1,
                'B' to 10,
                'C' to 100,
                'D' to 1000
            )

            private val ROOM_INDEXES: MutableMap<Char, Int> = mutableMapOf()
            private val TOTAL_ENERGY_FOR_STATES: MutableMap<String, Int> = mutableMapOf()

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
                for (i in (input.size - 2) downTo 2) {
                    val rooms = pattern.findAll(input[i]).toList()
                    for (roomNumber in rooms.indices) {
                        val amphipod = rooms[roomNumber].value.first()
                        if (!burrow.sideRooms.containsKey('A' + roomNumber)) {
                            burrow.sideRooms['A' + roomNumber] = Stack()
                            ROOM_INDEXES['A' + roomNumber] = (roomNumber + 1) * 2
                        }
                        burrow.sideRooms['A' + roomNumber]!!.add(amphipod)
                    }
                    burrow.roomSize++
                }

                return burrow
            }

            fun calculateEnergy(burrow: Burrow): Int {
                if (burrow.isCorrectSorted()) {
                    return 0
                }
                if (TOTAL_ENERGY_FOR_STATES.containsKey(burrow.uniqueString())) {
                    return TOTAL_ENERGY_FOR_STATES[burrow.uniqueString()]!!
                }

                // if a amphipod can move to his correct room
                burrow.hallway.forEachIndexed { hallwayIndex, cell ->
                    if (burrow.sideRooms.containsKey(cell) && burrow.canMoveTo(cell)
                        && burrow.pathIsPossible(cell, hallwayIndex)
                    ) {
                        val distanceFromHallwayToFinalPosition = burrow.getIndexInRoom(cell)
                        assert(distanceFromHallwayToFinalPosition != 0) { "Room has no empty place." }
                        val distance = distanceFromHallwayToFinalPosition + abs(hallwayIndex - ROOM_INDEXES[cell]!!)
                        val energy = ENERGY_COST[cell]!! * distance

                        val newBurrow = burrow.copy()
                        newBurrow.sideRooms[cell]!!.push(cell)

                        logger.debug { "Moving from hallwayIndex $hallwayIndex to Room($cell) with a distance $distance for a energy cost $energy" }
                        burrow.hallway[hallwayIndex] = SIGN_EMPTY_CELL
                        newBurrow.printBurrow()
                        return energy + calculateEnergy(newBurrow)
                    }
                }

                var minimalCost = Int.MAX_VALUE
                for ((roomLabel: Char, roomMates: Stack<Char>) in burrow.sideRooms) {
                    if (!burrow.canMoveFrom(roomLabel)) {
                        continue
                    }
                    if (roomMates.isEmpty()) {
                        continue
                    }

                    val amphipodOnTop = roomMates.peek()
                    val distanceToHallway = burrow.roomSize - roomMates.size

                    for (hallwayIndex in 0 until burrow.hallway.size) {
                        if (hallwayIndex in ROOM_INDEXES.values) {
                            // the position directly above the rooms are not allowed
                            continue
                        }
                        if (burrow.hallway[hallwayIndex] != SIGN_EMPTY_CELL) {
                            // if the hallway position is not empty we can't move
                            continue
                        }
                        if (burrow.pathIsPossible(roomLabel, hallwayIndex)) {
                            val dist = distanceToHallway + 1 + abs(hallwayIndex - ROOM_INDEXES[roomLabel]!!)
                            val energy = ENERGY_COST[amphipodOnTop]!! * dist
                            val newBurrow = burrow.copy()

                            // amphipod go to hallway
                            newBurrow.hallway[hallwayIndex] = amphipodOnTop
                            logger.debug { "Try to pop from $roomLabel" }
                            newBurrow.sideRooms[roomLabel]!!.pop()

                            logger.debug { "Moving $amphipodOnTop from Room($roomLabel) with a distance $dist for a energy cost $energy" }
                            newBurrow.printBurrow()

                            minimalCost = min(minimalCost, energy + calculateEnergy(newBurrow))
                        }
                    }
                }

                TOTAL_ENERGY_FOR_STATES[burrow.uniqueString()] = minimalCost
                return minimalCost
            }
        }
    }

    fun part1(input: List<String>): Int {
        val burrow = Burrow.parse(input)
        return Burrow.calculateEnergy(burrow)
    }

    fun part2(input: List<String>): Int {
        val extendedInput = input.toMutableList().also {
            it.add(3, "#D#C#B#A#")
            it.add(4, "#D#B#A#C#")
        }
        val burrow = Burrow.parse(extendedInput)

        return Burrow.calculateEnergy(burrow)
    }
}
