package com.example.fuckjava.customview.b_text_and_trans

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

/**
 * Camera效果
 */
class CameraView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var bitmap = getAvatar(resources, dp2px(300f))
    var camera = Camera()
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var OFFSET_VER: Float = 0.0f
    var OFFSET_HOZ: Float = 0f
    var mymatrix = Matrix()

    init {
        camera.setLocation(0f, 0f, getZForCamera(5))
        camera.rotateY(-60f)
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
        mymatrix.postRotate(-30f)
        mymatrix.postTranslate(OFFSET_HOZ + bitmap.width / 2f, OFFSET_VER + bitmap.height / 2f)
        canvas.concat(mymatrix)
        canvas.clipRect(-bitmap.width, -bitmap.width, 0, bitmap.width)
        mymatrix.reset()
        mymatrix.postTranslate(-(OFFSET_HOZ + bitmap.width / 2f), -(OFFSET_VER + bitmap.height / 2f))
        mymatrix.postRotate(30f)
        canvas.concat(mymatrix)
        canvas.drawBitmap(bitmap, OFFSET_HOZ, OFFSET_VER, paint)
        canvas.restore()

        canvas.save()
        mymatrix.reset()
        mymatrix.postRotate(-30f)
        mymatrix.postTranslate(OFFSET_HOZ + bitmap.width / 2f, OFFSET_VER + bitmap.height / 2f)
        canvas.concat(mymatrix)
        canvas.clipRect(0, -bitmap.width, bitmap.width, bitmap.width)
        camera.applyToCanvas(canvas)
        mymatrix.reset()
        mymatrix.postTranslate(-(OFFSET_HOZ + bitmap.width / 2f), -(OFFSET_VER + bitmap.height / 2f))
        mymatrix.postRotate(30f)
        canvas.concat(mymatrix)
        canvas.drawBitmap(bitmap, OFFSET_HOZ, OFFSET_VER, paint)
        canvas.restore()
    }
}