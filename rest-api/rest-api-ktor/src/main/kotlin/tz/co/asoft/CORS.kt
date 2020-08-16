package tz.co.asoft

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.http.HttpMethod

fun Application.installCORS() {
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Patch)
        method(HttpMethod.Delete)
        header("Access-Control-Allow-Origin")
        header("Access-Control-Allow-Methods")
        header("Access-Control-Allow-Headers")
        anyHost()
    }
}