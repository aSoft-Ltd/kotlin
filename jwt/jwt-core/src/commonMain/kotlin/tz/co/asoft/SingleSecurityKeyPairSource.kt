package tz.co.asoft

class SingleSecurityKeyPairSource(privateKey: String, publicKey: String) : Source<SecurityKeyPair> {
    val keyPair = SecurityKeyPair(
        uid = "0",
        expires = Long.MAX_VALUE,
        privateKey = privateKey,
        publicKey = publicKey
    )

    override suspend fun add(key: SecurityKeyPair) = key

    override suspend fun remove(key: SecurityKeyPair) = key

    override suspend fun load(kid: String) = keyPair.takeIf { it.uid == kid }

    fun load() = keyPair

    override suspend fun all() = listOf(keyPair)
}