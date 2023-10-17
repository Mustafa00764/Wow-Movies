package com.example.wowmovie_.adapter

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
import com.example.wowmovie_.model.SaveMovie


class MyAdapter:RecyclerView.Adapter<MyAdapter.MovieViewHolder>() {
    var itemClick: ((Int) -> Unit)? = null
    var deleteItem:((Int)->Unit) ? =null

    private val movieModel= ArrayList<SaveMovie>()
    fun submitList(movieModel: ArrayList<SaveMovie>){
        this.movieModel.clear()
        this.movieModel.addAll(movieModel)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_big_save, parent, false)
        return MovieViewHolder(view)
    }


    override fun getItemCount():Int{
        return movieModel.size

    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.apply {
            val movie = movieModel[position]
            Glide.with(ivMovieImage).load("https://image.tmdb.org/t/p/w500${movie.image}").into(ivMovieImage)
            tvMovieName.text = movie.title
            tvYear.text = movie.ball
            tvBall.text = movie.time

            delete.setOnClickListener {
                deleteItem?.invoke(position)
            }



            llItemMovieMini.setOnClickListener {
                itemClick?.invoke(position)

            }




        }



    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMovieImage = itemView.findViewById<ImageView>(R.id.iv_movie_image1)
        val llItemMovieMini = itemView.findViewById<LinearLayout>(R.id.ll_item_movie_big_save)
        val tvMovieName = itemView.findViewById<TextView>(R.id.tv_movie_name1)
        val tvBall = itemView.findViewById<TextView>(R.id.tv_ball_Save)
        val delete = itemView.findViewById<ImageView>(R.id.iv_movie_delete)
        val tvYear = itemView.findViewById<TextView>(R.id.tv_movie_janr_Save)




    }

}