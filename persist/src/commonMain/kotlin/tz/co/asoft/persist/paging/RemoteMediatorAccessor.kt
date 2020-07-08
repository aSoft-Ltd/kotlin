package tz.co.asoft.persist.paging

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import tz.co.asoft.persist.paging.RemoteMediator.MediatorResult

internal class RemoteMediatorAccessor<Key : Any, Value : Any>(
    private val remoteMediator: RemoteMediator<Key, Value>
) {
    private val jobsByLoadTypeLock = Mutex()
    private val jobsByLoadType = HashMap<LoadType, Deferred<MediatorResult>>()

    suspend fun initialize(): RemoteMediator.InitializeAction {
        return remoteMediator.initialize()
    }

    internal suspend fun load(
        scope: CoroutineScope,
        loadType: LoadType,
        state: PagingState<Key, Value>
    ): MediatorResult {
        val deferred = jobsByLoadTypeLock.withLock {
            if (jobsByLoadType[loadType]?.isActive != true) {
                val existingJobs = jobsByLoadType.values.toList()
                val existingBoundaryJobs = listOfNotNull(
                    jobsByLoadType[LoadType.PREPEND],
                    jobsByLoadType[LoadType.APPEND]
                )

                jobsByLoadType[loadType] = scope.async {
                    doLoad(loadType, state, existingJobs, existingBoundaryJobs)
                }
            }
            jobsByLoadType.getValue(loadType)
        }

        return deferred.await()
    }

    private suspend fun doLoad(
        loadType: LoadType,
        state: PagingState<Key, Value>,
        existingJobs: List<Job>,
        existingBoundaryJobs: List<Job>
    ): MediatorResult {
        if (loadType == LoadType.REFRESH) {
            existingBoundaryJobs.forEach { it.cancel() }
            existingBoundaryJobs.joinAll()
        }
        existingJobs.forEach { it.join() }
        return remoteMediator.load(loadType, state)
    }
}