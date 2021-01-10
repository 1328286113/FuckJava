package com.example.a18_scalable_image_view

import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.baseContext
        /**
         * ViewTreeObserver，View事件的观察者，可以用来监听视图树，会监听视图树发生全局变化时发出的通知。
         * 这里指的全局 事件包括而且不局限在以下几个：整个视图树的布局变化，开始绘制视图，触摸模式改变等等。
         * 添加addOnPreDrawListener确保了Layout过程执行，此时能获取宽高，在获取之后应及时调用remove方法将监听移除
         */
        siv.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                val height: Int = siv.measuredHeight
                val width: Int = siv.measuredWidth
                siv.viewTreeObserver.removeOnPreDrawListener(this)
                return true
            }
        })
    }
}
