package tz.co.asoft

fun JWT.verifyHS256(key: SecurityKey): JWTVerification {
    val newSignature = hs256Sign(this, key.value).signature
    return if (signature == newSignature) JWTVerification.Valid else JWTVerification.Invalid
}