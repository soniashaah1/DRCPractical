package com.example.drcpractical.adapter

import com.example.drcpractical.database.PlaceTable

interface OnItemClickListener {
    fun onClick(data: PlaceTable, position: Int)
}