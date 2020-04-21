package com.example.fuckjava.customview.d_bitmap_drawable

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.fuckjava.R
import com.example.fuckjava.util.dp2px

class MaterialEditText : AppCompatEditText {
    var FIRST: Boolean = true
    private val topTextDrawable = TopTextDrawable()
    private val TEXT_MARGIN = dp2px(8f)
    val VERTICAL_OFFSET = dp2px(38f)
    val HORIZONTAL_OFFSET = dp2px(5f)
    val EXTRA_OFFSET = dp2px(16f)
    var backgroundPadding: Rect = Rect()

    var isshow: Boolean = false
    var useFloatingLabel: Boolean = false
        set(value) {
            field = value
            refreshPadding()
        }
    var floatingLabelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }
    var objectAnimator: ObjectAnimator =
        ObjectAnimator.ofFloat(this@MaterialEditText, "floatingLabelFraction", 1f)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        /*attrs拿到了这个view在xml里面的所有属性;
        R.styleable.MaterialEditText本质是个数组，所以可以写成new Int[]{}，它过滤了attrs里面的属性;
        R.styleable.MaterialEditText_useFloatingLabel本质是过滤之后的数组的下标*/
        var typeArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        useFloatingLabel = typeArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel,true)
        typeArray.recycle()
        objectAnimator.duration = 300
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                FIRST = false
                if (!TextUtils.isEmpty(s) && !isshow) {
                    isshow = true
                    objectAnimator.start()
                } else if (TextUtils.isEmpty(s) && isshow) {
                    isshow = false
                    objectAnimator.reverse()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        refreshPadding()
    }

    private fun refreshPadding() {
        background.getPadding(backgroundPadding)
        setPadding(
            backgroundPadding.left,
            if (useFloatingLabel) (backgroundPadding.top + TEXT_MARGIN + topTextDrawable.bounds.bottom).toInt() else backgroundPadding.top,
            backgroundPadding.right,
            backgroundPadding.bottom
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (FIRST) {
            return
        }
        if (useFloatingLabel) {
            topTextDrawable.text = hint as String
            topTextDrawable.alpha = (floatingLabelFraction * 0xff).toInt()
            var extraOffset = EXTRA_OFFSET * (1 - floatingLabelFraction)
            topTextDrawable.setBounds(
                0,
                (extraOffset).toInt(),
                width,
                (topTextDrawable.paint.textSize + extraOffset).toInt()
            )
            topTextDrawable.draw(canvas)
        }
    }
}