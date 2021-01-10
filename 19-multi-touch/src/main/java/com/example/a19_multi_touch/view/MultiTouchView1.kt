package com.example.a19_multi_touch.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.a19_multi_touch.Utils

class MultiTouchView1(context: Context, attrs: AttributeSet) : View(context, attrs) {
    val IMAGE_WIDTH = Utils.dpToPx(300f)
    val bitmap = Utils.getAvatar(resources, IMAGE_WIDTH.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var offsetX = 0f
    var offsetY = 0f
    var downX = 0f
    var downY = 0f
    var OrginalOffsetX = 0f
    var OrginalOffsetY = 0f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var sumX = 0f
        var sumY = 0f
        var isPointUp = event.actionMasked == MotionEvent.ACTION_POINTER_UP
        var pointerCount = event.pointerCount
        for (i in 0 until event.pointerCount) {
            if(!isPointUp || i != event.actionIndex){
                sumX += event.getX(i)
                sumY += event.getY(i)
            }
        }
        if (isPointUp) {
            pointerCount--
        }
        var focusX = sumX/pointerCount
        var focusY = sumY/pointerCount
        when (event.actionMasked) {  //actionMasked 和 action 的区别：action只针对单点，actionMasked针对多点 我们都用actionMasked
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_POINTER_DOWN,
            MotionEvent.ACTION_POINTER_UP -> {
                downX = focusX
                downY = focusY
                OrginalOffsetX = offsetX
                OrginalOffsetY = offsetY
            }
            MotionEvent.ACTION_MOVE -> {
                offsetX = focusX - downX + OrginalOffsetX
                offsetY = focusY - downY + OrginalOffsetY
                invalidate()
            }
        }
        return true
    }
}