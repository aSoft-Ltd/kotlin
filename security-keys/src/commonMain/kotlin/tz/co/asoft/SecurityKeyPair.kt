@file:UseSerializers(LongAsStringSerializer::class)

package tz.co.asoft

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.internal.LongAsStringSerializer

@Serializable
data class SecurityKeyPair(
    override var uid: String? = null,
    override val created: Long = DateTime.nowUnixLong(),
    override val expires: Long = DateTime.nowUnixLong() + 30.days.millisecondsLong,
    val privateKey: String,
    val publicKey: String
) : KeyInfo {
    init {
        require(expires > created) { "Make sure expiration of keys happens after creation" }
    }

    val privateSecurityKey get() = SecurityKey(uid, created, expires, privateKey)
    val publicSecurityKey get() = SecurityKey(uid, created, expires, publicKey)
}