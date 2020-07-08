package tz.co.asoft.paging

import tz.co.asoft.persist.model.Entity

sealed class PagingState<K : Any, D : Entity> {
    class Loading<K : Any, D : Entity>(val msg: String) : PagingState<K, D>()
    class Showing<K : Any, D : Entity>(val page: Page<K, D>) : PagingState<K, D>()
    class Error<K : Any, D : Entity>(val cause: Throwable?) : PagingState<K, D>()
}