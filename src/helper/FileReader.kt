package helper

import java.io.File

object FileReader {

    fun readLinesToList(fileName: String): List<String> {
        val file = File(fileName)
        return file.readLines()
    }

    fun readLinesToNumberList(fileName: String): List<Int> {
        return readLinesToList(fileName).map { it.toInt() }
    }

}