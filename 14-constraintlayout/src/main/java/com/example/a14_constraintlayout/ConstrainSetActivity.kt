package com.example.a14_constraintlayout

import android.os.Build
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class ConstrainSetActivity : AppCompatActivity(), View.OnClickListener {
    //    lateinit var flow:Flow
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constrainset_start)
//        flow = findViewById(R.id.flow)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onClick(v: View?) {
        val constraintLayout = v as ConstraintLayout
        TransitionManager.beginDelayedTransition(constraintLayout)
        val constrainSet = ConstraintSet().apply {
            isForceId = false
            clone(this@ConstrainSetActivity, R.layout.activity_constrainset_end)
        }
        constrainSet.applyTo(v)
    }

}