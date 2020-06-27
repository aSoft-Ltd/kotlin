package tz.co.asoft.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import tz.co.asoft.camera.R
import tz.co.asoft.io.Document
import tz.co.asoft.io.FileRef

class ImagesAdapter(val images: List<FileRef>) : RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder>() {
    class ImagesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.image)
        val caption = view.findViewById<TextView>(R.id.caption)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_gallery_image, parent, false)
        return ImagesViewHolder(view)
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val img = images[position]
        holder.apply {
            image.load(img.url) {placeholder(R.drawable.icon_camera)}
            caption.text = img.metadata["caption"] ?: ""
        }
    }
}