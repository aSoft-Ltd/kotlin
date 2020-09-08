package tz.co.asoft

class Storage : IStorage {
    override val name: String get() = ""
    override fun clear() = Unit
    override fun get(key: String): String? = null
    override fun remove(key: String) = Unit
    override fun set(key: String, value: String) = Unit
}