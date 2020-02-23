package com.example.fuckjava.customview.d_bitmap_drawable

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.drawable.Drawable
import com.example.fuckjava.util.dp2px

class TopTextDrawable : Drawable() {
    lateinit var text: String
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.textSize = dp2px(12f)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawText(text, 0f, bounds.bottom.toFloat(), paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun getOpacity(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }
}