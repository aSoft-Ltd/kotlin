package tz.co.asoft

interface JWTVerifier {
    fun verify(jwt: JWT): JWTVerification
}