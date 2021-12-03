import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

object AdventOfCodeUtils {

    /**
     * Reads lines from the given input txt file.
     */
    fun readLines(parent: String = "src", name: String) =
        File(parent, "$name.txt").readLines()

    fun readLinesAsInts(parent: String = "src", name: String): List<Int> {
        return readLines(parent, name).map { it.toInt() }
    }

}

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5")
    .digest(toByteArray()))
    .toString(16)
