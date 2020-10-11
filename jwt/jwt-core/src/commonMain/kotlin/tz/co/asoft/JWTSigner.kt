package tz.co.asoft

interface JWTSigner {
    fun sign(jwt: JWT, key: SecurityKey): JWT
}