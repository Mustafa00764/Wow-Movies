package com.example.wowmovie_.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wowmovie_.R
import com.example.wowmovie_.model.Movie3
import com.example.wowmovie_.model.NowItemRU

class LudshiyFilmOpisanieAdapter(val context: Context,):RecyclerView.Adapter<LudshiyFilmOpisanieAdapter.LudshiyFilmViewHolder>() {
    val opisanie = ArrayList<NowItemRU>()
    fun submitLists3(opisanie: ArrayList<NowItemRU>) {
        this.opisanie.clear()
        this.opisanie.addAll(opisanie)
        notifyDataSetChanged()
    }
    var itemClick: ((Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LudshiyFilmViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_movies, parent, false)
        return LudshiyFilmViewHolder(view)
    }

    override fun getItemCount(): Int {
        return opisanie.size
    }

    override fun onBindViewHolder(holder: LudshiyFilmViewHolder, position: Int) {
        val opisanie = opisanie[position]
        holder.apply {
            Glide.with(iv_movie_image).load("https://image.tmdb.org/t/p/w500${opisanie.posterPath}").into(iv_movie_image)
            tv_Movie_NAME.text = opisanie.title
            tv_opisanie.text = opisanie.overview
            tv_ball.text = opisanie.voteAverage.toString()
            itemClick?.invoke(position)
//            rvMovieChild.adapter = MovieAdapter(context, movieModel = ArrayList())
        }

    }


    class LudshiyFilmViewHolder(view: View):RecyclerView.ViewHolder(view){
        val iv_movie_image = view.findViewById<ImageView>(R.id.iv_movie_image)
        val tv_Movie_NAME = view.findViewById<TextView>(R.id.tv_Movie_NAME)
        val tv_ball = view.findViewById<TextView>(R.id.tv_ball)
        val tv_opisanie = view.findViewById<TextView>(R.id.tv_opisanie)
        val tvGenreMovie = view.findViewById<TextView>(R.id.tvGenreMovie)
        val tvGenreMovie2 = view.findViewById<TextView>(R.id.tvGenreMovie2)
        val rvMovieChild = view.findViewById<RecyclerView>(R.id.rvMovieChild)
        val rvMovieChild2 = view.findViewById<RecyclerView>(R.id.rvMovieChild2)

    }


}