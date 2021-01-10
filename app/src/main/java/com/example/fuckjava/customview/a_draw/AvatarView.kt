package com.example.fuckjava.customview.a_draw

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.example.fuckjava.util.dp2px
import com.example.fuckjava.util.getAvatar

/**
 * 图片水印
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class AvatarView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val WIDTH = dp2px(300f)
    lateinit var bounds: RectF

    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap
    var xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    init {
        bitmap = getAvatar(resources,WIDTH)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bounds = RectF(
            (width / 2).toFloat() - WIDTH / 2,
            (height / 2).toFloat() - WIDTH / 2,
            (width / 2).toFloat() + WIDTH / 2,
            (height / 2).toFloat() + WIDTH / 2
        )
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.YELLOW
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), WIDTH / 2 + dp2px(10f), paint)
        val saved = canvas.saveLayer(bounds, paint)
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), WIDTH / 2, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(bitmap, (width - WIDTH) / 2, (height - WIDTH) / 2, paint)
        paint.xfermode = null
        canvas.restoreToCount(saved)
    }
}