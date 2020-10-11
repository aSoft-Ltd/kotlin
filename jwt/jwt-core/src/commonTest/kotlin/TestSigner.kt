import tz.co.asoft.*

class TestSigner : JWTSigner {
    override fun sign(jwt: JWT, key: SecurityKey) = jwt.copy(signature = jwt.message.base64Url + Base64.encode(key.value.toByteArray()))
}