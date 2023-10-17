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
import com.example.wowmovie_.model.VideoItem


class MovieAdapterVideo : RecyclerView.Adapter<MovieAdapterVideo.MovieViewHolder>() {

    //    private val collection: ArrayList<ResultsItem> = ArrayList()
    var itemClick: ((Int) -> Unit)? = null

    private val movieModel= ArrayList<VideoItem>()
    fun submitList228(movieModel: ArrayList<VideoItem>){
        this.movieModel.clear()
        this.movieModel.addAll(movieModel)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_movies, parent, false)
        return MovieViewHolder(view)
    }


    override fun getItemCount():Int{
        return movieModel.size

    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.apply {
            val movie = movieModel[position]
            tvBall.text = movie.key
            tvBall.setOnClickListener {
                itemClick?.invoke(position)

           }



        }



    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMovieImage = itemView.findViewById<ImageView>(R.id.iv_movie_Video)
        val ll_otcenka = itemView.findViewById<LinearLayout>(R.id.ll_1)
        val tvMovieName = itemView.findViewById<TextView>(R.id.tv_movie_name)
        val tvBall = itemView.findViewById<TextView>(R.id.tv_play00764)
        var ll =itemView.findViewById<LinearLayout>(R.id.ll_item_movie_Video)
        val tvYear = itemView.findViewById<TextView>(R.id.tv_movie_janr)



    }

}