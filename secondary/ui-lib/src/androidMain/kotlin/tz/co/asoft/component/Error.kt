package tz.co.asoft.component

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import tz.co.asoft.component.Error.Props
import tz.co.asoft.ui.R
import tz.co.asoft.ui.ViewHolder

class Error : Component<Props, Any>() {
    class Props {
        var msg = ""
        var actions = listOf<String>()
    }

    var hander: Handler? = null

    interface Handler {
        fun onErrorAction(action: String)
    }

    override val layoutId = R.layout.error

    class VH(view: View?) : ViewHolder(view) {
        val errorText: TextView? by Id(R.id.error_text)
        val actions: LinearLayout? by Id(R.id.actions)
    }

    override fun onResume() {
        super.onResume()
        VH(view).bindUI()
    }

    private fun String.toButton() = Button(ctx).apply {
        text = this@toButton
        setOnClickListener { hander?.onErrorAction(text.toString()) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val p = parentFragment
        if (p is Handler) {
            hander = p
        }
    }

    private fun VH.bindUI() {
        errorText?.text = props.msg
        actions?.removeAllViews()
        props.actions.forEach { actions?.addView(it.toButton()) }
    }

    override fun onDestroy() {
        hander = null
        super.onDestroy()
    }
}