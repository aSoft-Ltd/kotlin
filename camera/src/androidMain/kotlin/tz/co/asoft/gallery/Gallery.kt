package tz.co.asoft.gallery

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import tz.co.asoft.camera.R
import tz.co.asoft.component.HComponent
import tz.co.asoft.gallery.Gallery.*
import tz.co.asoft.io.Document
import tz.co.asoft.io.FileRef
import tz.co.asoft.ui.ViewHolder

class Gallery : HComponent<Props, Any, Handler>() {
    override val layoutId = R.layout.layout_gallery

    class Props {
        var pics = listOf<FileRef>()
    }

    interface Handler {
        fun onExit()
    }

    class VH(view: View?) : ViewHolder(view) {
        val back: TextView by Id(R.id.back)
        val images: RecyclerView by Id(R.id.image_grid)
    }

    override fun onReady() {
        super.onReady()
        VH(view).bindUI()
    }

    private fun VH.bindUI() {
        back.setOnClickListener { handler?.onExit() }
        images.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        images.adapter = ImagesAdapter(props.pics)
    }
}