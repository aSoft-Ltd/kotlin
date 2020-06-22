package tz.co.asoft.klock.js

import tz.co.asoft.klock.DateTime
import kotlin.js.Date

fun Date.toDateTime() = DateTime(this.getTime())
fun DateTime.toDate() = Date(this.unixMillisDouble)
