@file:Suppress("PackageDirectoryMismatch")

package tz.co.asoft

typealias JWTHeader = MutableMap<String, String>

fun JWTHeader(builder: JWTHeader.() -> Unit): JWTHeader = mutableMapOf<String, String>().apply {
    alg = "none"
    typ = "JWT"
    builder()
}

var JWTHeader.alg: String
    set(value) {
        put("alg", value)
    }
    get() = get("alg") ?: error("alg not present in header")

val JWTHeader.algOrNull
    get() = try {
        alg
    } catch (_: Throwable) {
        null
    }

var JWTHeader.typ: String
    set(value) {
        put("typ", value)
    }
    get() = get("typ") ?: error("typ not present in header")

val JWTHeader.typOrNull
    get() = try {
        typ
    } catch (_: Throwable) {
        null
    }

var JWTHeader.kid: String
    set(value) {
        put("kid", value)
    }
    get() = get("kid") ?: error("kid not present in header")

val JWTHeader.kidOrNull
    get() = try {
        kid
    } catch (_: Throwable) {
        null
    }
