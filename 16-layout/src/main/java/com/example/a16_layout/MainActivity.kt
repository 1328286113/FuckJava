package com.example.a16_layout

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var outMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(outMetrics)
        WINDOW_WIDTH = outMetrics.widthPixels
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_square -> {
                startActivity(Intent(this, SquareActivity::class.java))
            }
            R.id.btn_circle -> {
                startActivity(Intent(this, CircleActivity::class.java))
            }
            R.id.btn_taglayout -> {
                startActivity(Intent(this, TaglayoutActivity::class.java))
            }
            R.id.btn_uicollection -> {
                startActivity(intent.setClass(this,UiCollectionActivity::class.java))
            }
        }
    }
}
