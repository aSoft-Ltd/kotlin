package tz.co.asoft.io

expect class File private constructor(ref: Any, name: String = "tmp") {

    val ref: Any

    var name: String

    constructor(byteArray: ByteArray, name: String = "tmp")

    suspend fun readBytes(): ByteArray

    suspend fun readBytes(onProgress: (Int) -> Unit): ByteArray
}

val File.ext get() = name.split(".").lastOrNull() ?: ""