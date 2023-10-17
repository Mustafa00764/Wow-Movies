package com.example.wowmovie_.data.local

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.wowmovie_.model.SaveMovie

@Database(entities = [SaveMovie::class], version = 1)
abstract class AppDarabase:RoomDatabase() {


    abstract fun getMovieDao():MovieDao



    companion object{
        private var DB_INSTANCE:AppDarabase?=null


        fun myDB(context: Context):AppDarabase{
            if (DB_INSTANCE==null){
                DB_INSTANCE=Room.databaseBuilder(context.applicationContext,AppDarabase::class.java,"movie_db")
                    .allowMainThreadQueries().build()
            }
            return DB_INSTANCE!!
        }
    }


}