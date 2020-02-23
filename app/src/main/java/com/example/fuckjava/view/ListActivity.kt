package com.example.fuckjava.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fuckjava.R
import com.example.fuckjava.databinding.ActivityListBinding
import com.example.fuckjava.josnbean.Data
import com.example.fuckjava.model.NewsViewModel
import com.example.fuckjava.util.ItemClickSupport
import com.example.fuckjava.view.adapter.ListAdapter
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    private lateinit var listadapter: ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityListBinding>(this, R.layout.activity_list)
        rv.layoutManager = LinearLayoutManager(this)

        val jsonViewModel = ViewModelProvider(this@ListActivity)[NewsViewModel::class.java]
        jsonViewModel.getData().observe(this@ListActivity, Observer<List<Data>> { datas ->
            println("observe")
            listadapter = ListAdapter(datas)
//            dataBindAdapter = DataBindAdapter()
            rv.adapter = listadapter
        })
        ItemClickSupport.addTo(rv).addOnItemClickListener(object :ItemClickSupport.OnItemClickListener{
            override fun onItemClicked(recyclerView: RecyclerView, position: Int, v: View) {
                Toast.makeText(this@ListActivity,"itemclick：$position",Toast.LENGTH_SHORT).show()
            }
        }).addOnChildClickListener(R.id.iv,object :ItemClickSupport.OnChildClickListener{
            override fun onChildClicked(recyclerView: RecyclerView, position: Int, v: View) {
                Toast.makeText(this@ListActivity,"itemiv：$position",Toast.LENGTH_SHORT).show()
            }
        })

//        lifecycle.coroutineScope.launch {
//
//        }
    }
}