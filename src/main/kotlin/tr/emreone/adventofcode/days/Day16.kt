package tr.emreone.adventofcode.days

import tr.emreone.kotlin_utils.automation.Day
import java.math.BigInteger

class Day16 : Day(16, 2021, "Packet Decoder") {

    class BITSPacket(val bitMessage: String) {
        private val packetVersion: Int
        private val packetTypeId: Int
        private val children = mutableListOf<BITSPacket>()
        private var literalValue: BigInteger? = null
        private var calculatedValue: BigInteger? = null

        var parsingIndex = 0

        init {
            this.packetVersion = bitMessage.substring(parsingIndex, parsingIndex + 3).toInt(2)
            parsingIndex += 3
            this.packetTypeId = bitMessage.substring(parsingIndex, parsingIndex + 3).toInt(2)
            parsingIndex += 3

            when (this.packetTypeId) {
                4 -> { // literal value
                    parseLiteralValue()
                }

                else -> { // operator
                    val lengthTypeId = bitMessage.substring(parsingIndex, parsingIndex + 1).toInt()
                    parsingIndex += 1

                    if (lengthTypeId == 0) {
                        parse15BitOperator()
                    } else {
                        parse11BitOperator()
                    }
                }
            }
        }

        private fun parseLiteralValue() {
            // literal value
            val data = buildString {
                while (true) {
                    val bits = bitMessage.substring(parsingIndex, parsingIndex + 5)
                    parsingIndex += 5
                    append(bits.drop(1))

                    if (bits.startsWith("0")) {
                        break
                    }
                }
            }
            this.literalValue = data.toBigInteger(2)
        }

        private fun parse15BitOperator() {
            val dataLength = bitMessage.substring(parsingIndex, parsingIndex + 15).toInt(2)
            parsingIndex += 15
            val startOfOperatorData = parsingIndex

            while (parsingIndex < startOfOperatorData + dataLength) {
                val nextBitString = bitMessage.substring(parsingIndex, bitMessage.length)
                val child = BITSPacket(nextBitString)
                this.children.add(child)
                parsingIndex += child.parsingIndex
            }
        }

        private fun parse11BitOperator() {
            val numberOfPackets = bitMessage.substring(parsingIndex, parsingIndex + 11).toInt(2)
            parsingIndex += 11

            for (i in 0 until numberOfPackets) {
                val nextBitString = bitMessage.substring(parsingIndex, bitMessage.length)
                val child = BITSPacket(nextBitString)
                this.children.add(child)
                parsingIndex += child.parsingIndex
            }
        }

        fun getSumOfVersions(): Int {
            var result = 0
            result += this.packetVersion

            for (child in children) {
                result += child.getSumOfVersions()
            }

            return result
        }

        fun evaluate(): Long {
            /*
                Packets with type ID 0 are sum packets - their value is the sum of the values of their sub-packets. If they only have a single sub-packet, their value is the value of the sub-packet.
                Packets with type ID 1 are product packets - their value is the result of multiplying together the values of their sub-packets. If they only have a single sub-packet, their value is the value of the sub-packet.
                Packets with type ID 2 are minimum packets - their value is the minimum of the values of their sub-packets.
                Packets with type ID 3 are maximum packets - their value is the maximum of the values of their sub-packets.
                Packets with type ID 5 are greater than packets - their value is 1 if the value of the first sub-packet is greater than the value of the second sub-packet; otherwise, their value is 0. These packets always have exactly two sub-packets.
                Packets with type ID 6 are less than packets - their value is 1 if the value of the first sub-packet is less than the value of the second sub-packet; otherwise, their value is 0. These packets always have exactly two sub-packets.
                Packets with type ID 7 are equal to packets - their value is 1 if the value of the first sub-packet is equal to the value of the second sub-packet; otherwise, their value is 0. These packets always have exactly two sub-packets.
            */
            return when (this.packetTypeId) {
                0 -> { // sum of childs
                    var sum = 0L
                    for (child in children) {
                        sum += child.evaluate()
                    }
                    sum
                }

                1 -> { // product of childs
                    var product = 1L
                    for (child in children) {
                        product *= child.evaluate()
                    }
                    product
                }

                2 -> { // min of
                    this.children.map { it.evaluate() }.minOf { it }
                }

                3 -> { // max of
                    this.children.map { it.evaluate() }.maxOf { it }
                }

                4 -> { // return literal value
                    this.literalValue!!.toLong()
                }

                5 -> { // greater than
                    if (this.children[0].evaluate() > this.children[1].evaluate()) 1L else 0L
                }

                6 -> { // less than
                    if (this.children[0].evaluate() < this.children[1].evaluate()) 1L else 0L
                }

                7 -> { // equal to
                    if (this.children[0].evaluate() == this.children[1].evaluate()) 1L else 0L
                }

                else -> {
                    throw IllegalArgumentException("Unknown packet type ID: ${this.packetTypeId}")
                }
            }
        }
    }

    override fun part1(): Int {
        val hexMessage = inputAsString

        val hexLength = hexMessage.length
        val bitMessage = hexMessage
            .toBigInteger(16)
            .toString(2)
            .padStart(4 * hexLength, '0')

        return BITSPacket(bitMessage).getSumOfVersions()
    }

    override fun part2(): Long {
        val hexMessage = inputAsString

        val hexLength = hexMessage.length
        val bitMessage = hexMessage
            .toBigInteger(16)
            .toString(2)
            .padStart(4 * hexLength, '0')

        val parentBitsPacket = BITSPacket(bitMessage)
        return parentBitsPacket.evaluate()
    }

}
