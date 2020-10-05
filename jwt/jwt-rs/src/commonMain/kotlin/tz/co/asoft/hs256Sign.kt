package tz.co.asoft

fun hs256Sign(jwt: JWT, secret: String): JWT {
    val header = jwt.header
    val payload = jwt.payload
    val headerHex = header.toJson().encodeBase64ToString()
    val payloadHex = payload.toJson().encodeBase64ToString()
    val key = secret.encodeBase64ToString()
    val hash = SHA256.digest("$headerHex.$payloadHex.$key".toByteArray()).base64.replace("=", "")
    return JWT(
        header = header,
        payload = payload,
        signature = hash
    )
}