package com.example.drcpractical.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.drcpractical.database.PlaceTable
import kotlinx.android.synthetic.main.item_mapdata.view.*

class PlaceHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    fun bind(data: PlaceTable, listener: OnItemClickListener, position: Int) = with(itemView) {
        itemView.placename_txt.text = data.name
        itemView.vicinity_txt.text= data.vicinity
        setOnClickListener { listener.onClick(data, position) }
    }
}