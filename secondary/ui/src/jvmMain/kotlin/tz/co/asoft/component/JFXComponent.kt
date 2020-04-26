package tz.co.asoft.component

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tornadofx.Fragment
import tornadofx.vbox
import tz.co.asoft.persist.tools.Cause

abstract class JFXComponent<P : Any, S : Any> : Fragment(), ParentContainer {

    @PublishedApi
    internal var realProps: P? = null
    protected var props: P
        set(value) {
            realProps = value
        }
        get() = realProps ?: throw Cause("Props are not yet initialized")

    @PublishedApi
    internal var realState: S? = null
    protected var state: S
        set(value) {
            realState = value
        }
        get() = realState ?: throw Cause("State is not yet initialized")

    val attrs: P get() = props

    fun attrs(builder: P.() -> Unit) = props.apply(builder)

    protected open fun setState(builder: S.() -> Unit) {
        realState?.apply(builder)
        executeRender()
    }

    override fun onDock() {
        super.onDock()
        GlobalScope.launch(Dispatchers.Main) {

        }
        executeRender()
    }

    open fun executeRender() {
        render()
    }

    override val root = vbox { }

    abstract override fun render(): Any
}