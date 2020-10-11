package tz.co.asoft

import java.security.Signature

object RS512Signer : JWTSigner {
    override fun sign(jwt: JWT, key: SecurityKey): JWT {
        val privateKey = key.toRSAPrivateKey()
        val sig = Signature.getInstance(RS512Algorithm.ALGO_NAME)
        sig.initSign(privateKey)
        sig.update(jwt.message)
        val signature = sig.sign()
        return jwt.copy(signature = signature.base64)
    }
}