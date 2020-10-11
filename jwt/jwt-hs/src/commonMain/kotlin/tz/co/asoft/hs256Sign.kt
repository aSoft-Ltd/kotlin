package tz.co.asoft

fun hs256Sign(jwt: JWT, secret: String) = jwt.copy(
    signature = HMAC.hmacSHA256(secret.toByteArray(), jwt.message).base64Url
)