package tz.co.asoft

val KeyInfo.privateKey: SecurityKey
    get() = when (this) {
        is SecurityKey -> this
        is SecurityKeyPair -> privateSecurityKey
        else -> error("Unkown key")
    }