package com.example.drcpractical.modelplace;
import com.google.gson.annotations.SerializedName

data class Northeast (
	@SerializedName("lat") val lat : Double,
	@SerializedName("lng") val lng : Double
)