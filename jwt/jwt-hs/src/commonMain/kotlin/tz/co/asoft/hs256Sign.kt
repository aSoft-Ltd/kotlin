package tz.co.asoft

fun hs256Sign(jwt: JWT, secret: String): JWT {
    val header = jwt.header
    val payload = jwt.payload
    val headerHex = header.toJson().toByteArray().base64.replace("=", "")
    val payloadHex = payload.toJson().toByteArray().base64.replace("=", "")
    val hash = HMAC.hmacSHA256(secret.toByteArray(), "$headerHex.$payloadHex".toByteArray()).base64.replace("=", "")
    return JWT(
        header = header,
        payload = payload,
        signature = hash
    )
}