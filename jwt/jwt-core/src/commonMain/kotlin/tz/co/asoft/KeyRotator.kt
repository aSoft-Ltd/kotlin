package tz.co.asoft

interface KeyRotator {
    suspend fun nextSigningKey(): SecurityKey
}