package com.example.fuckjava.customview.c_animation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.fuckjava.util.dp2px

class TextArrayView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var start = "泷泽萝拉"
        set(value) {
            field = value
            invalidate()
        }

    init {
        paint.textAlign = Paint.Align.CENTER
        paint.textSize = dp2px(20f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawText(start, width / 2f, height / 2f, paint)
    }


}