package com.example.drcpractical.modelgif;
import com.google.gson.annotations.SerializedName
data class Preview_webp (

	@SerializedName("height") val height : Int,
	@SerializedName("width") val width : Int,
	@SerializedName("size") val size : Int,
	@SerializedName("url") val url : String
)