package tz.co.asoft.test

import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

expect fun asyncTest(block: suspend () -> Unit)

expect abstract class AsyncTest() : CoroutineScope {
    override val coroutineContext: CoroutineContext
    fun asyncTest(block: suspend () -> Unit)
}