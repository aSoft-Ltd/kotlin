package tz.co.asoft.internal

internal inline fun Int.chainComparison(comparer: () -> Int): Int = if (this == 0) comparer() else this
