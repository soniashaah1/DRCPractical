package com.example.drcpractical.modelplace;
import com.google.gson.annotations.SerializedName

data class Plus_code (
	@SerializedName("compound_code") val compound_code : String,
	@SerializedName("global_code") val global_code : String
)