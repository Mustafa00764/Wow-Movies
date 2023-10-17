package com.example.wowmovie_.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wowmovie_.R
import com.example.wowmovie_.model.MovieModel

class MovieMiniAdapter(private val list: ArrayList<MovieModel>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return MovieMiniViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_mini,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
    class MovieMiniViewHolder(view: View):RecyclerView.ViewHolder(view){



    }
//        Glide.with(holder.imagemovie).load(movie.image).into(holder.imagemovie)



//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieMiniViewHolder {
//        return MovieMiniViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ludshie_films,parent,false))
//
//    }
//
//    override fun getItemCount() = list.size
//
//
//
//    override fun onBindViewHolder(holder: MovieMiniViewHolder, position: Int) {
//        var movie = list[position]
//        holder.apply {
//            text.text=movie.text
//            janr.text=movie.janr
//            ball.text=movie.ball
//
//        }
//
//
//    }




}