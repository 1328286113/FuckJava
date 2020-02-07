package com.example.fuckjava.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fuckjava.R
import com.example.fuckjava.databinding.ItemJokerBinding
import com.example.fuckjava.model.Data

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private var datas:List<Data>


    constructor(datas: List<Data>) : super() {
        this.datas = datas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_joker,parent,false))
    }

    class ViewHolder: RecyclerView.ViewHolder {
        private var itemjokerBinding: ItemJokerBinding
        constructor(v:View) : super(v){
            itemjokerBinding = DataBindingUtil.bind(v)!!
        }
        fun bind(itembean:Data){
            itemjokerBinding.bean = itembean
        }
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}