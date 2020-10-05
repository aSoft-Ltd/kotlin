package tz.co.asoft

import java.security.KeyPairGenerator
import java.security.SecureRandom

fun Key.Companion.generate(length: Int = 2048): Key {
    val kpg = KeyPairGenerator.getInstance("RSA").apply {
        initialize(length, SecureRandom())
    }
    return Key(kpg.genKeyPair())
}