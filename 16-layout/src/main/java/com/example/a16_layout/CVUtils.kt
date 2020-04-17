package com.example.a16_layout

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.TypedValue

public var WINDOW_WIDTH:Int = 0

fun dp2px(dp:Float):Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,Resources.getSystem().displayMetrics)