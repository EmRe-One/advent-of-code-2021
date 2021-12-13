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
