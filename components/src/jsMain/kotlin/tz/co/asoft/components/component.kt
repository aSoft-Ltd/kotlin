package tz.co.asoft.components

import kotlinext.js.jsObject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import react.*
import tz.co.asoft.viewmodel.VModel

fun <P : RProps> RBuilder.Component(props: P = jsObject(), builder: RBuilder.() -> Unit) = child({
    buildElements {
        builder()
    }
}.unsafeCast<FunctionalComponent<P>>(), props)

@Deprecated("Trust me, you don't want a component that takes a viewmodel as a param")
fun <I, S, V : VModel<I, S>> RBuilder.Component(
    vm: V,
    builder: RBuilder.(state: S, post: (I) -> Any) -> Unit
) = Component(jsObject<RProps>()) {
    val (state, setState) = useState(vm.ui.value)
    useEffectWithCleanup {
        val scope = CoroutineScope(SupervisorJob())
        scope.launch { vm.ui.collect { if (state != it) setState(it) } };
        { scope.cancel() }
    }
    builder(state, vm::post)
}