package com.example.a15_motionlayout

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class YoutubeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)
        findViewById<TextView>(R.id.text_container).text = getString(R.string.large_text)
    }
}