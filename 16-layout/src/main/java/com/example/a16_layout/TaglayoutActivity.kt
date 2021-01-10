package com.example.a16_layout

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TaglayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taglayout)

        var textView = TextView(this);
        textView.setText("gege")
        var btn = Button(this)
//        taglayout.addView(textView)
//        taglayout.addView(btn)
//        var homeItemView = HomeItemView(this)
//        taglayout.addView(homeItemView)
//        taglayout.requestLayout()
    }
}