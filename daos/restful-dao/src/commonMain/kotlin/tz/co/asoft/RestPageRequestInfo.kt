package tz.co.asoft

import kotlinx.serialization.Serializable

@Serializable
class RestPageRequestInfo(val key: VKey, val pageSize: Int)