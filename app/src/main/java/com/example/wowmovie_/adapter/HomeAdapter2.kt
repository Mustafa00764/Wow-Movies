package com.example.wowmovie_.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.wowmovie_.R
import com.example.wowmovie_.model.Movie4
import com.example.wowmovie_.model.Movie5
import com.example.wowmovie_.model.TopRatedRuItem


//class HomeAdapter2 : RecyclerView.Adapter<HomeAdapter2.CollectionViewHolder>() {
//var onClick:((Int) -> Unit)? = null
//
//
//
//    private val collection = ArrayList<Movie5>()
//    fun submitLists(collection: ArrayList<Movie5>) {
//        this.collection.clear()
//        this.collection.addAll(collection)
//        notifyDataSetChanged()
//    }
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_ludshie_films2, parent, false)
//        return CollectionViewHolder(view)
//    }
//
//    override fun getItemCount():Int{
//        return collection.size
//
//    }
//
//
//
//
//    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
//        val collection = collection[position]
//
//
//
//
//holder.apply {
//    onClick?.invoke(position)
//}
//
//
//
//
//
//
//
//            holder.rvMovieChild.adapter = MovieAdapter5(collection.movieUpcoming)
//            holder.rvMovieChild2.adapter = MovieAdapter5(collection.movieUpcoming)
//            holder.rvMovieChild3.adapter = MovieAdapter5(collection.movieUpcoming)
//            holder.rvMovieChild4.adapter = MovieAdapter5(collection.movieUpcoming)
//
//
//
//
//
//
//
//
//
//
//
//
//    }
//
//    inner class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val rvMovieChild = itemView.findViewById<RecyclerView>(R.id.rv_samiy_popular1)
//        val rvMovieChild2 = itemView.findViewById<RecyclerView>(R.id.rv_samiy_popular2)
//        val rvMovieChild3 = itemView.findViewById<RecyclerView>(R.id.rv_samiy_popular3)
//        val rvMovieChild4 = itemView.findViewById<RecyclerView>(R.id.rv_samiy_popular4)
//    }
//}