package com.example.a16_layout

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.a16_layout.view.ColoredTextView
import com.example.a16_layout.view.HomeItemView
import kotlinx.android.synthetic.main.activity_taglayout.*

class TaglayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taglayout)

        var textView = TextView(this);
        textView.setText("gege")
        var btn = Button(this)
//        taglayout.addView(textView)
//        taglayout.addView(btn)
        var homeItemView = HomeItemView(this)
        taglayout.addView(homeItemView)
//        taglayout.requestLayout()
    }
}