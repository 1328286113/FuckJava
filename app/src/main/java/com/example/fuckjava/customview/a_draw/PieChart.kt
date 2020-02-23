package com.example.fuckjava.customview.a_draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.fuckjava.util.dp2px
import kotlin.math.cos
import kotlin.math.sin

/**
 * 饼图
 */
class PieChart(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val RADIUS = dp2px(150f)
    val OFFSET_LENGTH = dp2px(10f)
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val ANGLES: Array<Int> = arrayOf(60, 100, 120, 80)
    val COLORS: Array<Int> = arrayOf(
        Color.parseColor("#FF0000"),
        Color.parseColor("#0037FF"),
        Color.parseColor("#68FF00"),
        Color.parseColor("#00FFB7")
    )
    lateinit var rectF: RectF

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF =
            RectF(width / 2 - RADIUS, height / 2 - RADIUS, width / 2 + RADIUS, height / 2 + RADIUS)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var starangle = 0
        for (i in 0..3) {
            if (i == 0) {
                canvas.save()
                canvas.translate(
                    (OFFSET_LENGTH * cos(Math.toRadians((starangle + ANGLES[i] / 2f).toDouble()))).toFloat(),
                    (OFFSET_LENGTH * sin(Math.toRadians((starangle + ANGLES[i] / 2f).toDouble()))).toFloat()
                )
            }
            paint.color = COLORS[i]
            canvas.drawArc(rectF, starangle.toFloat(), ANGLES[i].toFloat(), true, paint)
            starangle += ANGLES[i]
            if(i == 0){
                canvas.restore()
            }
        }
    }
}