package tz.co.asoft.paging

import kotlinx.serialization.Serializable

@Serializable
class Page<K, V>(
    val key: K,
    var prev: Page<K, V>?,
    var next: Page<K, V>?,
    val data: List<V>,
    var pageSize: Int,
    var nextKey: K?
)