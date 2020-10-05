package tz.co.asoft

import kotlinx.serialization.json.Json

class HS512Signer(private val secret: String) : JWTSigner {
    @OptIn(ExperimentalStdlibApi::class)
    override fun sign(jwt: JWT): JWT {
        val header = jwt.header
        val payload = jwt.payload
        val headerHex = Json.stringify(JWTHeader.serializer(), header).encodeBase64ToString()
        val payloadHex = Json.stringify(JWTPayload.serializer(), payload).encodeBase64ToString()
        val hash = SHA256.digest("$headerHex.$payloadHex.$secret".encodeToByteArray()).hex

        return JWT(
            header = header,
            payload = payload,
            signature = hash
        )
    }
}