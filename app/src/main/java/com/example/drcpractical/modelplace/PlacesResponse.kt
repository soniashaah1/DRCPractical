package com.example.drcpractical.modelplace;

import com.google.gson.annotations.SerializedName

class PlacesResponse(
    @SerializedName("html_attributions")
    val html_attributions: List<String>,
    @SerializedName("next_page_token")
    val next_page_token: String,
    @SerializedName("results")
    val results: List<Results>,
    @SerializedName("status")
    val status: String,

    )