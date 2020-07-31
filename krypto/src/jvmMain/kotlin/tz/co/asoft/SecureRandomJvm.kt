package tz.co.asoft

import java.security.SecureRandom

private val jrandom = SecureRandom()

actual fun fillRandomBytes(array: ByteArray) {
    jrandom.nextBytes(array)
}
