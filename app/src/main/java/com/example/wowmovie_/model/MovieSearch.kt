package com.example.wowmovie_.model

import com.google.gson.annotations.SerializedName

data class MovieSearch (
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("overview") val overview: String,
        @SerializedName("poster_path") val posterPath: String?

)