package com.example.a15_motionlayout

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_sim.setOnClickListener(this)
        btn_sim1.setOnClickListener(this)
        btn_film.setOnClickListener(this)
        btn_you.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_sim -> startActivity<SimpleActivity>()
            R.id.btn_sim1 -> startActivity<Simple1Activity>()
            R.id.btn_film -> startActivity<FilmActivity>()
            R.id.btn_you -> startActivity<YoutubeActivity>()
        }
    }

    private inline fun <reified T : Activity> Context.startActivity() = startActivity(Intent(this, T::class.java))
}

