package tz.co.asoft.rest.dao

import kotlinx.serialization.Serializable
import tz.co.asoft.paging.VKey

@Serializable
class PageRequestInfo(
    val key: VKey,
    val pageSize: Int
)