package tz.co.asoft.io

import kotlinx.serialization.Serializable
import tz.co.asoft.klock.DateTime

@Serializable
open class Document {
    var id: Long? = null
    var uid = ""
    open var name = ""
    open var url = ""
    open var permits = mutableListOf<String>()
    open var metadata = mutableMapOf<String, String>()
    open var created = DateTime.nowUnixLong()
    open var modified = listOf<Long>()
}