package com.example.wowmovie_.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wowmovie_.R
import com.example.wowmovie_.model.MainModel

//class MediaAdapter(val context: Context, private var list: ArrayList<MainModel>)
//    :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    val item_movie = 0
//    val item_post = 1
//
//
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val view:View
//        if (viewType==item_movie) {
//            view =
//                LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
//            return MovieViewHolder(view)
//
//        }else view= LayoutInflater.from(parent.context).inflate(R.layout.item_ludshie_films,parent,false)
//        return PostViewHolder(view)
//
//
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//val movie = list[position]
//        if (holder is PostViewHolder){
//
//        }
//        if (holder is MovieViewHolder){
//            holder.apply {
//                rvMovies.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
//
//            }
//        }
//
//    }
//
//    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//
//
//
//    }
//
//    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val rvMovies = view.findViewById<RecyclerView>(R.id.rvMovieChild)
//
//    }
//}