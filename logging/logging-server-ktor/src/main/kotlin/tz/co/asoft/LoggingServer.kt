package tz.co.asoft

import io.ktor.application.call
import io.ktor.features.origin
import io.ktor.http.HttpStatusCode
import io.ktor.http.RequestConnectionPoint
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.util.toMap

object LoggingServer {

    val RequestConnectionPoint.url get() = "$scheme://$remoteHost:$port"

    val allowed = mapOf(
        "123456" to listOf(
            "http://localhost:8080",
            "http://127.0.0.1:8080",
            "http://192.168.43.218:8080"
        )
    )

    /**
     * @param env must have {
     *  appender: {
     *      logsDir: "/tmp/sampledir", // must be absolute path
     * },
     * ktor: {
     *      port: 8080
     * }
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun start(env: Map<String, *>) {
        val appender = env["appender"] as Map<String, String>
        val logsDir: String by appender
        val ktor = env["ktor"] as Map<String, String>
        val port: String? by ktor
        val ktorPort = port?.toIntOrNull() ?: 8080
        Logging.init(console, FileAppender(FileAppenderOptions(File(logsDir))))
        val log = Logger.of("logger" to "Logging Server")
        log.i("starting logging server")
        embeddedServer(CIO, port = ktorPort) {
            routing {
                post("/") {
                    try {
                        val apiKey = call.request.headers["x-api-key"]
                        console.obj(apiKey)
                        if (apiKey != null) {
                            console.obj(allowed)
                            if (allowed[apiKey]?.contains(call.request.origin.url) == true) {
                                console.log("valid api key")
                                val params = call.receiveParameters().toMap().mapValues { it.value.first() }.toMutableMap()
                                val level = LogLevel.valueOf(params["level"] ?: throw Exception("A log must have level"))
                                params.remove("level")
                                val msg = params["message"] ?: throw Exception("A log must have a msg")
                                params.remove("message")
                                log.append(level = level, msg = msg, data = *params.map { it.key to it.value }.toTypedArray())
                                call.respond(HttpStatusCode.OK, "Appended")
                            } else {
                                console.log("Unregistered api key")
                                call.respond(HttpStatusCode.Forbidden, "Invalid Api Key")
                            }
                        } else {
                            call.respond(HttpStatusCode.BadRequest, "No Api Key Provided")
                        }
                    } catch (e: Throwable) {
                        call.respond(HttpStatusCode.BadRequest, "Failed")
                    }
                }

                get("/status") {
                    call.respond(HttpStatusCode.OK, "Healthy")
                }
            }
            log.i("logging server started on port $ktorPort")
        }.start(wait = true)
    }
}