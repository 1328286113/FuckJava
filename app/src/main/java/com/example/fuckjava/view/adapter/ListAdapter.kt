package com.example.fuckjava.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fuckjava.R
import com.example.fuckjava.databinding.ItemJokerBinding
import com.example.fuckjava.josnbean.Data

class ListAdapter(private var datas: List<Data>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_joker,parent,false))
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val itemjokerBinding: ItemJokerBinding? = DataBindingUtil.bind(v)

        fun bind(itembean: Data){
            itemjokerBinding?.bean = itembean
        }
    }
    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.itemView.tag = position
        holder.bind(datas[position])
    }
}