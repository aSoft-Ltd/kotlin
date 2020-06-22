package tz.co.asoft.camera.imageprocessor

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri

fun Uri.getResizedBitmap(context: Context, widthQuality: Int = 400): Bitmap {
    var bm: Bitmap
    val sampleSizes = intArrayOf(5, 3, 2, 1)
    var i = 0
    do {
        bm = decodeBitmap(context, sampleSizes[i])
        i++
    } while (bm.width < widthQuality && i < sampleSizes.size)
    return bm
}