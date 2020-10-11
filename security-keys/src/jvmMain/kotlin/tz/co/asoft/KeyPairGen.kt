package tz.co.asoft

import java.security.KeyPairGenerator
import java.security.SecureRandom

fun generateRSASecurityKeyPair(length: Int = 2048): SecurityKeyPair {
    val kpg = KeyPairGenerator.getInstance("RSA").apply {
        initialize(length, SecureRandom())
    }
    val (priv, pub) = kpg.genKeyPair()
    return SecurityKeyPair(privateKey = priv.toBase64(), publicKey = pub.toBase64())
}