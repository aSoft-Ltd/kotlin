package tz.co.asoft.persist.paging

import tz.co.asoft.persist.paging.PagingSource.LoadResult.Page.Companion.COUNT_UNDEFINED

actual class PagingConfig constructor(
    val pageSize: Int,
    val prefetchDistance: Int = pageSize,
    val enablePlaceholders: Boolean = true,

    @JvmField
    val initialLoadSize: Int = pageSize * DEFAULT_INITIAL_PAGE_MULTIPLIER,

    @JvmField
    val maxSize: Int = MAX_SIZE_UNBOUNDED,

    @JvmField
    val jumpThreshold: Int = COUNT_UNDEFINED
) {
    init {
        if (!enablePlaceholders && prefetchDistance == 0) {
            throw IllegalArgumentException(
                "Placeholders and prefetch are the only ways" +
                        " to trigger loading of more data in PagingData, so either placeholders" +
                        " must be enabled, or prefetch distance must be > 0."
            )
        }
        if (maxSize != MAX_SIZE_UNBOUNDED && maxSize < pageSize + prefetchDistance * 2) {
            throw IllegalArgumentException(
                "Maximum size must be at least pageSize + 2*prefetchDist" +
                        ", pageSize=$pageSize, prefetchDist=$prefetchDistance" +
                        ", maxSize=$maxSize"
            )
        }

        require(jumpThreshold == COUNT_UNDEFINED || jumpThreshold > 0) {
            "jumpThreshold must be positive to enable jumps or COUNT_UNDEFINED to disable jumping."
        }
    }

    companion object {
        const val MAX_SIZE_UNBOUNDED = Int.MAX_VALUE
        internal const val DEFAULT_INITIAL_PAGE_MULTIPLIER = 3
    }
}