package tz.co.asoft.persist.model

import kotlinx.serialization.Serializable

@Serializable
abstract class Data : Entity {
    override var uid: String = ""
    override var deleted: Boolean = false
}