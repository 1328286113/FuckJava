package com.example.a14_constraintlayout

import android.os.Build
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Layer
import androidx.constraintlayout.widget.Group
import androidx.constraintlayout.widget.Placeholder

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var group: Group
    lateinit var layer: Layer
    lateinit var placeholder: Placeholder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constrainthelper)
        group = findViewById(R.id.group)
        layer = findViewById(R.id.layer)
        placeholder = findViewById(R.id.placeholder)

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn1 -> group.visibility = View.GONE
            R.id.btn2 -> {
                layer.rotation = 45f
                layer.translationX = 50f
            }
            R.id.placeholder_iv1, R.id.placeholder_iv2, R.id.placeholder_iv3, R.id.placeholder_iv4,
            R.id.placeholder_iv5, R.id.placeholder_iv6, R.id.placeholder_iv7 -> {
                TransitionManager.beginDelayedTransition(v.parent as ViewGroup)
                placeholder.setContentId(v.id)
            }
        }
    }
}
