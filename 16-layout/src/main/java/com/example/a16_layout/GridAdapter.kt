package com.example.a16_layout

import android.content.Context
import android.database.DataSetObserver
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class GridAdapter() : BaseAdapter() {
    var context:Context ?= null

    constructor(context: Context?) : this() {
        this.context = context
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.fragment_home_relist_part,null)
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return 6
    }

}