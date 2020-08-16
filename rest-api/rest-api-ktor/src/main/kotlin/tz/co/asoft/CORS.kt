package tz.co.asoft

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.headersOf

val HttpHeaders.XAuthToken get() = "X-Auth-Token"

fun Application.installCORS() {
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Patch)
        method(HttpMethod.Delete)
        headersOf(HttpHeaders.AccessControlAllowOrigin, "*")
        headersOf(
            HttpHeaders.AccessControlAllowMethods to listOf(
                HttpMethod.Get.value,
                HttpMethod.Post.value,
                HttpMethod.Put.value,
                HttpMethod.Patch.value,
                HttpMethod.Delete.value,
                HttpMethod.Head.value,
                HttpMethod.Options.value
            ),
            HttpHeaders.AccessControlAllowHeaders to listOf(
                HttpHeaders.Origin, HttpHeaders.ContentType, HttpHeaders.XAuthToken
            )
        )
        anyHost()
    }
}