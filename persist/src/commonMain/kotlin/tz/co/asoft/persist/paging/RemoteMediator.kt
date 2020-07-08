package tz.co.asoft.persist.paging

import tz.co.asoft.persist.paging.RemoteMediator.InitializeAction.LAUNCH_INITIAL_REFRESH

abstract class RemoteMediator<Key : Any, Value : Any> {
    abstract suspend fun load(loadType: LoadType, state: PagingState<Key, Value>): MediatorResult
    open suspend fun initialize(): InitializeAction = LAUNCH_INITIAL_REFRESH

    sealed class MediatorResult {
        class Error(val throwable: Throwable) : MediatorResult()
        class Success(val endOfPaginationReached: Boolean) : MediatorResult()
    }

    enum class InitializeAction {
        LAUNCH_INITIAL_REFRESH,
        SKIP_INITIAL_REFRESH
    }
}