package tz.co.asoft

import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.spec.PKCS8EncodedKeySpec

fun SecurityKey.toRSAPrivateKey() = KeyFactory.getInstance("RSA").let {
    val decodedKey = Base64.decode(value)
    val keySpec = PKCS8EncodedKeySpec(decodedKey)
    it.generatePrivate(keySpec) as RSAPrivateKey
}