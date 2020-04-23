package com.example.a16_layout

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.ListAdapter
import android.widget.TextView
import com.example.a16_layout.view.HomeItemView
import kotlinx.android.synthetic.main.activity_uicollection.*

@SuppressLint("ResourceAsColor")
class UiCollectionActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uicollection)
//        for (e in 0 until 11) {
//            var child = HomeItemView(this)
//            var child = TextView(this)
//            child.setBackgroundColor(R.color.colorPrimary)
//            child.setText("警务区")
//            uicollection.addView(child)
//        }
        uicollection.adapter = GridAdapter(this)
    }



    override fun onResume() {
        super.onResume()
        uicollection.post(Runnable {
            Thread.sleep(2000)
            Log.e("UiCollection","width:"+uicollection.getChildAt(0).width+"\nheight:"+uicollection.getChildAt(0).height)
        })
    }
}