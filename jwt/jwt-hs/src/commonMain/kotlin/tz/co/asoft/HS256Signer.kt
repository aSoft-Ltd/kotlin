package tz.co.asoft

object HS256Signer : JWTSigner {
    override fun sign(jwt: JWT, key: SecurityKey) = hs256Sign(jwt, key.value)
}