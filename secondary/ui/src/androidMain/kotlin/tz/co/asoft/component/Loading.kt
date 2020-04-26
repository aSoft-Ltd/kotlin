package tz.co.asoft.component

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import tz.co.asoft.component.Loading.Props
import tz.co.asoft.ui.R
import tz.co.asoft.ui.ViewHolder

class Loading : Component<Props, Any>() {
    class Props {
        var msg = ""
    }

    override val layoutId = R.layout.loading

    class VH(view: View?) : ViewHolder(view) {
        //        val progressBar: ProgressBar? by Id(R.id.progress_bar)
        val loadingText: TextView? by Id(R.id.loading_text)
    }

    override fun onResume() {
        super.onResume()
        VH(view).bindUI()
    }

    private fun VH.bindUI() {
        loadingText?.text = props.msg
    }
}