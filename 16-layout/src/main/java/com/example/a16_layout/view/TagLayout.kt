package com.example.a16_layout.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import kotlin.math.max

class TagLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    var childbounds = ArrayList<Rect>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var widthUsed = 0
        var heightUsed = 0
        var lineWidthUsed = 0
        var lineHeightUsed = 0
        var widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        var widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        for (i in 0 until childCount - 1) {
            var childview = getChildAt(i)
            measureChildWithMargins(childview, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            var childbound: Rect
            if (childbounds.size <= i) {
                childbound = Rect()
                childbounds.add(childbound)
            } else {
                childbound = childbounds[i]
            }
            if (widthSpecMode != MeasureSpec.UNSPECIFIED &&
                childview.measuredWidth + lineWidthUsed > widthSpecSize) {
                lineWidthUsed = 0
                heightUsed += lineHeightUsed
                lineHeightUsed = 0
//                measureChildWithMargins(childview, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            }
            childbound.set(
                lineWidthUsed,
                heightUsed,
                lineWidthUsed + childview.measuredWidth,
                heightUsed + childview.measuredHeight
            )
            lineWidthUsed += childview.measuredWidth
            widthUsed = max(widthUsed,lineWidthUsed)
            lineHeightUsed = max(lineHeightUsed, childview.measuredHeight)
//            childview.measure(widthMeasureSpec,heightMeasureSpec)
        }
        var width = widthUsed
        var height = heightUsed+lineHeightUsed
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount - 1) {
            var childbound = childbounds[i]
            getChildAt(i).layout(
                childbound.left,
                childbound.top,
                childbound.right,
                childbound.bottom
            )
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun addView(child: View?, index: Int) {
        requireNotNull(child) { "Cannot add a null child view to a ViewGroup" }
        var params = layoutParams
        if (params == null) {
            params = generateDefaultLayoutParams()
            requireNotNull(params) { "generateDefaultLayoutParams() cannot return null" }
        }
        addView(child, index, params)
    }
}