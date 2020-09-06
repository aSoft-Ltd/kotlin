package tz.co.asoft

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    System.err.println("Does this work")
    val env = mapOf(
        "appender" to mapOf(
            "logsDir" to "/tmp/testlogs"
        ),
        "ktor" to mapOf(
            "port" to "8080"
        )
    )
    launch {
        val logger = RestAppender(
            RestAppenderOptions(
                url = "http://192.168.43.218:8080",
                headers = mapOf("x-api-key" to "123456")
            )
        )

        repeat(100) {
            delay(500)
            when {
                it < 20 -> logger.debug("Testing Debug $it", "source" to "tester", "index" to it)
                it < 40 -> logger.info("Testing Info $it", "source" to "tester", "index" to it)
                it < 60 -> logger.warn("Testing Warn $it", "source" to "tester", "index" to it)
                it < 80 -> logger.error("Testing Error $it", "source" to "tester", "index" to it)
                else -> logger.failure("Testing Failure $it", "source" to "tester", "index" to it)
            }
        }
    }

    LoggingServer.start(env)
}