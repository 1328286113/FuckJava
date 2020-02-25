package com.example.a14_constraintlayout

import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ValueAnimatorActivity : AppCompatActivity() {
    lateinit var sun: ImageView
    lateinit var earth: ImageView
    lateinit var moon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valueanimator)
        sun = findViewById(R.id.sun)
        earth = findViewById(R.id.earth)
        moon = findViewById(R.id.moon)

        val earthAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 10000L
            repeatCount = INFINITE
            interpolator = LinearInterpolator()
            addUpdateListener {
                val params = earth.layoutParams as ConstraintLayout.LayoutParams
                params.circleAngle = 45 + it.animatedFraction * 360
                moon.requestLayout()
            }
        }

        val moonAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 2000L
            repeatCount = INFINITE
            interpolator = LinearInterpolator()
            addUpdateListener {
                val params = moon.layoutParams as ConstraintLayout.LayoutParams
                params.circleAngle = 270 + it.animatedFraction * 360
                moon.requestLayout()
            }
        }

        sun.setOnClickListener {
            earthAnimator.start()
            moonAnimator.start()
        }
    }
}