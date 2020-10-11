package tz.co.asoft

open class JWTAlgorithm(
    val name: String,
    private val rotator: KeyRotator,
    private val signer: JWTSigner
) : JWTSigner by signer {
    open suspend fun createJWT(builder: JWTPayload.() -> Unit): JWT {
        val key = rotator.nextSigningKey()

        val header = JWTHeader {
            alg = name
            key.uid?.let {
                kid = it
            }
            typ = "JWT"
        }

        val payload = JWTPayload {
            builder()
            iat = DateTime.nowUnixLong()
            exp = DateTime.nowUnixLong() + 1.days.millisecondsLong
        }
        return sign(JWT(header, payload), key)
    }
}