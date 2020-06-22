package tz.co.asoft.test

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

actual fun asyncTest(block: suspend () -> Unit) = runBlocking {
    block()
}

actual abstract class AsyncTest actual constructor() : CoroutineScope {
    actual override val coroutineContext: CoroutineContext = Dispatchers.Default
    actual fun asyncTest(block: suspend () -> Unit) = runBlocking {
        block()
    }
}
