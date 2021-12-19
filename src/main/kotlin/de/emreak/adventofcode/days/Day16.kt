package de.emreak.adventofcode.days

import java.math.BigInteger

object Day16 {

    class BITSPacket(val bitMessage: String) {
        private val packetVersion: Int
        private val packetTypeId: Int
        private val children = mutableListOf<BITSPacket>()
        private var literalValue: BigInteger? = null

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

            while(parsingIndex < startOfOperatorData + dataLength) {
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

            for(child in children) {
                result += child.getSumOfVersions()
            }

            return result
        }
    }

    fun part1(hexMessage: String): Int {
        val hexLength = hexMessage.length
        val bitMessage = hexMessage.toBigInteger(16)
            .toString(2)
            .padStart(4 * hexLength, '0')

        val temp = BITSPacket(bitMessage)
        return temp.getSumOfVersions()
    }

    fun part2(hexMessage: String): Int {

        return 0
    }
}
