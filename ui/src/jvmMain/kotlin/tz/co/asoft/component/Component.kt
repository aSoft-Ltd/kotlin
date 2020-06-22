package tz.co.asoft.component

import kotlinx.coroutines.*
import tz.co.asoft.LifeCycleMethods
import tz.co.asoft.platform.core.Activity
import tz.co.asoft.platform.core.Ctx
import tz.co.asoft.platform.core.FragmentActivity
import tz.co.asoft.rx.LiveData
import tz.co.asoft.ui.action.Action
import kotlin.coroutines.CoroutineContext

abstract class Component<P : Any, S : Any> : JFXComponent<P, S>(), CoroutineScope, LifeCycleMethods {
    companion object {
        val globalActivity = FragmentActivity()
    }

    protected val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val application get() = app

    val activity: Activity get() = globalActivity

    val act: FragmentActivity get() = globalActivity

    val ctx: Ctx get() = activity

    init {
        initComponent()
    }

    private fun initComponent() {
        launch {
            if (realProps == null || realState == null) {
                delay(5)
                initComponent()
            } else {
                onReady()
            }
        }
    }

    override fun onReady() {
        executeRender()
    }

    override fun executeRender() {
        launch(Dispatchers.Main) {
            render()
        }
    }

    override fun onDock() {
        super.onDock()
        onResume()
    }

    protected fun syncState(context: CoroutineContext = coroutineContext, buildState: suspend S.() -> Unit) {
        launch(context) {
            state.buildState()
            setState { }
        }
    }

    open fun showLoading(message: String) = child(Loading::class.java, Loading.Props()) {
        attrs { msg = message }
    }

    open fun showError(message: String, actions: List<Action> = listOf()) = child(Error::class.java, Error.Props()) {
        attrs { msg = message }
        attrs.actions = actions
    }

    fun <T> LiveData<T>.observe(action: (T) -> Unit) = onChange(this@Component, action)

    override fun onUndock() {
        job.cancel()
        onPause()
        super.onUndock()
    }

    override fun onDelete() {
        onDestroy()
        super.onDelete()
    }
}