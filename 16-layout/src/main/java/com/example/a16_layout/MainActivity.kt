package com.example.a16_layout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        }
    }
}
