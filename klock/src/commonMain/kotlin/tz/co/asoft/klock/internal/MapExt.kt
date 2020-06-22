package tz.co.asoft.klock.internal

internal fun <K> MutableMap<K, Int>.increment(key: K) {
    this.getOrPut(key) { 0 }
    this[key] = this[key]!! + 1
}
