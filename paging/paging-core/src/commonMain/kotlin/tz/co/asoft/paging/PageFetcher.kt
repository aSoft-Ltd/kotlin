package tz.co.asoft.paging

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import tz.co.asoft.paging.dao.Pageable
import tz.co.asoft.persist.model.Entity

class PageFetcher<K : Any, D : Entity>(
    val dao: Pageable<K,D>,
    private val pageSize: Int,
    private val extraPages: Int
) : CoroutineScope by CoroutineScope(SupervisorJob()) {
    val state = MutableStateFlow(State.Idle.FromInitiation as State<K, D>)

    private var latestDisplayedPage: Page<K, D>? = null

    private val pages = mutableMapOf<K, Page<K, D>>()

    private var job: Job? = null

    init {
        loadNext()
    }

    sealed class State<K : Any, D : Entity> {
        sealed class Loading<K : Any, D : Entity>(val tmp: Page<K, D>?) : State<K, D>() {
            class Previous<K : Any, D : Entity>(tmp: Page<K, D>?) : Loading<K, D>(tmp)
            class Next<K : Any, D : Entity>(tmp: Page<K, D>?) : Loading<K, D>(tmp)
            class Refresh<K : Any, D : Entity>(tmp: Page<K, D>?) : Loading<K, D>(tmp)
        }

        sealed class Idle<K : Any, D : Entity> : State<K, D>() {
            object FromInitiation : Idle<Any, Entity>()
            class FromSuccess<K : Any, D : Entity>(val page: Page<K, D>) : Idle<K, D>()
            class FromFailure<K : Any, D : Entity>(val cause: Throwable?) : Idle<K, D>()
        }
    }

    fun loadNext() {
        if (state.value !is State.Loading.Next<*, *>) {
            job?.cancel()
            job = launch {
                flow {
                    val nextKey = latestDisplayedPage?.nextKey
                    emit(State.Loading.Next(pages[nextKey]))
                    val page = dao.load(PageRequestInfo(nextKey, pageSize))
                    emit(State.Idle.FromSuccess(page))
                    latestDisplayedPage = page
                    page.nextKey?.let { loadNextExtraPages(startingFrom = it) }
                }.catch {
                    emit(State.Idle.FromFailure(it.cause))
                }.collect {
                    state.value = it
                }
            }
        }
    }

    private suspend fun loadNextExtraPages(startingFrom: K) {
        var next: K? = startingFrom
        var iterations = 0
        while (next != null && iterations < extraPages) {
            val nextPage = dao.load(PageRequestInfo(next, pageSize))
            pages[next] = nextPage
            next = nextPage.nextKey
            iterations++
        }
    }

    fun loadPrevious() {
        if (state.value !is State.Loading.Previous<*, *>) {
            job?.cancel()
            job = launch {
                flow {
                    val prevKey = latestDisplayedPage?.prevKey
                    emit(State.Loading.Previous(pages[prevKey]))
                    val page = dao.load(PageRequestInfo(prevKey, pageSize))
                    emit(State.Idle.FromSuccess(page))
                    latestDisplayedPage = page
                    page.prevKey?.let { loadPreviousExtraPages(startingFrom = it) }
                }.catch {
                    emit(State.Idle.FromFailure(it.cause))
                }.collect {
                    state.value = it
                }
            }
        }
    }

    private suspend fun loadPreviousExtraPages(startingFrom: K) {
        var prev: K? = startingFrom
        var iterations = 0
        while (prev != null && iterations < extraPages) {
            val prevPage = dao.load(PageRequestInfo(prev, pageSize))
            pages[prev] = prevPage
            prev = prevPage.prevKey
            iterations++
        }
    }

    fun refresh() {
        if (state.value !is State.Loading.Refresh<*, *>) {
            job?.cancel()
            job = launch {
                flow {
                    val key = latestDisplayedPage?.key
                    emit(State.Loading.Previous(pages[key]))
                    val page = dao.load(PageRequestInfo(key, pageSize))
                    emit(State.Idle.FromSuccess(page))
                    page.nextKey?.let { loadNextExtraPages(startingFrom = it) }
                    page.prevKey?.let { loadPreviousExtraPages(startingFrom = it) }
                }.catch {
                    emit(State.Idle.FromFailure(it.cause))
                }.collect {
                    state.value = it
                }
            }
        }
    }
}