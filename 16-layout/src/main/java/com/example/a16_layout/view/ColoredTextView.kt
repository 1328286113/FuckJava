package com.example.a16_layout.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.a16_layout.dp2px
import java.util.*

class ColoredTextView(context: Context?, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    @SuppressLint("NewApi")
    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            CORNER_RADIUS.toFloat(),
            CORNER_RADIUS.toFloat(),
            paint
        )
        super.onDraw(canvas)
    }

    companion object {
        private val COLORS = intArrayOf(
            Color.parseColor("#E91E63"),
            Color.parseColor("#673AB7"),
            Color.parseColor("#3F51B5"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#009688"),
            Color.parseColor("#FF9800"),
            Color.parseColor("#FF5722"),
            Color.parseColor("#795548")
        )
        private val TEXT_SIZES = intArrayOf(
            16, 22, 28
        )
        private val random = Random()
        private val CORNER_RADIUS = dp2px(4f).toInt()
        private val X_PADDING = dp2px(16f).toInt()
        private val Y_PADDING = dp2px(8f).toInt()
    }

    init {
        setTextColor(Color.WHITE)
        textSize = TEXT_SIZES[random.nextInt(
            3
        )].toFloat()
        paint.color = COLORS[random.nextInt(
            COLORS.size
        )]
        setPadding(
            X_PADDING,
            Y_PADDING,
            X_PADDING,
            Y_PADDING
        )
    }
}