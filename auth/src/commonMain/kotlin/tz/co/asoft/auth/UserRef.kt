package tz.co.asoft.auth

import kotlinx.serialization.Serializable

@Serializable
class UserRef(
    val id: Long?,
    val uid: String,
    val name: String,
    val photoUrl:String?   
)