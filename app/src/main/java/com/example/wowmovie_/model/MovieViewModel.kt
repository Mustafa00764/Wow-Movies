package com.example.wowmovie_.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieViewModel : ViewModel() {
    private val movieId = MutableLiveData<Int>()
    private val movieTitle = MutableLiveData<String>()

    fun setMovieId(id: Int) {
        movieId.value = id
    }

    fun getMovieId(): LiveData<Int> {
        return movieId
    }

    fun setMovieTitle(title: String) {
        movieTitle.value = title
    }

    fun getMovieTitle(): LiveData<String> {
        return movieTitle
    }
}