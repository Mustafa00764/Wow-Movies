package com.example.wowmovie_.data.remote

import com.example.wowmovie_.model.MovieID
import com.example.wowmovie_.model.NowPlaingRU
import com.example.wowmovie_.model.PopularRU
import com.example.wowmovie_.model.SaveMovie
import com.example.wowmovie_.model.Search
import com.example.wowmovie_.model.TopRatedRU

import com.example.wowmovie_.model.UpcomingRU
import com.example.wowmovie_.model.VideosUS
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TMDBService {
    @GET("movie/{id}?language=ru-RU")
    fun getAllProducts(@Path("id") id: Int): Call<MovieID>

    @GET("movie/{id}?language=ru-RU")
    fun getAllProductsSave(@Path("id") id: Int): Call<SaveMovie>

    @GET("movie/{movie_id}/videos?language=en-US")
    fun getVideos(@Path("movie_id") id: Int): Call<VideosUS>

    @GET("search/movie?language=ru-RU")
    fun getSearch(@Query("query") query: String): Call<Search>


    @GET("movie/now_playing?language=ru-RU&page=1")
    fun getNowPlayingMovies(
        @Query("page") page: Int
    ): Call<NowPlaingRU>

    @GET("movie/popular?language=ru-RU&page=1")
    fun getMoviePopular(
        @Query("page") page: Int
    ): Call<PopularRU>

    @GET("movie/top_rated?language=ru-RU&page=1")
    fun getMovieRTopResult(
        @Query("page") page: Int
    ): Call<TopRatedRU>

    @GET("movie/upcoming?language=ru-RU&page=1")
    fun getMovieUpcoming(
        @Query("page") page: Int
    ): Call<UpcomingRU>
}