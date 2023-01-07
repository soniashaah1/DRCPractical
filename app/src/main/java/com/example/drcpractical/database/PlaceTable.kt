package com.example.drcpractical.database

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class PlaceTable(
    var id: Int,
    var lat: String,
    var lan: String,
    var name: String,
    var vicinity: String,
) : Parcelable {
    constructor() : this(0, "", "", "", "")

}