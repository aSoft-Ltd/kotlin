package tz.co.asoft.klock.jvm

import tz.co.asoft.klock.DateTime
import java.util.*

fun Date.toDateTime() = DateTime(this.time)
fun DateTime.toDate() = Date(this.unixMillisLong)
