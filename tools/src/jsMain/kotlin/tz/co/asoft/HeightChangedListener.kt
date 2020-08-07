package tz.co.asoft

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLElement

private inline var HTMLElement.storedOffsetHeight: Int?
    get() = try {
        "${asDynamic()["storedOffsetHeight"]}".toIntOrNull()
    } catch (e: Throwable) {
        null
    }
    set(value) {
        asDynamic()["storedOffsetHeight"] = value
    }

fun HTMLElement.onHeightChangedWhileTyping(scope: CoroutineScope, handler: suspend (Int) -> Unit) {
    var diff = 0
    scope.launch {
        delay(1)
        storedOffsetHeight = offsetHeight
        diff = scrollHeight - offsetHeight
        handler(scrollHeight)
    }
    onkeydown = {
        scope.launch {
            delay(1)
            if (offsetHeight != storedOffsetHeight) {
                storedOffsetHeight = offsetHeight
                handler(offsetHeight + diff)
            }
        }
    }
}