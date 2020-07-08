package tz.co.asoft.paging

import tz.co.asoft.persist.model.Entity

class Page<K : Any, D : Entity>(
    val data: List<D>,
    val key: K,
    val prevKey: K?,
    val nextKey: K?
)