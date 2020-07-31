package tz.co.asoft

import java.util.Date

fun Date.toDateTime() = DateTime(this.time)
fun DateTime.toDate() = Date(this.unixMillisLong)
