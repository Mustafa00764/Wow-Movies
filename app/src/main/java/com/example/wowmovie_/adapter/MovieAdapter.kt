package com.example.wowmovie_.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.graphics.blue
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wowmovie_.R
import com.example.wowmovie_.model.MovieModel
import com.example.wowmovie_.model.NowItemRU



class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    //    private val collection: ArrayList<ResultsItem> = ArrayList()
    var itemClick: ((Int) -> Unit)? = null

    private val movieModel= ArrayList<NowItemRU>()
    fun submitList(movieModel: ArrayList<NowItemRU>){
        this.movieModel.clear()
        this.movieModel.addAll(movieModel)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_mini, parent, false)
        return MovieViewHolder(view)
    }


    override fun getItemCount():Int{
        return movieModel.size

    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.apply {
            val movie = movieModel[position]

            Glide.with(ivMovieImage).load("https://image.tmdb.org/t/p/w500${movie.posterPath}").into(ivMovieImage)
            tvMovieName.text = movie.title
            tvBall.text = movie.voteAverage.toString()
            tvYear.text = movie.releaseDate



            ll.setOnClickListener {
                itemClick?.invoke(position)

           }



        }



    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMovieImage = itemView.findViewById<ImageView>(R.id.iv_movie_image)
        val ll_otcenka = itemView.findViewById<LinearLayout>(R.id.ll_otcenka)
        val tvMovieName = itemView.findViewById<TextView>(R.id.tv_movie_name)
        val tvBall = itemView.findViewById<TextView>(R.id.tv_ball)
        val ll =itemView.findViewById<LinearLayout>(R.id.ll_item_movie_mini)
        val tvYear = itemView.findViewById<TextView>(R.id.tv_movie_janr)



    }

}