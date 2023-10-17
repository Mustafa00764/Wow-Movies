package com.example.wowmovie_.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wowmovie_.R
import com.example.wowmovie_.model.ResultsItemRU

import com.example.wowmovie_.model.TopRatedRuItem



class MovieAdapter3  : RecyclerView.Adapter<MovieAdapter3.MovieViewHolder>() {

    //    private val collection: ArrayList<ResultsItem> = ArrayList()
    var itemClick: ((Int) -> Unit)? = null
    private val movieTopResult=ArrayList<TopRatedRuItem>()

    fun submitList(movieTopResult: ArrayList<TopRatedRuItem>){
        this.movieTopResult.clear()
        this.movieTopResult.addAll(movieTopResult)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_mini, parent, false)
        return MovieViewHolder(view)
    }


    override fun getItemCount() = movieTopResult.size


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.apply {
            val movieTopResult = movieTopResult[position]
            Glide.with(ivMovieImage).load("https://image.tmdb.org/t/p/w500${movieTopResult.posterPath}").into(ivMovieImage)
            tvMovieName.text = movieTopResult.title
            tvBall.text = movieTopResult.voteAverage.toString()
            tvYear.text = movieTopResult.releaseDate




            llItemMovieMini.setOnClickListener {
                itemClick?.invoke(position)

            }

        }


    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMovieImage = itemView.findViewById<ImageView>(R.id.iv_movie_image)
        val llItemMovieMini = itemView.findViewById<LinearLayout>(R.id.ll_item_movie_mini)
        val tvMovieName = itemView.findViewById<TextView>(R.id.tv_movie_name)
        val tvBall = itemView.findViewById<TextView>(R.id.tv_ball)
        val tvYear = itemView.findViewById<TextView>(R.id.tv_movie_janr)


    }

}