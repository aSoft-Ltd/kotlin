package tz.co.asoft

fun verifyHS256(jwt: JWT, key: SecurityKey): JWTVerification {
    val signature = hs256Sign(jwt, key.value).signature
    return if (jwt.signature == signature) JWTVerification.Valid else JWTVerification.Invalid
}