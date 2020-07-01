package tz.co.asoft.gateway.email.core

import kotlinx.serialization.Serializable
import tz.co.asoft.persist.model.Entity

@Serializable
data class EmailMessage(
    override var uid: String = "",
    val sender: String,
    val recipients: List<String>,
    val subject: String?,
    val body: String,
    override var deleted: Boolean = false
) : Entity