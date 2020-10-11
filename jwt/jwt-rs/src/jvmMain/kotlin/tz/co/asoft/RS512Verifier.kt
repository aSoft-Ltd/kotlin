package tz.co.asoft

import java.security.Signature

fun verifyRS512(jwt: JWT, key: SecurityKey): JWTVerification {
    val publicKey = key.toRSAPublicKey()
    val signature = jwt.signature ?: return JWTVerification.Invalid
    val sig = Signature.getInstance(RS512Algorithm.ALGO_NAME)
    sig.initVerify(publicKey)
    sig.update(jwt.message)
    return if (sig.verify(Base64.decode(signature))) JWTVerification.Valid else JWTVerification.Invalid
}