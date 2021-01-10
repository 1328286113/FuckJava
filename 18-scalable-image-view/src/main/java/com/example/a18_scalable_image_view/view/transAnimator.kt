package com.example.a18_scalable_image_view.view

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.example.a18_scalable_image_view.dpToPixel

class transAnimator(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var pointer: PointF = PointF(dpToPixel(50f), dpToPixel(50f))
        set(value) {
            field = value
            invalidate()
        }

    var transAnimator = ObjectAnimator.ofObject(
        this, "pointer", PointFEvaluator(), PointF(
            dpToPixel(150f),
            dpToPixel(150f)
        )
    )
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        transAnimator.duration = 5000
        transAnimator.start()
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        canvas.translate(pointer.x.toFloat(), pointer.y.toFloat())
        canvas.drawCircle(dpToPixel(50f), dpToPixel(50f), dpToPixel(50f), paint)
        canvas.restore()
    }

    internal class PointFEvaluator :
        TypeEvaluator<PointF> {
        override fun evaluate(
            fraction: Float,
            startValue: PointF,
            endValue: PointF
        ): PointF {
            val x = startValue.x + (endValue.x - startValue.x) * fraction
            val y = startValue.y + (endValue.y - startValue.y) * fraction
            return PointF(x, y)
        }
    }
}