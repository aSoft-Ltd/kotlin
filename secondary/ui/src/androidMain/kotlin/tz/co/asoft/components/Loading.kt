package tz.co.asoft.components

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import tz.co.asoft.ui.R
import tz.co.asoft.ui.ViewHolder

@Deprecated("Use tz.co.asoft.component.Loading")
class Loading : Component<Loading.Props, CState>() {
    class Props : CProps() {
        var msg = ""
    }

    init {
        realProps = Props()
    }

    override val layoutId = R.layout.loading

    class VH(view: View?) : ViewHolder(view) {
        val progressBar: ProgressBar? by Id(R.id.progress_bar)
        val loadingText: TextView? by Id(R.id.loading_text)
    }

    override fun onReady() {
        super.onReady()
        VH(view).bindUI()
    }

    private fun VH.bindUI() {
        loadingText?.text = props.msg
    }
}