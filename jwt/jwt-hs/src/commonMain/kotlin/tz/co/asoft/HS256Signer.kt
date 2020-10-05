package tz.co.asoft

class HS256Signer(private val secret: String) : JWTSigner {
    override fun sign(jwt: JWT) = hs256Sign(jwt, secret)
}