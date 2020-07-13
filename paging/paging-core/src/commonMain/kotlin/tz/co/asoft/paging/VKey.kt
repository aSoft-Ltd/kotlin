package tz.co.asoft.paging

import kotlinx.serialization.Serializable

@Serializable
data class VKey(val pageNo: Int, val uid: String?)