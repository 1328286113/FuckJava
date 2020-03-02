package com.example.a16_layout.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.a16_layout.dp2px

class CircleImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val RADIUS = dp2px(80f)
    private val PADDING = dp2px(30f)

    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var size = ((RADIUS + PADDING) * 2).toInt()
        var width = resolveSize(size, widthMeasureSpec)
        var height = resolveSize(size, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.RED)
        canvas.drawCircle(RADIUS + PADDING, RADIUS + PADDING,RADIUS,paint)
    }
}