package tz.co.asoft

class LinearKeyRotator<T : KeyInfo>(
    val maxKeys: Int,
    val source: Source<T> = InMemoryKeySource(),
    val generator: () -> T
) : KeyRotator {
    private var lastKeyIndex: Int? = null
    override suspend fun nextSigningKey(): SecurityKey {
        val allKeys = source.all()
        if (allKeys.size < maxKeys) {
            val key = source.add(generator())
            lastKeyIndex = allKeys.indexOf(key)
            return key.privateKey
        }
        val kIndex = ((lastKeyIndex ?: -1) + 1) % maxKeys
        lastKeyIndex = kIndex
        return allKeys[kIndex].privateKey
    }
}