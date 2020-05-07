package com.example.a18_scalable_image_view.view

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.OverScroller
import androidx.core.animation.addListener
import androidx.core.view.GestureDetectorCompat
import com.example.a18_scalable_image_view.dpToPixel
import com.example.a18_scalable_image_view.getAvatar
import kotlin.math.max
import kotlin.math.min

class ScalableImageView1(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint: Paint = Paint()
    var bitmap: Bitmap = getAvatar(resources, dpToPixel(500f))

    var smallScale = 0f
    var bigScale = 0f
    var OVER_SCALE_FACTOR = 1.5F
    var big: Boolean = false
    var offsetX = 0f
    var offsetY = 0f
    var maxOffsetX = 0f
    var maxOffsetY = 0f
    var orginOffsetX = 0f
    var orginOffsetY = 0f
    var overOffsetX = dpToPixel(50f)
    var overOffsetY = dpToPixel(50f)
    var outPointF = PointF()
    var isOut = false
    var ACTION_UP = false
    var currentScale = 1f
        set(value) {
            field = value
            invalidate()
        }
    private var scaleAnimator: ObjectAnimator? = null
    private var transAnimator: ObjectAnimator? = null
    var transFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    private var gestureListener = MyGestureListener()
    private var scroller = OverScroller(context)
    var gestureDetector = GestureDetectorCompat(context, gestureListener)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (bitmap.width / bitmap.height > width / height) {
            smallScale = width.toFloat() / bitmap.width
            bigScale = height.toFloat() / bitmap.height * OVER_SCALE_FACTOR
        } else {
            smallScale = height.toFloat() / bitmap.height
            bigScale = width.toFloat() / bitmap.width * OVER_SCALE_FACTOR
        }
        currentScale = smallScale
        orginOffsetX = (width - bitmap.width) / 2f
        orginOffsetY = (height - bitmap.height) / 2f
        maxOffsetX = (bitmap.width * bigScale - width) / 2f
        maxOffsetY = (bitmap.height * bigScale - height) / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var scaleFraction = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.save()
        if (big && isOut && ACTION_UP) {
            canvas.translate(
                offsetX + (outPointF.x - offsetX) * transFraction,
                offsetY + (outPointF.y - offsetY) * transFraction
            )
        } else {
            canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
        }
        canvas.scale(currentScale, currentScale, (width / 2).toFloat(), (height / 2).toFloat())
        canvas.drawBitmap(bitmap, orginOffsetX, orginOffsetY, paint)
        canvas.restore()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.actionMasked) {
            MotionEvent.ACTION_UP -> {
                Log.i("MontionEvent:", "in")
                if (big && isOut) {
                    outPointF.x = offsetX
                    outPointF.y = offsetY
                    fixOffset()
                    getTransAnimator()?.setDuration(300)?.start()
                    ACTION_UP = true
                }
            }
        }

        return if (isOut && event.actionMasked == MotionEvent.ACTION_UP) {
            true
        } else {
            gestureDetector.onTouchEvent(event)
        }
    }

    private fun getScaleAnimator(): ObjectAnimator? {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", 0f)
        }
        scaleAnimator?.setFloatValues(smallScale, bigScale)
        return scaleAnimator
    }

    private fun getTransAnimator(): ObjectAnimator? {
        if (transAnimator == null) {
            transAnimator = ObjectAnimator.ofFloat(this, "transFraction", 1f, 0f)

        }
        transAnimator?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                isOut = false
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
        return transAnimator
    }

    private fun fixOffset() {
        offsetX = max(-maxOffsetX, min(maxOffsetX, offsetX))
        offsetY = max(-maxOffsetY, min(maxOffsetY, offsetY))
    }

    private var myAnimatorRunnable = Runnable {
        refresh()
    }

    private fun refresh() {
        if (scroller.computeScrollOffset()) {
            offsetX = scroller.currX.toFloat()
            offsetY = scroller.currY.toFloat()
            invalidate()
            postOnAnimation(myAnimatorRunnable)
        }
    }

    private inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return super.onSingleTapUp(e)
        }

        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (big && !isOut) {
                scroller.fling(
                    offsetX.toInt(),
                    offsetY.toInt(),
                    velocityX.toInt(),
                    velocityY.toInt(),
                    (-maxOffsetX).toInt(), maxOffsetX.toInt(),
                    (-maxOffsetY).toInt(), maxOffsetY.toInt()
                )
                postOnAnimation(myAnimatorRunnable)
            }
            return false
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            big = !big
            if (big) {
                offsetX = (e.x - width / 2) * (1 - bigScale / smallScale)
                offsetY = (e.y - height / 2) * (1 - bigScale / smallScale)
                fixOffset()
                getScaleAnimator()?.start()
            } else {
                getScaleAnimator()?.reverse()
            }
            return false
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if (big) {
                if (offsetX > maxOffsetX || offsetX < -maxOffsetX || offsetY > maxOffsetY || offsetY < -maxOffsetY) {
                    offsetX -= distanceX / 3
                    offsetY -= distanceY / 3
                    isOut = true
                } else {
                    offsetX -= distanceX
                    offsetY -= distanceY
                }
                invalidate()
            }
            return false
        }

        override fun onContextClick(e: MotionEvent?): Boolean {
            return super.onContextClick(e)
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            return false
        }

        override fun onShowPress(e: MotionEvent?) {
            super.onShowPress(e)
        }

        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
            return super.onDoubleTapEvent(e)
        }

        override fun onLongPress(e: MotionEvent?) {
            super.onLongPress(e)
        }
    }
}