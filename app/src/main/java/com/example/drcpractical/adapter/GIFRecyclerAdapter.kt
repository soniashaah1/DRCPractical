package com.example.drcpractical.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drcpractical.R
import com.example.drcpractical.modelgif.Downsized_large

class GIFRecyclerAdapter(
    private val context: Context,
    data: List<Downsized_large>,
) :
    RecyclerView.Adapter<GIFRecyclerAdapter.ViewHolder>() {
    private val datas = data
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_recycler, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        Glide.with(context)
            .asGif()
            .load(datas[i].url)
            .into(viewHolder.imgAndroid)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgAndroid: ImageView

        init {
            imgAndroid =
                view.findViewById<View>(R.id.iv_glide) as ImageView
        }
    }
}