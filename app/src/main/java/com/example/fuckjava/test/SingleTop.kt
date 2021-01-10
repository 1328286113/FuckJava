package com.example.fuckjava.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.fuckjava.R

class SingleTop : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_top)
        Log.d("aaaaSingletop","onCreate")
    }

    override fun onClick(v: View?) {
        startActivity(Intent(this, SingleTask::class.java))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("aaaaSingletop","onNewIntent")
    }
}