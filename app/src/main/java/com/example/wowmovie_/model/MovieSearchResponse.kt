package com.example.wowmovie_.model

import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    @SerializedName("results") val movies: ArrayList<MovieSearch>
)
