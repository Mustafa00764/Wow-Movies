package com.example.wowmovie_.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wowmovie_.model.Movie
import com.example.wowmovie_.model.SaveMovie


@Dao
interface MovieDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(saveMovie: SaveMovie)


    @Query("SELECT * FROM save")
    fun getAllMovie():List<SaveMovie>


    @Query("SELECT * FROM save WHERE id =:id")
    fun getMovieById(id:Int):SaveMovie

    @Query("DELETE FROM save WHERE :id=id")
    fun deleteMovieById(id: Int)
}