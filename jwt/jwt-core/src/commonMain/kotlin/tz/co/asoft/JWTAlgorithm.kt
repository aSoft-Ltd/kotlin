package tz.co.asoft

open class JWTAlgorithm(
    val name: String,
    private val signer: JWTSigner,
    private val verifier: JWTVerifier
) : JWTSigner by signer, JWTVerifier by verifier {
    open fun createJWT(builder: JWTBuilder.() -> Unit): JWT {
        val jwt = JWTBuilder().apply {
            builder()
            header.alg = name
        }.build()
        return sign(jwt)
    }
}