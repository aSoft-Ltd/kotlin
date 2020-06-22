package tz.co.asoft.component

import android.content.Context
import tz.co.asoft.viewmodel.ViewModel

abstract class HControlledComponent<P : Any, I : Any, S : Any, V : ViewModel<I, S>, H> : ControlledComponent<P, I, S, V>() {
    var handler: H? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        handler = parentFragment as? H
    }

    override fun onDestroy() {
        handler = null
        super.onDestroy()
    }
}