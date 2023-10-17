package com.example.wowmovie_.model

class MainModel(
    val title:String,
    val movieNowPlaingRU:ArrayList<NowItemRU> ?= null,
    val moviePopularRU: ArrayList<ResultsItemRU> ?= null,
    val movies: ArrayList<VideosItem> ?= null,
    val movie:ArrayList<VideoItem>?= null,
    val movieTopRatedRU:ArrayList<TopRatedRuItem>? = null,
    val movieUpcomingRU:ArrayList<UpcomingRUItem>? = null


)
