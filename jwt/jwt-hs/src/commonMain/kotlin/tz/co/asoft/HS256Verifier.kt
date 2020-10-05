package tz.co.asoft

class HS256Verifier(private val secret: String) : JWTVerifier {
    override fun verify(jwt: JWT): JWTVerification {
        val signature = hs256Sign(jwt, secret).signature
        return if (jwt.signature == signature) JWTVerification.Valid else JWTVerification.Invalid
    }
}