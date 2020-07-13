package tz.co.asoft.paging

interface PageLoader<K : Any, D : Any> {
    suspend fun prevOf(node: Page<K, D>): Page<K, D>
    suspend fun nextOf(node: Page<K, D>): Page<K, D>
    suspend fun firstPage(pageSize: Int): Page<K, D>
    fun pager(pageSize: Int, extraPages: Int = 1, showTmpPages: Boolean = true): Pager<*, D> {
        val fetcher = PageFetcher(this, pageSize)
        return Pager(fetcher, showTmpPages)
    }
}