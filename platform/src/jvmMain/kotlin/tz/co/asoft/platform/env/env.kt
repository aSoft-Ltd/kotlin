package tz.co.asoft.platform.env

import tz.co.asoft.platform.core.Ctx

actual fun Ctx.env() = getEnv()

fun env() = getEnv()

private val error = Throwable("Can't locate platform.environment.json file")

fun getEnv(): Map<String, Any> {
    val classLoader = ClassLoader.getSystemClassLoader()
    val stream = classLoader.getResourceAsStream("platform.environment.json") ?: throw error
    return stream.bufferedReader().readText().toJsonObject().toMap()
}