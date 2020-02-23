package com.example.fuckjava.customview.c_animation

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.fuckjava.util.dp2px
import com.example.fuckjava.util.getAvatar
import com.example.fuckjava.util.getZForCamera

class CameraView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var bitmap = getAvatar(resources, dp2px(300f))
    var camera = Camera()
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var OFFSET_VER: Float = 0.0f
    var OFFSET_HOZ: Float = 0f
    var mymatrix = Matrix()

    var topFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    var bottomFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    var flipRotation = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        camera.setLocation(0f, 0f, getZForCamera(5))
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        OFFSET_VER = (height - bitmap.height) / 2f
        OFFSET_HOZ = (width - bitmap.width) / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        mymatrix.reset()
        mymatrix.postRotate(-flipRotation)
        mymatrix.postTranslate(OFFSET_HOZ + bitmap.width / 2f, OFFSET_VER + bitmap.height / 2f)
        canvas.concat(mymatrix)
        canvas.clipRect(-bitmap.width, -bitmap.width, 0, bitmap.width)
        camera.save()
        camera.rotateY(topFlip)
        camera.applyToCanvas(canvas)
        camera.restore()
        mymatrix.reset()
        mymatrix.postTranslate(
            -(OFFSET_HOZ + bitmap.width / 2f),
            -(OFFSET_VER + bitmap.height / 2f)
        )
        mymatrix.postRotate(flipRotation)
        canvas.concat(mymatrix)
        canvas.drawBitmap(bitmap, OFFSET_HOZ, OFFSET_VER, paint)
        canvas.restore()

        canvas.save()
        mymatrix.reset()
        mymatrix.postRotate(-flipRotation)
        mymatrix.postTranslate(OFFSET_HOZ + bitmap.width / 2f, OFFSET_VER + bitmap.height / 2f)
        canvas.concat(mymatrix)
        canvas.clipRect(0, -bitmap.width, bitmap.width, bitmap.width)
        camera.save()
        camera.rotateY(bottomFlip)
        camera.applyToCanvas(canvas)
        camera.restore()
        mymatrix.reset()
        mymatrix.postTranslate(
            -(OFFSET_HOZ + bitmap.width / 2f),
            -(OFFSET_VER + bitmap.height / 2f)
        )
        mymatrix.postRotate(flipRotation)
        canvas.concat(mymatrix)
        canvas.drawBitmap(bitmap, OFFSET_HOZ, OFFSET_VER, paint)
        canvas.restore()
    }
}