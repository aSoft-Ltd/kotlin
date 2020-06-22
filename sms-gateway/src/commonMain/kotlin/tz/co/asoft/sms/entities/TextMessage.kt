package tz.co.asoft.sms.entities

import kotlinx.serialization.Serializable
import tz.co.asoft.persist.model.Entity

@Serializable
open class TextMessage(
        val sender: String,
        val recipients: List<String>,
        val type: String = Type.Text.name,
        val body: String
) : Entity {
    override var uid: String = ""

    enum class Type {
        Text,
        Flash,
        Unicode,
    }
}