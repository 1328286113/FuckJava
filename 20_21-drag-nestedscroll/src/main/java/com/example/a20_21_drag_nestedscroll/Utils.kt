package com.example.a20_21_drag_nestedscroll

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.TypedValue

fun dpToPixel(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dp,
        Resources.getSystem().displayMetrics
    )
}

fun getAvatar(res: Resources?, width: Float): Bitmap {
    val options =
        BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(
        res,
        R.drawable.avatar,
        options
    )
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = width.toInt()
    return BitmapFactory.decodeResource(
        res,
        R.drawable.avatar,
        options
    )
}