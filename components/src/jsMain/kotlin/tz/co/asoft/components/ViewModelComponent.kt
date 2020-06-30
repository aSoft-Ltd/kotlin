package tz.co.asoft.components

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import react.RBuilder
import tz.co.asoft.viewmodel.VModel

class TVM : VModel<TVM.Intent, TVM.State>(State(0)) {
    class State(val count: Int)

    sealed class Intent {
        class Increment(val amount: Int) : Intent()
        class Decrement(val amount: Int) : Intent()
    }

    override fun post(i: Intent) = when (i) {
        is Intent.Increment -> launch { increment(i) }
        is Intent.Decrement -> launch { decrement(i) }
    }

    private suspend fun increment(i: Intent.Increment) {
        repeat(i.amount) {
            ui.value = State(ui.value.count + 1)
            delay(1000)
        }
    }

    private suspend fun decrement(i: Intent.Decrement) {
        repeat(i.amount) {
            ui.value = State(ui.value.count - 1)
            delay(1000)
        }
    }
}

private val vm by lazy { TVM() }
fun RBuilder.TestComponent() = Component(vm) { state, post ->

}