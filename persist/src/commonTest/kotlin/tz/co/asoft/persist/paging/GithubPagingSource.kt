package tz.co.asoft.persist.paging

import tz.co.asoft.persist.model.Data

class GithubPagingSource(
    val service: GithubService,
    val query: String
) : PagingSource<Int, Data>() {
    val startingIndex = 1
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val position = params.key ?: startingIndex
        val data = listOf<Data>()
        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = null
        )
    }
}