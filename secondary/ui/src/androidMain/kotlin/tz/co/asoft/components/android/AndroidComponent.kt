package tz.co.asoft.components.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import tz.co.asoft.persist.tools.Cause
import tz.co.asoft.ui.R

abstract class AndroidComponent<P : Any, S : Any> : Fragment(), Parent {
    @PublishedApi
    internal var realProps: P? = null
    protected var props: P
        set(value) {
            realProps = value
        }
        get() = realProps ?: arguments?.props ?: throw Cause("Props are not yet initialized")

    @PublishedApi
    internal var realState: S? = null
    protected var state: S
        set(value) {
            realState = value
        }
        get() = realState ?: throw Cause("State is not yet initialized")

    open val layoutId by lazy { R.layout.fragment_frame }
    override val frameId by lazy { R.id.frame }

    override val fManager get() = childFragmentManager

    val attrs: P get() = props

    fun attrs(builder: P.() -> Unit) = props.apply(builder)

    private val backDispatcher by lazy { requireActivity().onBackPressedDispatcher }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realProps = arguments?.props

        if (realProps == null) {
            realProps = savedInstanceState?.props
        }

        if (realState == null) {
            realState = savedInstanceState?.state
        }
    }

    private val Bundle.props: P? get() = load("props")

    private val Bundle.state: S? get() = load("state")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return (view ?: inflater.inflate(layoutId, container, false)).also {
            if (savedInstanceState == null) {
                executeRender()
            }
            backDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (onBackPressed()) {
                        backDispatcher.onBackPressed()
                    }
                }
            })
        }
    }

    protected open fun setState(builder: S.() -> Unit) {
        realState?.apply(builder)
        executeRender()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        realProps?.let { outState.append("props", it::class, it) }
        realState?.let { outState.append("state", it::class, it) }
        super.onSaveInstanceState(outState)
    }

    open fun executeRender() {
        render()
    }

    open fun render(): Any = Unit

    override fun onDestroy() {
        realProps = null
        realState = null
        super.onDestroy()
    }

    open fun onBackPressed(): Boolean = false
}
