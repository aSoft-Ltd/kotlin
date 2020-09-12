package tz.co.asoft

fun konfig(): Map<String, Any> {
    val error = Throwable("Can't locate konfig.json file")
    val classLoader = ClassLoader.getSystemClassLoader()
    val stream = classLoader.getResourceAsStream("konfig.json") ?: throw error
    return stream.bufferedReader().readText().toJsonObject().toMap()
}