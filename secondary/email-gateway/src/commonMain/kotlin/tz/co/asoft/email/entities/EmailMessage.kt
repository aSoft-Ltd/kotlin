package tz.co.asoft.email.entities

import kotlinx.serialization.Serializable
import tz.co.asoft.persist.model.Entity

@Serializable
class EmailMessage(
        val sender: String,
        val recipients: List<String>,
        val subject: String?,
        val body: String
) : Entity {
    override var uid: String = ""
}