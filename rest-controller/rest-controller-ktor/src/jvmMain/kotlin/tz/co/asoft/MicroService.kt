package tz.co.asoft

import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer

open class MicroService(val port: Int = 8080, val modules: List<IModule>) {
    fun start() = embeddedServer(CIO, port) {
        installCORS()
        modules.forEach { it.setRoutes(this) }
        routing {
            get("/status") {
                call.respondText("Healthy")
            }
        }
    }.start(wait = true)
}