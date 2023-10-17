package com.example.wowmovie_.data.local

import android.app.Application
import com.example.wowmovie_.model.Movie
import com.example.wowmovie_.model.SaveMovie

class MovieRepository( val app: Application) {

    private val movieDao = AppDarabase.myDB(app).getMovieDao()


    fun saveMovies(saveMovie: SaveMovie) = movieDao.saveMovie(saveMovie)

    fun getMovieById(id:Int):SaveMovie = movieDao.getMovieById(id)

    fun getAllMovies():List<SaveMovie>{
        return movieDao.getAllMovie()
    }
    fun deleteById(id:Int){

        movieDao.deleteMovieById(id)

    }
}