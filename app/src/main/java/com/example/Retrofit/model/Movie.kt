package com.example.Retrofit.model

import com.google.gson.annotations.SerializedName


data class Movie(
    @SerializedName("Title") var title: String,
    @SerializedName("Poster") var poster: String,
    @SerializedName("Type") var type: String,
    @SerializedName("Year") var year: String
) {

}