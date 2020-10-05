@file:Suppress("PackageDirectoryMismatch")
@file:UseSerializers(LongAsStringSerializer::class)

package tz.co.asoft

import kotlinx.serialization.UseSerializers
import kotlinx.serialization.internal.LongAsStringSerializer

typealias JWTPayload = MutableMap<String, Any>

fun JWTPayload(builder: JWTPayload.() -> Unit): JWTPayload = mutableMapOf<String, Any>().apply(builder)

var JWTPayload.iat: Long
    set(value) {
        put("iat", value)
    }
    get() = get("iat")?.toString()?.toLong() ?: error("iat not found in payload")

val JWTPayload.iatOrNull
    get() = try {
        iat
    } catch (e: Throwable) {
        null
    }

var JWTPayload.exp: Long
    set(value) {
        put("exp", value)
    }
    get() = get("exp")?.toString()?.toLong() ?: error("exp not found in payload")

val JWTPayload.expOrNull
    get() = try {
        exp
    } catch (e: Throwable) {
        null
    }

var JWTPayload.aid: String
    set(value) {
        put("aid", value)
    }
    get() = get("aid")?.toString() ?: error("aid not found in payload")

val JWTPayload.aidOrNull
    get() = try {
        aid
    } catch (e: Throwable) {
        null
    }

var JWTPayload.uid: String
    set(value) {
        put("uid", value)
    }
    get() = get("uid")?.toString() ?: error("uid not found in payload")

val JWTPayload.uidOrNull
    get() = try {
        uid
    } catch (e: Throwable) {
        null
    }

var JWTPayload.userName: String
    set(value) {
        put("userName", value)
    }
    get() = get("userName")?.toString() ?: error("userName not found in payload")

val JWTPayload.userNameOrNull
    get() = try {
        userName
    } catch (e: Throwable) {
        null
    }

var JWTPayload.accountName: String
    set(value) {
        put("accountName", value)
    }
    get() = get("accountName")?.toString() ?: error("accountName not found in payload")

val JWTPayload.accountNameOrNull
    get() = try {
        accountName
    } catch (e: Throwable) {
        null
    }

var JWTPayload.claimId: String
    set(value) {
        put("claimId", value)
    }
    get() = get("claimId")?.toString() ?: error("claimId not found in payload")

val JWTPayload.claimIdOrNull
    get() = try {
        claimId
    } catch (e: Throwable) {
        null
    }

var JWTPayload.claims: List<String>
    set(value) {
        put("claims", value)
    }
    get() = (get("claims") as? List<String>) ?: error("claims not found in payload")

val JWTPayload.claimsOrNull
    get() = try {
        claims
    } catch (t: Throwable) {
        null
    }