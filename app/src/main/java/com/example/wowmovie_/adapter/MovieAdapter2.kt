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
import com.example.wowmovie_.model.NowItemRU

import com.example.wowmovie_.model.ResultsItemRU


class MovieAdapter2 : RecyclerView.Adapter<MovieAdapter2.MovieViewHolder>() {

    //    private val collection: ArrayList<ResultsItem> = ArrayList()
    var itemClick: ((Int) -> Unit)? = null
    private val moviePopular = ArrayList<ResultsItemRU>()
    fun submitList(moviePopular: ArrayList<ResultsItemRU>){
        this.moviePopular.clear()
        this.moviePopular.addAll(moviePopular)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_mini, parent, false)
        return MovieViewHolder(view)
    }


    override fun getItemCount() = moviePopular.size


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.apply {
            val moviespoplar = moviePopular[position]
            Glide.with(ivMovieImage).load("https://image.tmdb.org/t/p/w500${moviespoplar.posterPath}").into(ivMovieImage)
            tvMovieName.text = moviespoplar.title
            tvBall.text = moviespoplar.voteAverage.toString()
            tvYear.text = moviespoplar.releaseDate




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