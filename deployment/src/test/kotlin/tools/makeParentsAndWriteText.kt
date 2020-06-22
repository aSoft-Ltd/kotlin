package tools

import java.io.File

fun File.makeParentsAndWriteText(text: String) {
    parentFile.mkdirs()
    if (!exists()) createNewFile()
    writeText(text)
}