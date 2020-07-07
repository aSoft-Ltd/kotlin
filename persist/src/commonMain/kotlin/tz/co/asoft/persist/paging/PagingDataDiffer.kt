/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tz.co.asoft.persist.paging

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import tz.co.asoft.persist.paging.LoadType.REFRESH
import kotlin.jvm.Transient

abstract class PagingDataDiffer<T : Any>(
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    private var presenter: PagePresenter<T> = PagePresenter.initial()
    private var receiver: UiReceiver? = null
    private val dataRefreshedListeners = arrayListOf<(isEmpty: Boolean) -> Unit>()

    private val collectFromRunner = SingleRunner()

    @Transient
    private var lastAccessedIndex: Int = 0

    /**
     * @return Transformed result of [lastAccessedIndex] as an index of [newList] using the diff
     * result between [previousList] and [newList]. Null if [newList] or [previousList] lists are
     * empty, where it does not make sense to transform [lastAccessedIndex].
     */
    abstract suspend fun performDiff(
        previousList: NullPaddedList<T>,
        newList: NullPaddedList<T>,
        newCombinedLoadStates: CombinedLoadStates,
        lastAccessedIndex: Int
    ): Int?

    open fun postEvents(): Boolean = false

    suspend fun collectFrom(
        pagingData: PagingData<T>,
        callback: PresenterCallback
    ) = collectFromRunner.runInIsolation {
        receiver = pagingData.receiver

        pagingData.flow.collect { event ->
            withContext(mainDispatcher) {
                if (event is PageEvent.Insert && event.loadType == REFRESH) {
                    val newPresenter = PagePresenter(event)
                    val transformedLastAccessedIndex = performDiff(
                        previousList = presenter,
                        newList = newPresenter,
                        newCombinedLoadStates = event.combinedLoadStates,
                        lastAccessedIndex = lastAccessedIndex
                    )
                    presenter = newPresenter

                    // Dispatch ListUpdate as soon as we are done diffing.
                    dataRefreshedListeners.forEach { listener ->
                        listener(event.pages.all { page -> page.data.isEmpty() })
                    }

                    // Transform the last loadAround index from the old list to the new list
                    // by passing it through the DiffResult, and pass it forward as a
                    // ViewportHint within the new list to the next generation of Pager.
                    // This ensures prefetch distance for the last ViewportHint from the old
                    // list is respected in the new list, even if invalidation interrupts
                    // the prepend / append load that would have fulfilled it in the old
                    // list.
                    transformedLastAccessedIndex?.let { newIndex ->
                        lastAccessedIndex = newIndex
                        receiver?.addHint(presenter.loadAround(newIndex))
                    }
                } else {
                    if (postEvents()) {
                        yield()
                    }

                    // Send event to presenter to be shown to the UI.
                    presenter.processEvent(event, callback)
                }
            }
        }
    }

    operator fun get(index: Int): T? {
        lastAccessedIndex = index
        receiver?.addHint(presenter.loadAround(index))
        return presenter.get(index)
    }

    /**
     * Retry any failed load requests that would result in a [LoadState.Error] update to this
     * [PagingDataDiffer].
     *
     * [LoadState.Error] can be generated from two types of load requests:
     *  * [PagingSource.load] returning [PagingSource.LoadResult.Error]
     *  * [RemoteMediator.load] returning [RemoteMediator.MediatorResult.Error]
     */
    fun retry() {
        receiver?.retry()
    }

    /**
     * Refresh the data presented by this [PagingDataDiffer].
     *
     * [refresh] triggers the creation of a new [PagingData] with a new instance of [PagingSource]
     * to represent an updated snapshot of the backing dataset. If a [RemoteMediator] is set,
     * calling [refresh] will also trigger a call to [RemoteMediator.load] with [LoadType] [REFRESH]
     * to allow [RemoteMediator] to check for updates to the dataset backing [PagingSource].
     *
     * Note: This API is intended for UI-driven refresh signals, such as swipe-to-refresh.
     * Invalidation due repository-layer signals, such as DB-updates, should instead use
     * [PagingSource.invalidate].
     *
     * @see PagingSource.invalidate
     *
     * @sample androidx.paging.samples.refreshSample
     */
    fun refresh() {
        receiver?.refresh()
    }

    val size: Int
        get() = presenter.size

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _dataRefreshCh = ConflatedBroadcastChannel<Boolean>()

    /**
     * A [Flow] of [Boolean] that is emitted when new [PagingData] generations are submitted and
     * displayed. The [Boolean] that is emitted is `true` if the new [PagingData] is empty,
     * `false` otherwise.
     */
    @ExperimentalPagingApi
    @OptIn(FlowPreview::class)
    val dataRefreshFlow: Flow<Boolean> = _dataRefreshCh.asFlow()

    init {
        @OptIn(ExperimentalCoroutinesApi::class, ExperimentalPagingApi::class)
        addDataRefreshListener { _dataRefreshCh.offer(it) }
    }

    /**
     * Add a listener to observe new [PagingData] generations.
     *
     * @param listener called whenever a new [PagingData] is submitted and displayed. `true` is
     * passed to the [listener] if the new [PagingData] is empty, `false` otherwise.
     *
     * @see removeDataRefreshListener
     */
    @ExperimentalPagingApi
    fun addDataRefreshListener(listener: (isEmpty: Boolean) -> Unit) {
        dataRefreshedListeners.add(listener)
    }

    /**
     * Remove a previously registered listener for new [PagingData] generations.
     *
     * @param listener Previously registered listener.
     *
     * @see addDataRefreshListener
     */
    @ExperimentalPagingApi
    fun removeDataRefreshListener(listener: (isEmpty: Boolean) -> Unit) {
        dataRefreshedListeners.remove(listener)
    }
}
