package tz.co.asoft.rx

import kotlinx.coroutines.*
import tz.co.asoft.test.AsyncTest
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.test.Test

class LiveDataTest : AsyncTest() {

    private val liveInt = LiveData(1)

    @Test
    fun should_work() = asyncTest {
        val changer = async {
            repeat(9) {
                delay(1000)
                println("Setting : $it, Scope: $isActive")
                liveInt.value = it
            }
        }

        val observer = async {
            coroutineScope {
                liveInt.onChange(this) {
                    println("Value is $it, Scope: Test Scope $isActive")
                }
                delay(6000)
            }
        }

        liveInt.onChange(GlobalScope) {
            println("Value is $it, Scope: Global Scope $isActive")
        }

        delay(3000)
        observer.cancel()
        changer.await();
    }
}