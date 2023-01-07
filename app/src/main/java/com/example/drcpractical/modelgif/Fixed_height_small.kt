package com.example.drcpractical.modelgif;
import com.google.gson.annotations.SerializedName

data class Fixed_height_small (

	@SerializedName("height") val height : Int,
	@SerializedName("width") val width : Int,
	@SerializedName("size") val size : Int,
	@SerializedName("url") val url : String,
	@SerializedName("mp4_size") val mp4_size : Int,
	@SerializedName("mp4") val mp4 : String,
	@SerializedName("webp_size") val webp_size : Int,
	@SerializedName("webp") val webp : String
)