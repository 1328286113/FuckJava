package com.example.a19_multi_touch.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.a19_multi_touch.Utils

class MultiTouchView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    val IMAGE_WIDTH = Utils.dpToPx(300f)
    val bitmap = Utils.getAvatar(resources, IMAGE_WIDTH.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var offsetX = 0f
    var offsetY = 0f
    var downX = 0f
    var downY = 0f
    var OrginalOffsetX = 0f
    var OrginalOffsetY = 0f
    var trackingPointerId = 0

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(offsetX, offsetY)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {  //actionMasked 和 action 的区别：action只针对单点，actionMasked针对多点 我们都用actionMasked
            MotionEvent.ACTION_DOWN -> {
                trackingPointerId = event.getPointerId(0)
                downX = event.x
                downY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                var index = event.findPointerIndex(trackingPointerId)
                offsetX = event.getX(index) - downX + OrginalOffsetX
                offsetY = event.getY(index) - downY + OrginalOffsetY
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                OrginalOffsetX = offsetX
                OrginalOffsetY = offsetY
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                var actionIndex = event.actionIndex
                trackingPointerId = event.getPointerId(actionIndex)
                OrginalOffsetX = offsetX
                OrginalOffsetY = offsetY
                downX = event.getX(actionIndex)
                downY = event.getY(actionIndex)
            }
            MotionEvent.ACTION_POINTER_UP -> {
                var actionIndex = event.actionIndex
                var pointId = event.getPointerId(actionIndex)
                if (pointId == trackingPointerId) {
                    var newIndex =
                        if (actionIndex == event.pointerCount - 1) event.pointerCount - 2 else event.pointerCount - 1
                    trackingPointerId = event.getPointerId(newIndex)
                    downX = event.getX(newIndex)
                    downY = event.getY(newIndex)
                    OrginalOffsetX = offsetX
                    OrginalOffsetY = offsetY
                }
            }
        }
        return true
    }
}