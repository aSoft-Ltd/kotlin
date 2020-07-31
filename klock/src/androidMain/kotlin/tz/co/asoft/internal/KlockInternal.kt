package tz.co.asoft.internal

import tz.co.asoft.DateTime
import tz.co.asoft.TimeSpan
import tz.co.asoft.milliseconds
import java.util.*

internal actual object KlockInternal {
    actual val currentTime: Double get() = (System.currentTimeMillis()).toDouble()
    actual val microClock: Double get() = (System.nanoTime() / 1000L).toDouble()
    actual fun localTimezoneOffsetMinutes(time: DateTime): TimeSpan = TimeZone.getDefault().getOffset(time.unixMillisLong).milliseconds
}

actual typealias Serializable = java.io.Serializable
