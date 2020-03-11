package com.example.a18_scalable_image_view.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import com.example.a18_scalable_image_view.getAvatar

class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs),
    GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    var IMAGE_WIDTH = 0
    var OVER_SCALE_FACTOR = 1.5f
    var big: Boolean = false
    var offsetX = 0f
    var offsetY = 0f
    var bigScale = 0f
    var smallScale = 0f

    var currentScale = 0f
        set(value) {
            field = value
            invalidate()
        }
    var scaleAnimator: ObjectAnimator? = null

    var scroller = OverScroller(context)
    var paint = Paint()
    lateinit var bitmap: Bitmap
    var gestureDetector = GestureDetectorCompat(context, this)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        IMAGE_WIDTH = width
        bitmap = getAvatar(resources, IMAGE_WIDTH)
        gestureDetector.setOnDoubleTapListener(this)
//        bigScale =
//            if (bitmap.width / bitmap.height > width / height) height / bitmap.height * OVER_SCALE_FACTOR else width / bitmap.width * OVER_SCALE_FACTOR
//        smallScale =
//            (if (bitmap.width / bitmap.height > width / height) width / bitmap.width else height / bitmap.height).toFloat()
        bigScale =
            if (bitmap.width / width > bitmap.height / height) height / bitmap.height * OVER_SCALE_FACTOR else width / bitmap.width * OVER_SCALE_FACTOR
        smallScale =
            (if (bitmap.width / width > bitmap.height / height) width / bitmap.width else height / bitmap.height).toFloat()
        currentScale = smallScale
        fixOffset()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        var scaleFraction = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.translate(offsetX*scaleFraction, offsetY*scaleFraction)
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        canvas.restore()
    }

    private fun fixOffset() {
        offsetX = ((width - bitmap.width * currentScale) / 2)
        offsetY = ((height - bitmap.height * currentScale) / 2)
    }

    private fun getAnimator(): ObjectAnimator? {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", 0f)
        }
        scaleAnimator?.setFloatValues(smallScale, bigScale)
        return scaleAnimator
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)  //代理了默认的触摸监听器
    }

    override fun onShowPress(e: MotionEvent?) {    // ⽤户按下 100ms 不松⼿后会被调⽤，⽤于标记「可以显示按下状态了」

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {  // ⽤户单击时被调⽤(⽀持⻓按时⻓按后松⼿不会调⽤、双击的第⼆下时不会被调⽤)
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {  // 每次 ACTION_DOWN 事件出现的时候都会被调⽤，在这⾥返回 true 可以保证必然消费掉事件
        return true
    }

    override fun onFling(   // ⽤于滑动时迅速抬起时被调⽤，⽤于⽤户希望控件进⾏惯性滑动的场景
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return false
    }

    override fun onScroll(// ⽤户滑动时被调⽤
        e1: MotionEvent?,
        e2: MotionEvent?,// 第⼀个事件是⽤户按下时的 ACTION_DOWN 事件，第⼆个事件是当前事件
        distanceX: Float,// 偏移是按下时的位置 - 当前事件的位置
        distanceY: Float
    ): Boolean {
        return false
    }

    /**
     * ⽤户⻓按（按下 500ms 不松⼿）后会被调⽤
     * 这个 500ms 在 GestureDetectorCompat 中变成了 600ms
     */
    override fun onLongPress(e: MotionEvent?) {
        TODO("Not yet implemented")
    }

    /**
     * ⽤户双击时被调⽤
     * 注意：第⼆次触摸到屏幕时就调⽤，⽽不是抬起时
     */
    override fun onDoubleTap(e: MotionEvent?): Boolean {
        big = !big
        if (big) {
            fixOffset()
            getAnimator()?.start()
        } else {
            getAnimator()?.reverse()
        }
        invalidate()
        return false
    }

    /**
     *  ⽤户双击第⼆次按下时、第⼆次按下后移动时、第⼆次按下后抬起时都会被调⽤
     *  常⽤于「双击拖拽」的场景
     */
    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return false
    }

    /**
     * ⽤户单击时被调⽤
     * 和 onSingltTapUp() 的区别在于，⽤户的⼀次点击不会⽴即调⽤这个⽅法，⽽是在⼀定时间后（300ms），确认⽤户没有进⾏双击，这个⽅法才会被调⽤
     */
    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return false
    }
}