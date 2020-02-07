package com.example.fuckjava.view.adapter

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


internal class VH private constructor(v: View) : RecyclerView.ViewHolder(v) {
    private val mViews: SparseArray<View?>
    private val mConvertView: View
    fun <T : View?> getView(id: Int): T? {
        var v: View? = mViews[id]
        if (v == null) {
            v = mConvertView.findViewById(id)
            mViews.put(id, v)
        }
        return v as T?
    }

    fun setText(id: Int, value: String?) {
        val view: TextView? = getView(id)
        view!!.text = value
    }

    companion object {
        operator fun get(parent: ViewGroup, layoutId: Int): VH {
            val convertView: View =
                LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
            return VH(convertView)
        }
    }

    init {
        mConvertView = v
        mViews = SparseArray<View?>()
    }
}