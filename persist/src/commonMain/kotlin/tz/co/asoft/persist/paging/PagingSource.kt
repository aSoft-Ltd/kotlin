package tz.co.asoft.persist.paging

import kotlinx.atomicfu.atomic

abstract class PagingSource<Key : Any, Value : Any> {

    sealed class LoadParams<Key : Any>(
        val loadSize: Int,
        val placeholdersEnabled: Boolean
    ) {
        abstract val key: Key?

        class Refresh<Key : Any>(
            override val key: Key?,
            loadSize: Int,
            placeholdersEnabled: Boolean
        ) : LoadParams<Key>(loadSize, placeholdersEnabled)

        class Append<Key : Any>(
            override val key: Key,
            loadSize: Int,
            placeholdersEnabled: Boolean
        ) : LoadParams<Key>(loadSize, placeholdersEnabled)

        class Prepend<Key : Any>(
            override val key: Key,
            loadSize: Int,
            placeholdersEnabled: Boolean
        ) : LoadParams<Key>(loadSize, placeholdersEnabled)

        internal companion object {
            fun <Key : Any> create(
                loadType: LoadType,
                key: Key?,
                loadSize: Int,
                placeholdersEnabled: Boolean
            ): LoadParams<Key> = when (loadType) {
                LoadType.REFRESH -> Refresh(
                    key = key,
                    loadSize = loadSize,
                    placeholdersEnabled = placeholdersEnabled
                )
                LoadType.PREPEND -> Prepend(
                    loadSize = loadSize,
                    key = requireNotNull(key) {
                        "key cannot be null for prepend"
                    },
                    placeholdersEnabled = placeholdersEnabled
                )
                LoadType.APPEND -> Append(
                    loadSize = loadSize,
                    key = requireNotNull(key) {
                        "key cannot be null for append"
                    },
                    placeholdersEnabled = placeholdersEnabled
                )
            }
        }
    }

    sealed class LoadResult<Key : Any, Value : Any> {
        data class Error<Key : Any, Value : Any>(val throwable: Throwable) :
            LoadResult<Key, Value>()

        data class Page<Key : Any, Value : Any>(
            val data: List<Value>,
            val prevKey: Key?,
            val nextKey: Key?,
            val itemsBefore: Int = COUNT_UNDEFINED,
            val itemsAfter: Int = COUNT_UNDEFINED
        ) : LoadResult<Key, Value>() {
            init {
                require(itemsBefore == COUNT_UNDEFINED || itemsBefore >= 0) {
                    "itemsBefore cannot be negative"
                }

                require(itemsAfter == COUNT_UNDEFINED || itemsAfter >= 0) {
                    "itemsAfter cannot be negative"
                }
            }

            companion object {
                const val COUNT_UNDEFINED = Int.MIN_VALUE
                internal val EMPTY = Page(emptyList(), null, null, 0, 0)
                internal fun <Key : Any, Value : Any> empty() = EMPTY as Page<Key, Value>
            }
        }
    }

    open val jumpingSupported: Boolean
        get() = false
    open val keyReuseSupported: Boolean
        get() = false

    open fun getRefreshKey(state: PagingState<Key, Value>): Key? = null

    private val onInvalidatedCallbacks = arrayListOf<() -> Unit>()

    private val _invalid = atomic(false)
    val invalid: Boolean
        get() = _invalid.value

    open fun invalidate() {
        if (_invalid.compareAndSet(false, true)) {
            onInvalidatedCallbacks.forEach { it.invoke() }
        }
    }

    fun registerInvalidatedCallback(onInvalidatedCallback: () -> Unit) {
        onInvalidatedCallbacks.add(onInvalidatedCallback)
    }

    fun unregisterInvalidatedCallback(onInvalidatedCallback: () -> Unit) {
        onInvalidatedCallbacks.remove(onInvalidatedCallback)
    }

    abstract suspend fun load(params: LoadParams<Key>): LoadResult<Key, Value>
}
