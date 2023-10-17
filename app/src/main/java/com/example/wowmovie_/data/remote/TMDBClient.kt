package com.example.wowmovie_.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object TMDBClient {

    private const val isTester=false
    private const val SERVER_PRODUCTION="https://api.themoviedb.org/3/"
    private const val SERVER_DEVELOPMENT="https://api.themoviedb.org/3/"
    private val baseURL=if (isTester) SERVER_DEVELOPMENT else SERVER_PRODUCTION
    private val client=getClient()


    private val movies=Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()


    val api:TMDBService = movies.create(TMDBService::class.java)

    private fun getClient(): OkHttpClient {

        return OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.addHeader("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmOGIxMTc0NjFmYjA1MGU1ZTg3NDc1ZGMzOWIxODEyNSIsInN1YiI6IjY0ZmQ3YWZkMmRmZmQ4MDBlM2QyYTY4ZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.bL9NW2XsjRnmqXbq1gGvEdAjHBVwJvxE6HevVahJzK4")
            chain.proceed(builder.build())
        }).build()

}}
