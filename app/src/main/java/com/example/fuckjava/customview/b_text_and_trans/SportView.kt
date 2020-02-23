package com.example.fuckjava.customview.b_text_and_trans

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.fuckjava.util.dp2px

/**
 * 文字居中
 */
class SportView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val RADIUS = dp2px(150f)
    lateinit var rectF: RectF
    val paint = Paint()
    val text = "Muggle"
    var bounds = Rect()
    var fontMetrics = Paint.FontMetrics()

    init {
        paint.textSize = dp2px(80f)
        paint.textAlign = Paint.Align.CENTER
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF =
            RectF(width / 2 - RADIUS, height / 2 - RADIUS, width / 2 + RADIUS, height / 2 + RADIUS)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.YELLOW
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = dp2px(20f)
        canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)

        paint.color = Color.LTGRAY
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(rectF, -90f, 240f, false, paint)

        // 绘制文字
        paint.style = Paint.Style.FILL
//        paint.getTextBounds(text, 0, text.length, bounds)
//        var offset = (bounds.top + bounds.bottom) / 2
        paint.getFontMetrics(fontMetrics)
        var offset = (fontMetrics.ascent + fontMetrics.descent) / 2f
        canvas.drawText(text, width / 2f, height / 2f - offset, paint)
    }
}