package com.example.fuckjava.view

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fuckjava.R
import com.example.fuckjava.databinding.ActivityListBinding
import com.example.fuckjava.josnbean.Data
import com.example.fuckjava.model.BaseViewModel
import com.example.fuckjava.model.NewsViewModel
import com.example.fuckjava.util.ItemClickSupport
import com.example.fuckjava.view.adapter.ListAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_list.*
import java.io.Serializable
import kotlin.concurrent.thread
import kotlin.collections.ArrayList as ArrayList1

class ListActivity : AppCompatActivity(),Serializable {

    private lateinit var listadapter: ListAdapter
    private lateinit var mm:BaseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityListBinding>(this, R.layout.activity_list)
        rv.layoutManager = LinearLayoutManager(this)
        mm = ViewModelProvider(this)[BaseViewModel::class.java]
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            tv.text = newName
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mm.currentName.observe(this, nameObserver)
        val jsonViewModel = ViewModelProvider(this@ListActivity)[NewsViewModel::class.java]
        jsonViewModel.data.observe(this, Observer { datas ->
            if(rv.adapter == null){
                listadapter = ListAdapter(datas)
                rv.adapter = listadapter
            }else{
                listadapter.notifyItemChanged(0)
            }

        })
        ItemClickSupport.addTo(rv)
            .addOnItemClickListener(object : ItemClickSupport.OnItemClickListener {
                override fun onItemClicked(recyclerView: RecyclerView, position: Int, v: View) {
                    Toast.makeText(this@ListActivity, "itemclick：$position", Toast.LENGTH_SHORT)
                        .show()
                    jsonViewModel.data.value?.get(position)?.title = "数据更改"
                    jsonViewModel.data.value = jsonViewModel.data.value
                    mm.currentName.value = "数据更改"
//                listadapter.notifyItemChanged(position)
                }
            }).addOnChildClickListener(R.id.iv, object : ItemClickSupport.OnChildClickListener {
            override fun onChildClicked(recyclerView: RecyclerView, position: Int, v: View) {
                Toast.makeText(this@ListActivity, "itemiv：$position", Toast.LENGTH_SHORT).show()
            }
        })
        lifecycleScope.launchWhenCreated {
            try {

            } finally {
                if (lifecycle.currentState >= Lifecycle.State.STARTED) {

                }
            }
        }
    }
}