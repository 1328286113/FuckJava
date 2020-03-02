package com.example.a16_layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a16_layout.databinding.ActivitySquareBinding

class SquareActivity:AppCompatActivity() {
    lateinit var viewBinding: ActivitySquareBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySquareBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.textHh.text = "hh"
    }
}