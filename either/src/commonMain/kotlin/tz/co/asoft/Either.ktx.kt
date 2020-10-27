package tz.co.asoft

inline val <L,R> Either<L,R>.value get() = when(this) {
    is Either.Left -> value
    is Either.Right -> value
}

fun <L, R> Either<L, R>.left(): L = when (this) {
    is Either.Left -> value
    is Either.Right -> throw Exception("Can't get left side of either coz it is right side")
}

fun <L, R> Either<L, R>.leftOrNull(): L? = try {
    left()
} catch (_: Throwable) {
    null
}

fun <L, R> Either<L, R>.right(): R = when (this) {
    is Either.Left -> throw Exception("Can't get right side of either coz it is left side")
    is Either.Right -> value
}

fun <L, R> Either<L, R>.rightOrNull(): R? = try {
    right()
} catch (_: Throwable) {
    null
}