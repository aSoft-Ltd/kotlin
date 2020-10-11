package tz.co.asoft

import java.security.KeyFactory
import java.security.interfaces.RSAPublicKey
import java.security.spec.X509EncodedKeySpec

fun SecurityKey.toRSAPublicKey() = KeyFactory.getInstance("RSA").generatePublic(X509EncodedKeySpec(Base64.decode(value))) as RSAPublicKey