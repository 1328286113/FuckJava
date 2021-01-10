package com.example.a19_multi_touch.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import com.example.a19_multi_touch.Utils

class MultiTouchView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var paths: SparseArray<Path> = SparseArray()

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = Utils.dpToPx(4f)
        paint.strokeCap = Paint.Cap.ROUND
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var actionIndex = 0
        var pointId = 0
        var path:Path
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN,MotionEvent.ACTION_POINTER_DOWN -> {
                actionIndex = event.actionIndex
                pointId = event.getPointerId(actionIndex)
                path = Path()
                path.moveTo(event.getX(actionIndex), event.getY(actionIndex))
                paths.append(pointId,path)
            }
            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until paths.size()){
                    pointId = event.getPointerId(i)
                    path = paths[pointId]
                    path.lineTo(event.getX(i),event.getY(i))
                }
                invalidate()
            }
            MotionEvent.ACTION_UP,MotionEvent.ACTION_POINTER_UP -> {
                pointId = event.getPointerId(event.actionIndex)
                paths.remove(pointId)
                invalidate()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in 0 until paths.size()){
            var path = paths.valueAt(i)
            canvas.drawPath(path,paint)
        }
    }
}