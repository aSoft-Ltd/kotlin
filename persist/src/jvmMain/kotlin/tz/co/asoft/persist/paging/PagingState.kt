package tz.co.asoft.persist.paging

import tz.co.asoft.persist.paging.PagingSource.LoadResult.Page

actual class PagingState<Key : Any, Value : Any> internal constructor(
    val pages: List<Page<Key, Value>>,
    val anchorPosition: Int?,
    val config: PagingConfig,
    private val placeholdersBefore: Int
) {
    fun closestItemToPosition(anchorPosition: Int): Value? {
        if (pages.all { it.data.isEmpty() }) return null

        anchorPositionToPagedIndices(anchorPosition) { pageIndex, index ->
            return when {
                index < 0 -> pages.first().data.first()
                pageIndex == pages.lastIndex && index > pages.last().data.lastIndex -> {
                    pages.last().data.last()
                }
                else -> pages[pageIndex].data[index]
            }
        }
    }

    fun closestPageToPosition(anchorPosition: Int): Page<Key, Value>? {
        if (pages.all { it.data.isEmpty() }) return null

        anchorPositionToPagedIndices(anchorPosition) { pageIndex, index ->
            return when {
                index < 0 -> pages.first()
                else -> pages[pageIndex]
            }
        }
    }

    fun isEmpty() = pages.all { it.data.isEmpty() }

    fun firstItemOrNull(): Value? = pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()

    fun lastItemOrNull(): Value? = pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()

    private inline fun <T> anchorPositionToPagedIndices(
        anchorPosition: Int,
        block: (pageIndex: Int, index: Int) -> T
    ): T {
        var pageIndex = 0
        var index = anchorPosition - placeholdersBefore
        while (pageIndex < pages.lastIndex && index > pages[pageIndex].data.lastIndex) {
            index -= pages[pageIndex].data.size
            pageIndex++
        }

        return block(pageIndex, index)
    }
}