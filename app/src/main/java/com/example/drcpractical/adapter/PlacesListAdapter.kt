package com.example.drcpractical.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.drcpractical.R
import com.example.drcpractical.database.PlaceTable

class PlacesListAdapter(data: List<PlaceTable>, listener: OnItemClickListener) :
    RecyclerView.Adapter<PlaceHolder>() {

    private val datas = data
    private val listeners = listener

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
        holder.bind(datas[position], listeners, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_mapdata, parent, false)
        return PlaceHolder(view)
    }
}