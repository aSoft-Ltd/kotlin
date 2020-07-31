package tz.co.asoft.internal

import tz.co.asoft.DateTime
import tz.co.asoft.TimeSpan

internal expect object KlockInternal {
    val currentTime: Double
    val microClock: Double
    fun localTimezoneOffsetMinutes(time: DateTime): TimeSpan
}

expect interface Serializable
