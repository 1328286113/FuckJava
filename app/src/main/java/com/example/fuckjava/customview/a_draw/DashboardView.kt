package com.example.fuckjava.customview.a_draw

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.example.fuckjava.util.dp2px
import kotlin.math.cos
import kotlin.math.sin

/**
 * 仪表盘
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class DashboardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val RADIUS = dp2px(150f)
    val STROKE_WIDTH = dp2px(3f)
    val OPEN_ANGLE = 120
    val POINTER_LENGTH = dp2px(100f)

    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)//抗锯齿
    var dash = Path()
    var path = Path()
    lateinit var effect: PathEffect
    lateinit var pathMeasure: PathMeasure

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = STROKE_WIDTH
        dash.addRect(0f, 0f, dp2px(2f), dp2px(10f), Path.Direction.CW)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()
        path.addArc(
            width / 2 - RADIUS,
            height / 2 - RADIUS,
            width / 2 + RADIUS,
            height / 2 + RADIUS,
            (90 + OPEN_ANGLE / 2).toFloat(), (360 - OPEN_ANGLE).toFloat()
        )
        pathMeasure = PathMeasure(path, false)
        effect = PathDashPathEffect(
            dash,
            (pathMeasure.length - dp2px(2f)) / 20,
            0f,
            PathDashPathEffect.Style.ROTATE
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(path, paint)
        paint.pathEffect = effect
        canvas.drawPath(path, paint)
        paint.pathEffect = null
        canvas.drawLine(
            width / 2f,
            height / 2f,
            width / 2 + (POINTER_LENGTH * cos(getAngle(20))).toFloat(),
            height / 2 + (POINTER_LENGTH * sin(getAngle(20))).toFloat(), paint
        )
    }

    private fun getAngle(mark: Int): Double = Math.toRadians((90 + OPEN_ANGLE / 2 + (360 - OPEN_ANGLE) / 20 * mark).toDouble())
}