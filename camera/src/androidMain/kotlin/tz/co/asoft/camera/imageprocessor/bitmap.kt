package tz.co.asoft.camera.imageprocessor

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.FileNotFoundException

fun Uri.decodeBitmap(context: Context, sampleSize: Int): Bitmap {
    val options = BitmapFactory.Options()
    options.inSampleSize = sampleSize

    var fileDescriptor: AssetFileDescriptor? = null
    try {
        fileDescriptor = context.contentResolver.openAssetFileDescriptor(this, "r")
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    }

    return BitmapFactory.decodeFileDescriptor(
            fileDescriptor!!.fileDescriptor, null, options)
}