package tz.co.asoft.camera.ui

import android.graphics.Matrix
import android.view.Surface
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.camera.core.*
import coil.api.load
import tz.co.asoft.camera.R
import tz.co.asoft.camera.di.injection
import tz.co.asoft.camera.ui.Capture.Handler
import tz.co.asoft.camera.viewmodel.CaptureViewModel
import tz.co.asoft.camera.viewmodel.CaptureViewModel.Intent
import tz.co.asoft.camera.viewmodel.CaptureViewModel.State
import tz.co.asoft.component.HControlledComponent
import tz.co.asoft.klock.DateTime
import tz.co.asoft.klock.asFormated
import tz.co.asoft.platform.core.alert
import tz.co.asoft.ui.ViewHolder
import java.io.File

class Capture : HControlledComponent<Any, Intent, State, CaptureViewModel, Handler>() {
    override val viewModel by lazy { injection.viewModel.capture() }

    interface Handler {
        fun onImageCaptured(image: File, caption: String?)
        fun onExit()
    }

    override val layoutId = R.layout.camera

    private val vh by lazy { VH(view) }

    class VH(v: View?) : ViewHolder(v) {
        val viewFinder: TextureView? by Id(R.id.view_finder)
        val capture: ImageView? by Id(R.id.capture)
        val close: ImageView? by Id(R.id.close)

        val preview: View? by Id(R.id.preview)
        val caption: EditText? by Id(R.id.caption)
        val image: ImageView? by Id(R.id.image)
        val keep: ImageView? by Id(R.id.keep)
        val discard: ImageView? by Id(R.id.discard)
    }

    override fun onReady() {
        super.onReady()
        retainInstance = true
        VH(view).startCamera()
        VH(view).bindUI()
    }

    private fun VH.startCamera() = viewFinder?.also { vf ->
        vf.post {
            val previewConfig = PreviewConfig.Builder().build()
            val preview = Preview(previewConfig)

            val imageCaptureConfig = ImageCaptureConfig.Builder().apply {
                setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
            }.build()

            val imageCapture = ImageCapture(imageCaptureConfig)

            capture?.setOnClickListener {
                val file = File(
                    ACam.imagesDir,
                    DateTime.nowUnixLong().asFormated("yyyy-MM-dd-HH-mm-ss-SSS.jpg")
                )
                imageCapture.takePicture(file, object : ImageCapture.OnImageSavedListener {
                    override fun onImageSaved(file: File) {
                        post(Intent.Preview(file))
                    }

                    override fun onError(
                        imageCaptureError: ImageCapture.ImageCaptureError,
                        message: String,
                        cause: Throwable?
                    ) {
                        ctx.alert("Failed to capture Image: $message, cause: ${cause?.message}")
                    }
                })
            }

            preview.setOnPreviewOutputUpdateListener {
                val parent = vf.parent as ViewGroup
                parent.removeView(vf)
                parent.addView(vf, 0)
                vf.surfaceTexture = it.surfaceTexture
                updateTransform()
            }
            CameraX.bindToLifecycle(this@Capture, preview, imageCapture)
        }
    }

    private fun VH.updateTransform() = viewFinder?.also { vf ->
        vf.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            val matrix = Matrix()
            val centerX = vf.width / 2f
            val centerY = vf.height / 2f

            val rotationDegrees = when (vf.display.rotation) {
                Surface.ROTATION_0 -> 0
                Surface.ROTATION_90 -> 90
                Surface.ROTATION_180 -> 180
                Surface.ROTATION_270 -> 270
                else -> return@addOnLayoutChangeListener
            }
            matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)
            vf.setTransform(matrix)
        }
    }

    private fun VH.showCapture() {
        preview?.visibility = View.INVISIBLE
    }

    private fun VH.bindUI() {
        close?.setOnClickListener {
            handler?.onExit()
        }
    }

    private fun VH.showPreview(im: File) {
        preview?.visibility = View.VISIBLE
        image?.load(im) {
            crossfade(true)
        }
        keep?.setOnClickListener {
            var text = caption?.text?.toString()
            if (text?.isBlank() == true) {
                text = null
            }
            handler?.onImageCaptured(im, text)
            post(Intent.Capture)
        }
        discard?.setOnClickListener {
            im.delete()
            post(Intent.Capture)
        }
    }

    override fun render(ui: State) = when (ui) {
        State.Capturing -> vh.showCapture()
        is State.Previewing -> vh.showPreview(ui.image)
    }
}