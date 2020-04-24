package tz.co.asoft.klock.internal

import tz.co.asoft.klock.*

internal expect object KlockInternal {
    val currentTime: Double
    val microClock: Double
    fun localTimezoneOffsetMinutes(time: DateTime): TimeSpan
}

expect interface Serializable
