package tz.co.asoft

fun env() = getEnv()

private val error = Throwable("Can't locate platform.environment.json file")

fun getEnv(): Map<String, Any> {
    val classLoader = ClassLoader.getSystemClassLoader()
    val stream = classLoader.getResourceAsStream("platform.environment.json") ?: throw error
    return stream.bufferedReader().readText().toJsonObject().toMap()
}