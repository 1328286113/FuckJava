package com.example.a18_scalable_image_view.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.GestureDetector.*
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.example.a18_scalable_image_view.dpToPixel
import com.example.a18_scalable_image_view.getAvatar
import kotlin.math.max
import kotlin.math.min
import android.view.GestureDetector.SimpleOnGestureListener as SimpleOnGestureListener1

class ScalableImageView : View {
    var IMAGE_WIDTH = dpToPixel(500f)
    var OVER_SCALE_FACTOR = 1.5f
    var big: Boolean = false
    var offsetX = 0f
    var offsetY = 0f
    var bigScale = 0f
    var smallScale = 0f
    var maxOffsetX = 0f
    var maxOffsetY = 0f
    var originalOffsetX = 0f
    var originalOffsetY = 0f

    var currentScale = 1f
        set(value) {
            field = value
            invalidate()
        }
    var scaleAnimator: ObjectAnimator? = null

    var scroller = OverScroller(context)
    var paint = Paint()
    var bitmap: Bitmap

    var myGestureDetectorListener = MyGestureDetector()
    var gestureDetector = GestureDetectorCompat(context, myGestureDetectorListener)
    var myScaleGestureListener = MyScaleDestureListener()
    var myScaleGestureDetector = ScaleGestureDetector(context, myScaleGestureListener)
    var myAnimatorRunnable: MyRunner = MyRunner()

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        bitmap = getAvatar(resources, IMAGE_WIDTH)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        smallScale =
            if (bitmap.width.toFloat() / bitmap.height > width.toFloat() / height) (width.toFloat() / bitmap.width) else (height.toFloat() / bitmap.height)
        bigScale =
            (if (bitmap.width.toFloat() / bitmap.height > width.toFloat() / height) (height.toFloat() / bitmap.height) else (width.toFloat() / bitmap.width)) * OVER_SCALE_FACTOR
        currentScale = smallScale
        originalOffsetX = (width - bitmap.width) / 2f
        originalOffsetY = (height - bitmap.height) / 2f
        maxOffsetX = (bitmap.width * bigScale - width) / 2
        maxOffsetY = (bitmap.height * bigScale - height) / 2
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        val scaleFraction = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
        canvas.restore()
    }

    private fun fixOffset() {
        offsetX = max(min(offsetX, maxOffsetX), -maxOffsetX)
        offsetY = max(min(offsetY, maxOffsetY), -maxOffsetY)
    }

    private fun getAnimator(): ObjectAnimator? {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", 0f)
        }
        scaleAnimator?.setFloatValues(smallScale, bigScale)
        return scaleAnimator
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var result = myScaleGestureDetector.onTouchEvent(event)
        if (myScaleGestureDetector.isInProgress) {    //判断当前是否正在缩放
            result = gestureDetector.onTouchEvent(event)   //代理了默认的触摸监听器
        }
        return result
    }

    private fun refresh() {
        if (scroller.computeScrollOffset()) {   //计算惯性滑动的距离，返回值：Boolean 判断动画是否还在进行
            offsetX = scroller.currX.toFloat()
            offsetY = scroller.currY.toFloat()
            invalidate()
            postOnAnimation(myAnimatorRunnable)
        }
    }

    inner class MyGestureDetector : GestureDetector.SimpleOnGestureListener() {
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
            if (big) {
                scroller.fling(
                    offsetX.toInt(),
                    offsetY.toInt(),
                    velocityX.toInt(),
                    velocityY.toInt(),
                    (-maxOffsetX).toInt(),
                    maxOffsetX.toInt(),
                    (-maxOffsetY).toInt(),
                    maxOffsetY.toInt(),
                    dpToPixel(50f).toInt(),
                    dpToPixel(50f).toInt()
                )
                ViewCompat.postOnAnimation(
                    this@ScalableImageView,
                    myAnimatorRunnable
                )    //让view在下一帧执行里面的run，与post(Runnable)不同，post是在当前主线程立刻执行
            }
            return false
        }

        override fun onScroll(// ⽤户滑动时被调⽤
            e1: MotionEvent?,
            e2: MotionEvent?,// 第⼀个事件是⽤户按下时的 ACTION_DOWN 事件，第⼆个事件是当前事件
            distanceX: Float,// 偏移是按下时的位置 - 当前事件的位置
            distanceY: Float
        ): Boolean {
            if (big) {
                offsetX -= distanceX
                offsetY -= distanceY
                fixOffset()
                invalidate()
            }
            return false
        }

        /**
         * ⽤户⻓按（按下 500ms 不松⼿）后会被调⽤
         * 这个 500ms 在 GestureDetectorCompat 中变成了 600ms
         */
        override fun onLongPress(e: MotionEvent?) {
            return
        }

        /**
         * ⽤户双击时被调⽤
         * 注意：第⼆次触摸到屏幕时就调⽤，⽽不是抬起时
         */
        override fun onDoubleTap(e: MotionEvent): Boolean {
            big = !big
            if (big) {
                offsetX = (e.x - width / 2) * (1 - bigScale / smallScale)
                offsetY = (e.y - height / 2) * (1 - bigScale / smallScale)
                fixOffset()
                getAnimator()?.start()
            } else {
                getAnimator()?.reverse()
            }
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

    inner class MyRunner : Runnable {
        override fun run() {
            refresh()
        }
    }

    inner class MyScaleDestureListener : ScaleGestureDetector.OnScaleGestureListener {
        private var initialCurrentScale = 0f
        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            initialCurrentScale = currentScale
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector) {
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            currentScale = initialCurrentScale * detector.scaleFactor
            invalidate()
            return false
        }
    }
}