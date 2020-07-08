package tz.co.asoft.persist.paging

sealed class LoadState(
    val endOfPaginationReached: Boolean
) {
    class NotLoading(endOfPaginationReached: Boolean) : LoadState(endOfPaginationReached) {
        override fun toString(): String {
            return "NotLoading(endOfPaginationReached=$endOfPaginationReached)"
        }

        override fun equals(other: Any?): Boolean {
            return other is NotLoading && endOfPaginationReached == other.endOfPaginationReached
        }

        override fun hashCode(): Int {
            return endOfPaginationReached.hashCode()
        }

        internal companion object {
            internal val Complete = NotLoading(endOfPaginationReached = true)
            internal val Incomplete = NotLoading(endOfPaginationReached = false)
        }
    }

    object Loading : LoadState(false) {
        override fun toString(): String {
            return "Loading(endOfPaginationReached=$endOfPaginationReached)"
        }

        override fun equals(other: Any?): Boolean {
            return other is Loading && endOfPaginationReached == other.endOfPaginationReached
        }

        override fun hashCode(): Int {
            return endOfPaginationReached.hashCode()
        }
    }

    class Error(val error: Throwable) : LoadState(false) {
        override fun equals(other: Any?): Boolean {
            return other is Error && endOfPaginationReached == other.endOfPaginationReached &&
                    error == other.error
        }

        override fun hashCode(): Int {
            return endOfPaginationReached.hashCode() + error.hashCode()
        }

        override fun toString(): String {
            return "Error(endOfPaginationReached=$endOfPaginationReached, error=$error)"
        }
    }
}
