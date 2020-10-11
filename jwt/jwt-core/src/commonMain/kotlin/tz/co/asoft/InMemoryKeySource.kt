package tz.co.asoft

class InMemoryKeySource<T : KeyInfo> : Source<T> {
    private val keys = mutableListOf<T>()
    override suspend fun add(key: T): T {
        key.uid = keys.size.toString()
        keys.add(key)
        return key
    }

    override suspend fun remove(key: T): T = key.also { keys.remove(key) }
    override suspend fun load(kid: String): T? = keys.first { it.uid == kid }
    override suspend fun all(): List<T> = keys
}