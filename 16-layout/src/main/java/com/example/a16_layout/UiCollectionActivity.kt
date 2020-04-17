package com.example.a16_layout

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.a16_layout.view.HomeItemView
import kotlinx.android.synthetic.main.activity_uicollection.*

class UiCollectionActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uicollection)
        for (e in 0 until 9) {
            var child = HomeItemView(this)
//            var child = Button(this)
            uicollection.addView(child)
        }
    }

    override fun onResume() {
        super.onResume()
        uicollection.post(Runnable {
            Thread.sleep(2000)
            Log.e("UiCollection","width:"+uicollection.getChildAt(0).width+"\nheight:"+uicollection.getChildAt(0).height)
        })
    }
}