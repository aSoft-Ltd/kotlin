package tz.co.asoft

import tz.co.asoft.DateTime
import kotlin.js.Date

fun Date.toDateTime() = DateTime(this.getTime())
fun DateTime.toDate() = Date(this.unixMillisDouble)
