import tz.co.asoft.*

object TestSigner : JWTSigner {
    override fun sign(jwt: JWT, key: SecurityKey): JWT {
        val message = "${jwt.headerInBase64}.${jwt.payloadInBase64}"
        val secret = key.value.toByteArray().base64Url
        val hash = Base64.encode("$message.$secret".toByteArray()).replace("=", "")
        return jwt.copy(signature = hash)
    }
}