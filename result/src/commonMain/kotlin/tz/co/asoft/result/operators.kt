package tz.co.asoft.result

fun <T> Result<T>.respond(): T = when (this) {
    is Result.Success -> data
    is Result.Failure -> throw Exception(msg)
}

inline fun <T> Result<T>.catch(handler: (Exception) -> Unit): Result<T> {
    if (this is Result.Failure) {
        handler(Exception(msg))
    }
    return this
}

inline fun <T> Result<T>.collect(handler: (T) -> Unit) {
    if (this is Result.Success) {
        handler(data)
    }
}