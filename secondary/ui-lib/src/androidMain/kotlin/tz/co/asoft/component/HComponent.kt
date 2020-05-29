package tz.co.asoft.component

import android.content.Context

abstract class HComponent<P : Any, S : Any, H> : Component<P, S>() {
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