package com.example.fuckjava.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fuckjava.R
import com.example.fuckjava.databinding.ActivityListBinding
import com.example.fuckjava.model.Data
import com.example.fuckjava.model.JsonViewModel
import com.example.fuckjava.view.adapter.ListAdapter
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    private lateinit var listadapter: ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityListBinding>(this, R.layout.activity_list)
        rv.layoutManager = LinearLayoutManager(this)

        val jsonViewModel = ViewModelProvider(this@ListActivity)[JsonViewModel::class.java]
        jsonViewModel.getData().observe(this@ListActivity, Observer<List<Data>> { datas ->
            println("observe")
            listadapter = ListAdapter(datas)
            rv.adapter = listadapter
        })
//        lifecycle.coroutineScope.launch {
//
//        }
    }
}