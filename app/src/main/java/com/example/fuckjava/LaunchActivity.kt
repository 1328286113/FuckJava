package com.example.fuckjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_launch.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        GlobalScope.launch {  }
    }

}
