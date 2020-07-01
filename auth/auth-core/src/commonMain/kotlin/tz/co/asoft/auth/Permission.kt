package tz.co.asoft.auth

import kotlinx.serialization.Serializable

@Serializable
data class Permission(
    val account: UserAccountRef,
    val claims: List<String> = listOf()
)