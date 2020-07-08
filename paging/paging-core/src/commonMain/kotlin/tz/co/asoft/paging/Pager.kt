package tz.co.asoft.paging

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tz.co.asoft.persist.model.Entity

class Pager<K : Any, D : Entity>(
    private val fetcher: PageFetcher<K, D>,
    private val showTmpPages: Boolean = true
) : CoroutineScope by CoroutineScope(SupervisorJob()) {

    val state = MutableStateFlow(fetcher.state.value.toPagingState())

    init {
        launch {
            fetcher.state.collect {
                state.value = it.toPagingState()
            }
        }
    }

    private fun PageFetcher.State<K, D>.toPagingState(): PagingState<K, D> = when (this) {
        PageFetcher.State.Idle.FromInitiation -> PagingState.Loading("Loading")
        is PageFetcher.State.Loading -> {
            if (showTmpPages && tmp != null) PagingState.Showing(tmp)
            else PagingState.Loading("Loading")
        }
        is PageFetcher.State.Idle.FromSuccess -> PagingState.Showing(page)
        is PageFetcher.State.Idle.FromFailure -> PagingState.Error(cause)
    }

    fun loadNext() = fetcher.loadNext()

    fun refresh() = fetcher.refresh()

    fun loadPrevious() = fetcher.loadPrevious()
}