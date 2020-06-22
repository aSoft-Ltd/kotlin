package tz.co.asoft.camera.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import tz.co.asoft.camera.ui.ACam.Props
import tz.co.asoft.component.Component
import tz.co.asoft.components.android.child
import java.io.File

class ACam : Component<Props, Any>(), Capture.Handler {

    class Props {
        var onExit = {}
        var onImageCaptured: (image: File, caption: String?) -> Unit = { _, _ -> }
    }

    companion object {
        val acamDir = File(Environment.getExternalStorageDirectory(), "acam").apply { mkdirs() }
        val imagesDir = File(acamDir, "images").apply { mkdirs() }
        val videosDir = File(acamDir, "videos").apply { mkdirs() }
    }

    override fun onImageCaptured(image: File, caption: String?) {
        props.onImageCaptured(image, caption)
    }

    override fun onReady() {
        super.onReady()
        checkPermitsAndLaunch()
    }

    override fun onExit() {
        props.onExit()
    }

    private fun cameraIsNotPermitted() = ContextCompat.checkSelfPermission(ctx, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED

    private val REQUEST_CODE = 0x00010

    private fun checkPermitsAndLaunch() {
        if (cameraIsNotPermitted()) {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CAMERA), REQUEST_CODE)
        } else {
            showCapture()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            checkPermitsAndLaunch()
        }
    }

    private fun showCapture() = child(Capture::class.java)
}