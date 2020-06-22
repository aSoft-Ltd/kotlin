package tz.co.asoft.camera.imageprocessor

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.provider.MediaStore

fun Uri.getRotationFromCamera(context: Context) = try {
    context.contentResolver.notifyChange(this, null)
    val exif = ExifInterface(path)
    val orientation = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL)

    when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_270 -> 270
        ExifInterface.ORIENTATION_ROTATE_180 -> 180
        ExifInterface.ORIENTATION_ROTATE_90 -> 90
        else -> 0
    }
} catch (e: Exception) {
    e.printStackTrace()
    0
}

fun Uri.getRotationFromGallery(context: Context): Int {
    val columns = arrayOf(MediaStore.Images.Media.ORIENTATION)
    val cursor = context.contentResolver.query(this, columns, null, null, null)
    return try {
        if (cursor?.moveToFirst() == true) {
            val orientationColumnIndex = cursor.getColumnIndex(columns[0])
            cursor?.getInt(orientationColumnIndex)
        } else {
            0
        }
    } catch (e: Exception) {
        0//Do nothing
    } finally {
        cursor?.close()
    }
}

fun Bitmap.rotate(rotation: Float): Bitmap {
    if (rotation != 0f) {
        val matrix = Matrix()
        matrix.postRotate(rotation)
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }
    return this
}