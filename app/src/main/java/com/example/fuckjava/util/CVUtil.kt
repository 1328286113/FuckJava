package com.example.fuckjava.util

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.TypedValue
import com.example.fuckjava.R

fun dp2px(dp:Float):Float =TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,Resources.getSystem().displayMetrics)

fun getAvatar(resources: Resources, width: Float): Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = width.toInt()
    return BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
}

fun getZForCamera(des:Int): Float =  -des * Resources.getSystem().displayMetrics.density
