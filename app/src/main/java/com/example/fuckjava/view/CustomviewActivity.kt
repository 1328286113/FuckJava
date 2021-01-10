package com.example.fuckjava.view

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fuckjava.R
import com.example.fuckjava.customview.d_bitmap_drawable.MaterialEditText
import com.example.fuckjava.databinding.ActivityCustomviewBinding

class CustomviewActivity : AppCompatActivity() {
    lateinit var    viewBinding :ActivityCustomviewBinding

    var personages: Array<String> = arrayOf("泷泽萝拉", "樱井步", "小川阿佐美", "西条琉璃", "冲田杏梨", "雏名由奈", "明日花绮罗")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCustomviewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
//        view.useFloatingLabel = true


//        var topAnimator:ObjectAnimator = ObjectAnimator.ofFloat(view,"topFlip",45f)
//        topAnimator.duration = 2000
//        topAnimator.startDelay = 800
//
//        var bottomAnimator:ObjectAnimator = ObjectAnimator.ofFloat(view,"bottomFlip",-45f)
//        bottomAnimator.duration = 2000
//        bottomAnimator.startDelay = 800
//
//        var flipRotationAnimator:ObjectAnimator = ObjectAnimator.ofFloat(view,"flipRotation",360f)
//        flipRotationAnimator.duration = 2000
//        flipRotationAnimator.startDelay = 800
//
//        var animatorSet = AnimatorSet()
//        animatorSet.playSequentially(topAnimator,flipRotationAnimator,bottomAnimator)
//        animatorSet.startDelay = 4000
//        animatorSet.start()

//        var topproperty: PropertyValuesHolder = PropertyValuesHolder.ofFloat("topFlip", 45f)
//        var bottomproperty: PropertyValuesHolder = PropertyValuesHolder.ofFloat("bottomFlip", -45f)
//        var flipproperty: PropertyValuesHolder = PropertyValuesHolder.ofFloat("flipRotation", 360f)
//        var animator: ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(
//            view, topproperty,
//            bottomproperty,
//            flipproperty
//        )
//        animator.duration = 2000
//        animator.startDelay = 2000
//        animator.start()

        var objectAnimator = ObjectAnimator.ofObject(viewBinding.tvArray,"start", textTypeEvaluate(),"明日花绮罗")
        objectAnimator.duration = 2000
        objectAnimator.startDelay = 1000
        objectAnimator.start()

    }
    inner class textTypeEvaluate : TypeEvaluator<String> {
        override fun evaluate(fraction: Float, startValue: String?, endValue: String?): String {
            return personages[(personages.indexOf(startValue) + (personages.indexOf(endValue) - personages.indexOf(startValue)) * fraction).toInt()]
        }
    }
}