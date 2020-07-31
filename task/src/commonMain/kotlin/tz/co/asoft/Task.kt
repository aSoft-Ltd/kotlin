package tz.co.asoft

sealed class Task<T> {
    class Progress<T>(val pct: Int, val msg: String = "") : Task<T>() {
        override fun toString() = "$pct%: $msg"
    }

    class Failed<T>(val cause: Throwable) : Task<T>()
    class Completed<T>(val data: T) : Task<T>()
}