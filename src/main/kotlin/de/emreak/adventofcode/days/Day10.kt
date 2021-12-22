package de.emreak.adventofcode.days

import java.util.Stack

object Day10 {

    fun isStringCorrupted(input: String): Boolean {
        val stack = Stack<Char>()

        for(currentChar in input) {
            if (currentChar in arrayOf('(', '[', '{', '<')) {
                stack.push(currentChar)
            }
            else {
                if (stack.isEmpty()) {
                    return true
                }

                when(stack.pop()) {
                    '(' -> if (currentChar != ')') {
                        return true
                    }
                    '[' -> if (currentChar != ']') {
                        return true
                    }
                    '{' -> if (currentChar != '}') {
                        return true
                    }
                    '<' -> if (currentChar != '>') {
                        return true
                    }
                }
            }
        }
        return false
    }

    fun part1(input: List<String>): Long {
        val scores = mapOf(
            ')' to 3L,
            ']' to 57L,
            '}' to 1197L,
            '>' to 25137L
        )

        return input.filter {
            isStringCorrupted(it)
        }.sumOf { line ->
            var firstCorruptedChar = '!'
            val stack = Stack<Char>()

            for(c in line) {
                if (c in arrayOf('(', '[', '{', '<')) {
                    stack.push(c)
                }
                else {
                    if (stack.isEmpty()) {
                        firstCorruptedChar = c
                        break
                    }

                    when(stack.pop()) {
                        '(' -> if (c != ')') {
                            firstCorruptedChar = c
                            break
                        }
                        '[' -> if (c != ']') {
                            firstCorruptedChar = c
                            break
                        }
                        '{' -> if (c != '}') {
                            firstCorruptedChar = c
                            break
                        }
                        '<' -> if (c != '>') {
                            firstCorruptedChar = c
                            break
                        }
                    }
                }
            }

            scores[firstCorruptedChar]!!
        }
    }

    fun part2(input: List<String>): Long {
        val sortedScoreList = input.filter {
            !isStringCorrupted(it)
        }.map { line ->
            val stack = Stack<Char>()

            for(c in line) {
                if (c in arrayOf('(', '[', '{', '<')) {
                    stack.push(c)
                }
                else {
                    stack.pop()
                }
            }

            var score = 0L
            for(c in stack.reversed()) {
                score = score * 5 + when(c) {
                    '(' -> 1
                    '[' -> 2
                    '{' -> 3
                    '<' -> 4
                    else -> 0
                }
            }
            score
        }.sortedBy { it }

        return sortedScoreList[sortedScoreList.size / 2]
    }
}
