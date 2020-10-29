package tz.co.asoft

fun <T> Failure.toResult(): Result<T> = Either.Right(this)