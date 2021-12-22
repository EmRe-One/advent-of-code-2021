package de.emreak.adventofcode

import mu.KotlinLogging
import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

typealias Coords = Pair<Int, Int>

object AdventOfCodeUtils {

    val logger = KotlinLogging.logger {}
    val debugLogger = KotlinLogging.logger("de.emreak.adventofcode.debug")

    /**
     * Reads lines from the given input txt file.
     */
    fun readLines(parent: String = "src/main/resources", filename: String) =
        File(parent, filename).readLines()

    fun readLinesAsInts(parent: String = "src/main/resources", filename: String): List<Int> {
        return readLines(parent, filename).map { it.toInt() }
    }

}

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1,
        MessageDigest.getInstance("MD5").digest(toByteArray())
    ).toString(16)

operator fun String.times(i: Int): String {
    return IntRange(0, i).joinToString("") {
        this
    }
}

/**
 * Create a permutation of the given list
 */
fun <T> List<T>.permutations(): List<List<T>> {
    if (size == 1) return listOf(this)
    return (0 until this.size).flatMap { i ->
        val rest = subList(0, i) + subList(i + 1, size)
        rest.permutations().map {
            listOf(this[i]) + it
        }
    }
}

/*
 *  POWERSETS
 */
fun <T> Collection<T>.powerset(): Set<Set<T>> = powerset(this, setOf(setOf()))

private tailrec fun <T> powerset(left: Collection<T>, acc: Set<Set<T>>): Set<Set<T>> = when {
    left.isEmpty() -> acc
    else -> powerset(left.drop(1), acc + acc.map { it + left.first() })
}

/*
 *  COMBINATIONS
 */

/**
 * In mathematics, a combination is a way of selecting items from a collection, such that (unlike permutations) the
 * order of selection does not matter.
 *
 * For set {1, 2, 3}, 2 elements combinations are {1, 2}, {2, 3}, {1, 3}.
 * All possible combinations is called 'powerset' and can be found as an
 * extension function for set under this name
 */
fun <T> Set<T>.combinations(combinationSize: Int): Set<Set<T>> = when {
    combinationSize < 0 -> throw Error("combinationSize cannot be smaller then 0. It is equal to $combinationSize")
    combinationSize == 0 -> setOf(setOf())
    combinationSize >= size -> setOf(toSet())
    else -> powerset() // TODO this is not the best implementation
        .filter { it.size == combinationSize }
        .toSet()
}

fun <T> Set<T>.combinationsWithRepetitions(combinationSize: Int): Set<Map<T, Int>> = when {
    combinationSize < 0 -> throw Error("combinationSize cannot be smaller then 0. It is equal to $combinationSize")
    combinationSize == 0 -> setOf(mapOf())
    else -> combinationsWithRepetitions(combinationSize - 1)
        .flatMap { subset -> this.map { subset + (it to (subset.getOrElse(it) { 0 } + 1)) } }
        .toSet()
}

/*
 * SPLITS
 */

// Takes set of elements and returns set of splits and each of them is set of sets
fun <T> Set<T>.splits(groupsNum: Int): Set<Set<Set<T>>> = when {
    groupsNum < 0 -> throw Error("groupsNum cannot be smaller then 0. It is equal to $groupsNum")
    groupsNum == 0 -> if (isEmpty()) setOf(emptySet()) else emptySet()
    groupsNum == 1 -> setOf(setOf(this))
    groupsNum == size -> setOf(this.map { setOf(it) }.toSet())
    groupsNum > size -> emptySet()
    else -> setOf<Set<Set<T>>>()
        .plus(splitsWhereFirstIsAlone(groupsNum))
        .plus(splitsForFirstIsInAllGroups(groupsNum))
}

private fun <T> Set<T>.splitsWhereFirstIsAlone(groupsNum: Int): List<Set<Set<T>>> = this
    .minusElement(first())
    .splits(groupsNum - 1)
    .map { it.plusElement(setOf(first())) }

private fun <T> Set<T>.splitsForFirstIsInAllGroups(groupsNum: Int): List<Set<Set<T>>> = this
    .minusElement(first())
    .splits(groupsNum)
    .flatMap { split -> split.map { group -> split.minusElement(group).plusElement(group + first()) } }