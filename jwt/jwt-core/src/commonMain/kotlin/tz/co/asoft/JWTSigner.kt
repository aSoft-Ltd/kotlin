package tz.co.asoft

interface JWTSigner {
    fun sign(jwt: JWT): JWT
}