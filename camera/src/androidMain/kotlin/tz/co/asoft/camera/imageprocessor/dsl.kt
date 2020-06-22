package tz.co.asoft.camera.imageprocessor

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

val File.uri get() = Uri.fromFile(this)

suspend fun File.minifyImage(context: Context, widthQuality: Int = 400) = withContext(Dispatchers.IO) {
    val bm = uri.getResizedBitmap(context, widthQuality)
//    val rotation = uri.getRotationFromCamera(context).toFloat()

    val fos = FileOutputStream(this@minifyImage)
    bm.rotate(90f).compress(Bitmap.CompressFormat.JPEG, 85, fos)
    fos.flush()
    fos.close()
}

fun Uri.getMinifiedBitmap(context: Context, widthQuality: Int = 400): Bitmap {
    val bm = getResizedBitmap(context, widthQuality)
    val rotation = getRotationFromGallery(context).toFloat()
    return bm.rotate(rotation)
}