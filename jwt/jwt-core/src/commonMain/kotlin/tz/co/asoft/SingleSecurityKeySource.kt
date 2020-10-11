package tz.co.asoft

class SingleSecurityKeySource(secret: String) : Source<SecurityKey> {
    private val key = SecurityKey(
        uid = "0",
        expires = Long.MAX_VALUE,
        value = secret
    )

    override suspend fun add(key: SecurityKey) = key

    override suspend fun remove(key: SecurityKey) = key

    override suspend fun load(kid: String) = key.takeIf { key.uid == kid }

    override suspend fun all() = listOf(key)
}