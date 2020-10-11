@file:UseSerializers(LongAsStringSerializer::class)

package tz.co.asoft

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.internal.LongAsStringSerializer

@Serializable
data class SecurityKey(
    override var uid: String?=null,
    override val created: Long = DateTime.nowUnixLong(),
    override val expires: Long = DateTime.nowUnixLong() + 30.days.millisecondsLong,
    val value: String
) : KeyInfo {
    init {
        require(expires > created) { "Make sure expiration of keys happens after creation" }
    }
}