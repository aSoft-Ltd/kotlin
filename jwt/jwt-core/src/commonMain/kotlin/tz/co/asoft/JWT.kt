@file:Suppress("PackageDirectoryMismatch")

package tz.co.asoft

data class JWT(
    val header: JWTHeader,
    val payload: JWTPayload,
    val signature: String? = null
) {
    companion object {
        @OptIn(ExperimentalStdlibApi::class)
        fun from(token: String): JWT {
            val splits = token.split(".")
            val h = splits[0]
            val p = splits[1]
            val signature = splits.getOrNull(2)
            val header = Base64.decode(h).ascii
            val payload = Base64.decode(p).ascii
            return JWT(
                header = header.toJsonObject().toMap().mapValues { (_, it) -> it.toString() }.toMutableMap(),
                payload = payload.toJsonObject().toMap().toMutableMap(),
                signature = signature
            )
        }
    }
}