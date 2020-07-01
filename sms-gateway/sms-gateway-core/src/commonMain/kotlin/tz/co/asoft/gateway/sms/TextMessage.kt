package tz.co.asoft.gateway.sms

import kotlinx.serialization.Serializable
import tz.co.asoft.persist.model.Entity

@Serializable
data class TextMessage(
    override var uid: String = "",
    val sender: String,
    val recipients: List<String>,
    val type: String = Type.Text.name,
    val body: String,
    override var deleted: Boolean = false
) : Entity {
    enum class Type {
        Text,
        Flash,
        Unicode,
    }
}