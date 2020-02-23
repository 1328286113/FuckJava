package com.example.fuckjava.customview.b_text_and_trans

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.example.fuckjava.util.dp2px
import com.example.fuckjava.util.getAvatar

/**
 * 文字不覆盖图片
 */
class ImageTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val cutWidth: FloatArray = FloatArray(1)
    val IMAGE_VERTICAL = dp2px(100f)
    val IMAGE_WIDTH = dp2px(200f)
    var IMAGE_HEIGHT: Int
    val text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam pretium neque vitae erat interdum pretium. Vivamus vestibulum venenatis est, eu tempus sapien vestibulum sit amet. Integer commodo gravida laoreet. Maecenas malesuada non lacus a vehicula. Sed scelerisque eu felis id rhoncus. In tincidunt ac nunc ac semper. Nam a hendrerit odio. Phasellus in consequat mi. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nulla eget ligula a lacus semper suscipit. Nunc ultricies arcu vitae velit lobortis, aliquet pulvinar sem ultrices. Integer efficitur suscipit metus in egestas. Ut ultricies, odio nec tincidunt efficitur, risus tellus posuere turpis, eu dapibus libero est quis ante. Vivamus quis ante eu eros mollis suscipit."

    var bitmap: Bitmap = getAvatar(resources, IMAGE_WIDTH)
    var paint = Paint()
    var bounds = Rect()
    val fontMetrics = Paint.FontMetrics()

    init {
        paint.textSize = dp2px(18f)
        paint.getFontMetrics(fontMetrics)
        IMAGE_HEIGHT = bitmap.height
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, width - IMAGE_WIDTH, IMAGE_VERTICAL, paint)

        paint.getTextBounds(text, 0, text.length, bounds)
        var offset = (bounds.bottom - bounds.top).toFloat()
        var verticaloffset = paint.fontSpacing
        var count: Int
        var start = 0
        for (i in text.indices) {
            var texttop = verticaloffset + fontMetrics.top
            var textbottom = verticaloffset + fontMetrics.bottom
            var maxwidth = width.toFloat()
            if (texttop > IMAGE_VERTICAL && texttop < IMAGE_VERTICAL + IMAGE_HEIGHT
                || textbottom > IMAGE_VERTICAL && textbottom < IMAGE_VERTICAL + IMAGE_HEIGHT
            ) {
                maxwidth = width - IMAGE_WIDTH
            }
            count = paint.breakText(text, start, text.length, true, maxwidth, cutWidth)
            canvas.drawText(
                text,
                start,
                start + count,
                0f,
                verticaloffset,
                paint
            )
            start += count
            verticaloffset += paint.fontSpacing
        }
    }
}