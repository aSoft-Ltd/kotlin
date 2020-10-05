package tz.co.asoft

abstract class JWTAlgorithm(
    val name: String,
    private val signer: JWTSigner,
    private val verifier: JWTVerifier
) : JWTSigner by signer, JWTVerifier by verifier {
    abstract fun create(): JWT
}