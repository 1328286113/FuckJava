package com.example.fuckjava.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.fuckjava.R

class SingleTask :AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_task)
        Log.d("aaaaSingletask","onCreate")
    }

    override fun onClick(v: View?) {
        startActivity(Intent(this, SingleTop::class.java))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("aaaaSingletask","onNewIntent")
    }
}